package com.jiacer.modules.clientInterface.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jiacer.modules.clientInterface.service.WechatService;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.mybatis.dao.LoginStatusMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.WechatAuthrizeInfoMapper;
import com.jiacer.modules.mybatis.entity.LoginStatusEntity;
import com.jiacer.modules.mybatis.entity.WechatAuthrizeInfoEntity;

public class WechatServiceImpl extends BaseService implements WechatService {
	@Autowired
	UserBaseInfoMapper userDao;
	@Autowired
	LoginStatusMapper loginStatusDao;
	@Autowired
	WechatAuthrizeInfoMapper wecahtAuthrizeDao;
	
	@Override
	public WechatAuthrizeInfoEntity getWechatAuthrizeInfoByOpenId(String openId) {
		// TODO Auto-generated method stub
		return wecahtAuthrizeDao.getById(openId);
	}

	@Override
	public void insertWechatAuthrizeInfo(WechatAuthrizeInfoEntity entity) {
		// TODO Auto-generated method stub
		wecahtAuthrizeDao.insert(entity);
	}

	@Override
	public void updateWechatAuthrizeInfo(WechatAuthrizeInfoEntity entity) {
		// TODO Auto-generated method stub
		wecahtAuthrizeDao.update(entity);
	}

	@Override
	public LoginStatusEntity getLoginStatus(Integer userId, String mobile, String source,String openId) {
		// TODO Auto-generated method stub
		LoginStatusEntity entity = new LoginStatusEntity();
		entity.setUserId(userId);
		entity.setMobile(mobile);
		entity.setSource(source);
		entity.setOpenId(openId);
		List<LoginStatusEntity> list = loginStatusDao.findList(entity);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean login(LoginStatusEntity loginStatus) {
		// TODO Auto-generated method stub
		
		loginStatus.setLastActiveTime(new Date());
		
		return loginStatusDao.update(loginStatus) == 1;
	}

	@Override
	public void updateLoginStatus(LoginStatusEntity loginStatus) {
		// TODO Auto-generated method stub
		loginStatusDao.update(loginStatus);
	}

	@Override
	public LoginStatusEntity getLastLoginStatus(Integer userId, String mobile, String source) {
		// TODO Auto-generated method stub
		LoginStatusEntity entity = new LoginStatusEntity();
		entity.setUserId(userId);
		entity.setMobile(mobile);
		entity.setSource(source);
		List<LoginStatusEntity> list = loginStatusDao.findList(entity);
		if (list != null && list.size() > 0) {
			return list.get(list.size()-1);
		}
		return null;
	}

	@Override
	public LoginStatusEntity getLoginStatusByOpenId(String openId) {
		// TODO Auto-generated method stub
		
		LoginStatusEntity  LoginStatus=loginStatusDao.getByOpenId(openId);
		return LoginStatus;
	}
}
