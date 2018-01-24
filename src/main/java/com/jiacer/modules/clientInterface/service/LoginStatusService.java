package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.mybatis.entity.LoginStatusEntity;

public interface LoginStatusService {
	void insertLoginStatus(LoginStatusEntity entity);

	LoginStatusEntity findtLoginStatusByOpenId(String openId);

	boolean queryLoginStatusByOpenId(String openId);

	void deleteLoginStatus(LoginStatusEntity entity);
}
