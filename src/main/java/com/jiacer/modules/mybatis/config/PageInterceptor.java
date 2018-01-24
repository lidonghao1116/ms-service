package com.jiacer.modules.mybatis.config;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonUtilsNoneStrategy;
import com.jiacer.modules.common.utils.Reflections;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


/**
 * <b>分页拦截器</b>
 * <p>
 * 拦截所有dao层的需要分页的sql查询
 * </p>
 * Created by gaoya on 16/1/13.
 */
@Intercepts(@Signature(args = {Connection.class, Integer.class}, method = "prepare", type = StatementHandler.class))
public class PageInterceptor implements Interceptor {

    //private Logger log = LoggerFactory.getLogger(PageInterceptor.class);
    private String dialect;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) target;
        StatementHandler statementHandler = (StatementHandler) Reflections.getFieldValue(routingStatementHandler, "delegate");
        // 这个对象封装了SQL语句
        BoundSql boundSql = statementHandler.getBoundSql();
        Object parameObject = boundSql.getParameterObject();
        //log.debug("sql:{}", boundSql.getSql());
        //log.debug("param:{}", JsonUtilsNoneStrategy.toJson(parameObject));
        if ((parameObject instanceof Page) && boundSql.getSql().toUpperCase().startsWith("SELECT")) {
            // 全部转为大写
            String sql = boundSql.getSql().toUpperCase();
            //得到计算总记录数的sql。
            String countSql = "SELECT COUNT(*) " + sql.substring(sql.lastIndexOf("FROM"));
            // 得到MappedStatement对象
            MappedStatement mappedStatement =
                    (MappedStatement) Reflections.getFieldValue(statementHandler, "mappedStatement");
            // 通过注解得到参数
            Connection connection = (Connection) invocation.getArgs()[0];
            Page page = (Page) parameObject;

            setTotalRecord(page, mappedStatement, connection, countSql);

            //将SQL语句进行封装。
            String changeValue = paginationSql(sql, page);
            //改变BoundSql对象的私有属性sql的值
            changePrivateAttributeValue(boundSql, "sql", changeValue);
        } else {
            //log.debug("not page query object");
        }
        //得到要执行的方法
        Method method = invocation.getMethod();
        //可以使用这个invocation.proceed()
        Object object = method.invoke(target, invocation.getArgs());
        return object;

    }

    /**
     * 拦截的方法，target就是拦截到的目标对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof RoutingStatementHandler) {
            //这里是一Mybatis自带的动态代理方法，这样就会调用上面的intercept。
            //其实内部就是下面的方法
            return Plugin.wrap(target, this);
        }
        return target;
    }


    /**
     * 读取配置文件中的配置
     */
    @Override
    public void setProperties(Properties properties) {
        this.dialect = properties.getProperty("dialect");
    }

    /**
     * 判断是哪个数据库
     * 将查询语句的组装成分页语句
     *
     * @param sql
     * @return
     */
    private String paginationSql(String sql, Page page) {
        return mySqlPaginationSql(sql, page.getFirstIndex(), page.getPageSize());
    }


    /**
     * MySQL数据库的分页
     *
     * @param sql
     * @return
     */
    private String mySqlPaginationSql(String sql, int startIndex, int pageSize) {
        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        sb.append(" limit " + startIndex + "," + pageSize);
        return sb.toString();
    }

    /**
     * 查询总记录数的方法,执行SQL语句
     *
     * @param page            封装了参数
     * @param mappedStatement
     * @param connection      链接
     * @param countSql        sql语句
     */
    private void setTotalRecord(Page page, MappedStatement mappedStatement, Connection connection,
                                String countSql) {
        // 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
        //delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        //通过BoundSql获取对应的参数映射
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //利用Configuration、查询记录数的Sql语句countSql、
        //参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
        //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
        //通过connection建立一个countSql对应的PreparedStatement对象。
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //通过parameterHandler给PreparedStatement对象设置参数
            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);
            // 执行sql
            rs = pstmt.executeQuery();
            if (rs.next()) {
                page.setRecords(rs.getInt(1));
            }
        } catch (SQLException e) {
            //log.error(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
              //  log.error(e.getMessage());
            }
        }
    }

    /**
     * 改变某个类的私有属性的方法
     *
     * @param target        类的实力对象
     * @param attributeName 要改变的属性名
     * @param changeValue   该表属性的值
     */
    private static void changePrivateAttributeValue(Object target,
                                                    String attributeName, Object changeValue) {
        ObjectFactory objectFactory = new DefaultObjectFactory();
        ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
        ReflectorFactory reflector = new DefaultReflectorFactory();
        MetaObject metaObject = MetaObject.forObject(target, objectFactory, objectWrapperFactory, reflector);
        metaObject.setValue(attributeName, changeValue);
    }
}

