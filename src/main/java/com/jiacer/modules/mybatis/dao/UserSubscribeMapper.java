package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.model.AddressInfo;
import com.jiacer.modules.mybatis.model.SubscribeInfo;
import com.jiacer.modules.mybatis.model.UserSubscrbes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface UserSubscribeMapper extends CrudDao<SubscribeInfo> {

    /**
     * 查询订阅用户列表
     * @return
     */
    List<UserSubscrbes> getJobSubscribeBySubject(UserSubscrbes subscrbe);

    /**
     * 获取用户openId
     * @param channel
     * @param userId
     * @return
     */
    UserSubscrbes getSubscribeInfoByUserId(@Param("channel") String channel, @Param("userId") Integer userId);

    List<String> findServicesBySubId(Integer subId);
    List<AddressInfo> findAddressesBySubId(Integer subId);

    /**
     * 清空订阅
     * @param tenantUserId
     */
    void clearUserSubscribe(Integer tenantUserId);


    /**
     * 添加订阅主题
     * @param subId
     */
    void insertSubject(@Param("subId") Integer subId, @Param("subject") String subject);

    /**
     * 添加订阅地址
     * @param subId
     * @param addr
     */
    void insertArea(@Param("subId") Integer subId, @Param("addr") AddressInfo addr);
}