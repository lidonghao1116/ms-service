package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.clientInterface.bean.RegisterParamsBean;
import com.jiacer.modules.clientInterface.model.CertInfo;
import com.jiacer.modules.mybatis.entity.LoginRecordEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import com.jiacer.modules.mybatis.model.UserExtendInfo;

import java.util.List;

public interface UserService {

	UserBaseInfoEntity getUser(String mobile);

	boolean validateLockState(UserBaseInfoEntity user);

	boolean login(UserBaseInfoEntity user, String loginPwd);

	void loginRecord(LoginRecordEntity record);

	/**
	 * @param mobile
	 * @return
	 */
	boolean isMobileExisted(String mobile);

	/**
	 * @param params
	 * @throws Exception 
	 */
	void register(RegisterParamsBean params) throws Exception;

	/**
	 * @param mobile
	 * @return
	 */
	UserBaseInfoEntity getUserByMobile(String mobile);
	
	/**
	 * @param user
	 * @param password
	 */
	void resetPwd(UserBaseInfoEntity user, String password);

	boolean isCertNoExisted(String certNo);

	UserBaseInfoEntity getUserByOpenId(String openId);

	void updateUserBaseInfo(UserBaseInfoEntity user);
	void updateUserExtendInfo(UserExtendInfo user) ;


    UserBaseInfoEntity getUserByInvideCode(String inviterCode);


	List<CertInfo> getOwnCertList(Integer userId);

}
