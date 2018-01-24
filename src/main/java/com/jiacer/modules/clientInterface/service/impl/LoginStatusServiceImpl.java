package com.jiacer.modules.clientInterface.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jiacer.modules.clientInterface.service.LoginStatusService;
import com.jiacer.modules.mybatis.dao.LoginStatusMapper;
import com.jiacer.modules.mybatis.entity.LoginStatusEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;

public class LoginStatusServiceImpl implements LoginStatusService {

	@Autowired
	LoginStatusMapper loginStatusDao;
	@Override
	public void insertLoginStatus(LoginStatusEntity entity) {
		// TODO Auto-generated method stub
		loginStatusDao.insert(entity);
	}
	@Override
	public LoginStatusEntity findtLoginStatusByOpenId(String openId) {
		// TODO Auto-generated method stub

		return loginStatusDao.getByOpenId(openId);
	}
	@Override
	public void deleteLoginStatus(LoginStatusEntity entity) {
		// TODO Auto-generated method stub
		loginStatusDao.delete(entity);
	}
	@Override
	public boolean queryLoginStatusByOpenId(String openId) {
		// TODO Auto-generated method stub
		LoginStatusEntity entity=new LoginStatusEntity();
		entity.setOpenId(openId);
		List<LoginStatusEntity> list = loginStatusDao.findList(entity);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
	
}
