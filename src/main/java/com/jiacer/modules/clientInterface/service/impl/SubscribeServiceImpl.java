package com.jiacer.modules.clientInterface.service.impl;

import com.jiacer.modules.clientInterface.service.SubscribeService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.mybatis.dao.UserSubscribeMapper;
import com.jiacer.modules.mybatis.model.AddressInfo;
import com.jiacer.modules.mybatis.model.SubscribeInfo;
import com.jiacer.modules.mybatis.model.UserSubscrbes;
import com.jiacer.modules.wechat.message.config.WechatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class SubscribeServiceImpl implements SubscribeService {

	@Autowired
	private UserSubscribeMapper userSubscribeMapper;

	/**
	 * 订阅设置
	 * @param subscribeInfo
	 * @throws ServiceException
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void settingSubscribe(SubscribeInfo subscribeInfo) throws ServiceException {
		//清空订阅消息
		userSubscribeMapper.clearUserSubscribe(subscribeInfo.getUserId());

		if("0".equals(subscribeInfo.getIsOn())){
			//取消订阅
			return;
		}
		//设置订阅消息
		Integer subId = null;
		for(String channel : subscribeInfo.getAllChannels()){
			subscribeInfo.setId(null);
			subscribeInfo.setChannel(channel);
			userSubscribeMapper.insert(subscribeInfo);
			if(channel.equals(WechatConfig.Channel.JOB_MS_PUBLISH.getChannel())){
				subId = subscribeInfo.getId();
			}
		}
		if(subscribeInfo.getServices() != null){
			for(String subject : subscribeInfo.getServices()){
				userSubscribeMapper.insertSubject(subId, subject);
			}
		}
		if(subscribeInfo.getAddressInfos() != null){
			for(AddressInfo addr : subscribeInfo.getAddressInfos()){
				userSubscribeMapper.insertArea(subId, addr);
			}
		}

	}

	/**
	 * 订阅查询
	 */
	@Override
	public SubscribeInfo get(Integer userId) throws ServiceException {
		SubscribeInfo subscribeInfo = new SubscribeInfo();
		UserSubscrbes sub = userSubscribeMapper.getSubscribeInfoByUserId(WechatConfig.Channel.JOB_MS_PUBLISH.getChannel(), userId);
		if(sub == null){
			subscribeInfo.setIsOn("0");
			return subscribeInfo;
		}

		subscribeInfo.setIsOn("1");
		subscribeInfo.setServices(userSubscribeMapper.findServicesBySubId(sub.getId()));
		subscribeInfo.setAddressInfos(userSubscribeMapper.findAddressesBySubId(sub.getId()));

		return subscribeInfo;
	}

}
