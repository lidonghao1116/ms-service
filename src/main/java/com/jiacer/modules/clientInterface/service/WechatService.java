package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.mybatis.entity.LoginStatusEntity;
import com.jiacer.modules.mybatis.entity.WechatAuthrizeInfoEntity;

public interface WechatService {

	WechatAuthrizeInfoEntity getWechatAuthrizeInfoByOpenId(String openId);

	void insertWechatAuthrizeInfo(WechatAuthrizeInfoEntity entity);

	void updateWechatAuthrizeInfo(WechatAuthrizeInfoEntity entity);

	LoginStatusEntity getLoginStatusByOpenId(String openId);

	LoginStatusEntity getLoginStatus(Integer userId, String mobile, String source,String openId);

	LoginStatusEntity getLastLoginStatus(Integer userId, String mobile, String source);

	void updateLoginStatus(LoginStatusEntity loginStatus);

	boolean login(LoginStatusEntity loginStatus);

}
