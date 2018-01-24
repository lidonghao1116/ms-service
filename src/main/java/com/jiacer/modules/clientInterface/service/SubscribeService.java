package com.jiacer.modules.clientInterface.service;


import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.mybatis.model.SubscribeInfo;

public interface SubscribeService {

	void settingSubscribe(SubscribeInfo subscribeInfo) throws ServiceException;

	SubscribeInfo get(Integer tenantUserId) throws ServiceException;
}
