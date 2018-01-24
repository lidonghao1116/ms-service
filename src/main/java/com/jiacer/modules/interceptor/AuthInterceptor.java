package com.jiacer.modules.interceptor;

import com.jiacer.modules.clientInterface.common.SessionKeys;
import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.anno.Guest;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.service.UserService;
import com.jiacer.modules.clientInterface.service.WechatService;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.JsonUtils;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.model.WechatAuthrizeInfo;
import net.sf.json.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
public class AuthInterceptor {

    private static final Logger log = LoggerFactory.getLogger(UserInterceptor.class);


    @Autowired
    private UserService userService;
    @Autowired
    private WechatService wechatService;


    @Pointcut("execution(public * com.jiacer.modules.clientInterface.controller..*.*(..))")
    public void point() {

    }

    @Around("point()")
    public Object aroundAdvice(ProceedingJoinPoint joinpoint) throws Throwable {
        Method m = ((MethodSignature) joinpoint.getSignature()).getMethod();
        boolean guest = m.isAnnotationPresent(Guest.class);
        boolean auth = m.isAnnotationPresent(Auth.class);
        HttpServletRequest request = SessionManager.getRequest();
        HttpServletResponse response = SessionManager.getResponse();

        log.info("method : " + m.getName());
        String openId = request.getHeader(Constants.REQ_WX_OPENID);
        System.out.println("=========================================");
        System.out.println("openId==========================:" + openId);
        System.out.println("=========================================");
        WechatAuthrizeInfo wechat = null;
        UserBaseInfoEntity user = null;
        if (StringUtils.isNotBlank(openId)) {
            wechat = wechatService.getWechatAuthrizeInfoByOpenId(openId);
            System.out.println("wechat==========================:" + JsonUtils.toJson(wechat));
            SessionManager.getSession().setAttribute(Constants.Session.WX_USER_INFO, wechat);
            user = userService.getUserByOpenId(openId);
            System.out.println("user==========================:" + JsonUtils.toJson(user));
            SessionManager.getSession().setAttribute(SessionKeys.USER_SESSION, user);
        }else{
            SessionManager.getSession().removeAttribute(Constants.Session.WX_USER_INFO);
            SessionManager.getSession().removeAttribute(SessionKeys.USER_SESSION);
            SessionManager.getSession().invalidate();
        }

        //若请求方法允许游客访问，则不验证
        if (guest) {
            log.info("guest method, skipping");
            return joinpoint.proceed();
        }
        log.info("StringUtils.isBlank(openId) [" + openId + "] ");
        if (StringUtils.isBlank(openId) || wechat == null) {
            log.info("auth method , check openid and wxAuth");
            //若请求中不带openId，则请求没有授权，需要授权
            //wx.jiacedu.com
            JsonResult jr = JsonResult.failure(ErrorCode.WX_AUTH_ERROR);
            return jr;
        }

        //若请求存在openId， 查询该Id对应的用户
        if (!auth && user == null) {
            //其他方法都是私有，需要注册用户信息
            //如果带有openId，但没有用户信息，则需要注册
            log.info("user need login");
            JsonResult jr = JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
            return jr;
        }

        return joinpoint.proceed();
    }

}
