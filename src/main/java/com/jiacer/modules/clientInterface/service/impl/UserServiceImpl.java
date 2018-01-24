package com.jiacer.modules.clientInterface.service.impl;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import com.jiacer.modules.clientInterface.model.CertInfo;
import com.jiacer.modules.mybatis.dao.*;
import com.jiacer.modules.mybatis.model.UserExtendInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiacer.modules.clientInterface.bean.RegisterParamsBean;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.IdcardInfoExtractor;
import com.jiacer.modules.clientInterface.service.UserService;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.entity.LoginRecordEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

@Service
public class UserServiceImpl implements UserService{

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserBaseInfoMapper userDao;
	
	@Autowired
	UserExtendInfoMapper userExtendDao;
	
	@Autowired
	LoginRecordMapper loginRecordDao;

	@Autowired
	UserCertMapper userCertMapper;

	private static NumberFormat nf = NumberFormat.getInstance();

	static {
		nf.setGroupingUsed(false);
		nf.setMaximumIntegerDigits(Integer.SIZE);
		nf.setMinimumIntegerDigits(5);
	}


	@Override
	public UserBaseInfoEntity getUser(String mobile) {
		return userDao.getByMobile(mobile);
	}

	@Override
	public boolean validateLockState(UserBaseInfoEntity user) {

		if (Global.YES.equals(user.getIsLocked())) {
			if ((new Date().getTime() - user.getLockTime().getTime() < 120 * 60000)) {
				return true;
			} else {
				user.setIsLocked(Global.NO);
				user.setLockTime(null);
				user.setPwdTryCount(0);
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean login(UserBaseInfoEntity user, String loginPwd) {
		boolean result = true;
		Date currentDate = new Date();
		if (user.getLoginPassword().equals(EntryptUtils.entryptUserPassword(loginPwd.toUpperCase(), user.getMobile()))) {
			result = true;
			user.setLastLoginTime(currentDate);
			user.setPwdTryCount(0);
		} else {
			result = false;
			if (user.getPwdTryCount() == null || user.getPwdTryCount() == 0) {
				user.setFirstWrongTime(currentDate);
				user.setPwdTryCount(1);
			} else {
				if (currentDate.getTime() - user.getFirstWrongTime().getTime() < 30 * 60 * 1000) {
					user.setPwdTryCount(user.getPwdTryCount() + 1);
					if (user.getPwdTryCount() >= 5) {
						user.setIsLocked(Global.YES);
						user.setLockTime(currentDate);
					}
				} else {
					user.setFirstWrongTime(currentDate);
					user.setPwdTryCount(1);
				}
			}
		}
		UserBaseInfoEntity storedUser = new UserBaseInfoEntity();
		storedUser.setMobile(user.getMobile());
		storedUser.setFirstWrongTime(user.getFirstWrongTime());
		storedUser.setLastLoginTime(user.getLastLoginTime());
		storedUser.setPwdTryCount(user.getPwdTryCount());
		storedUser.setIsLocked(user.getIsLocked());
		storedUser.setLockTime(user.getLockTime());
		
		return result;
	}

	@Override
	public void loginRecord(LoginRecordEntity record) {
		loginRecordDao.insert(record);
	}

	@Override
	public boolean isMobileExisted(String mobile) {
		return userDao.countByMobile(mobile) > 0;
	}

	@Override
	public void register(RegisterParamsBean params) throws Exception{
		UserBaseInfoEntity entity = new UserBaseInfoEntity();
		entity.setMobile(params.getMobile());
		entity.setSalt(String.format(Constants.ENTRY_STRING, params.getMobile()));
		entity.setUserStatus(DBStatus.UserStatus.NORMAL);
		entity.setIsLocked(Global.NO);
		entity.setPwdTryCount(0);
		entity.setRegisterTime(new Date());
		entity.setAddTime(entity.getRegisterTime());
		entity.setModifyTime(entity.getAddTime());
		entity.setUserType(DBStatus.UserType.NORMAL);
		entity.setGender(params.getSex());
		entity.setWechatNick(params.getWechatNick());
		entity.setWechatImage(params.getWechatImage());
		entity.setOpenId(params.getOpenId());
		entity.setIsInvite(params.getIsInvite());

		if (userDao.insert(entity) != 1) {
			throw new ServiceException("插入用户基本信息发生异常");
		}

		UserBaseInfoEntity e1 = new UserBaseInfoEntity();
		e1.setId(entity.getId());
		e1.setInviteCode(nf.format(entity.getId()));
		userDao.update(e1);

		entity.setInviteCode(e1.getInviteCode());

		UserExtendInfoEntity extendUser = new UserExtendInfoEntity();
		extendUser.setUserId(entity.getId());
		extendUser.setUserName(entity.getUserName());
		extendUser.setCertType(entity.getCertType());
		extendUser.setCertNo(entity.getCertNo());
		extendUser.setEducation(entity.getEducation());

		if (userExtendDao.insert(extendUser) != 1) {
			throw new ServiceException("插入用户扩展信息发生异常");
		}

	}

	@Override
	public UserBaseInfoEntity getUserByMobile(String mobile) {
		UserBaseInfoEntity entity = new UserBaseInfoEntity();
		entity.setMobile(mobile);
		List<UserBaseInfoEntity> list = userDao.findList(entity);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
 		return null;
	}
    


	@Override
	public void resetPwd(UserBaseInfoEntity user, String password) {
		user.setLoginPassword(EntryptUtils.entryptUserPassword(password,user.getMobile()));
		user.setFirstWrongTime(null);
		user.setPwdTryCount(0);
		user.setIsLocked(Global.NO);
		user.setLockTime(null);
		user.setModifyTime(new Date());

		userDao.update(user);
	}


	@Override
	public boolean isCertNoExisted(String certNo) {
		return userExtendDao.countByCertNo(certNo) > 0;
	}

	@Override
	public UserBaseInfoEntity getUserByOpenId(String openId) {
		if(StringUtils.isBlank(openId)){
			return null;
		}
		log.info("UserServiceImpl======================="+openId);
		UserBaseInfoEntity entity = new UserBaseInfoEntity();
		entity.setOpenId(openId);
		log.info("UserServiceImpl=======================UserBaseInfoEntity"+entity.getOpenId());
		List<UserBaseInfoEntity> list = userDao.findList(entity);
		log.info("UserServiceImpl======================="+list.size());
		if (list.size() == 1) {
			return list.get(0);
		}
 		return null;
	}

	@Override
	public void updateUserBaseInfo(UserBaseInfoEntity user) {
		IdcardInfoExtractor idcard = null;
		UserExtendInfoEntity extendUser = new UserExtendInfoEntity();
		extendUser.setCertNo(user.getCertNo());
		extendUser.setCertType(user.getCertType());
		extendUser.setUserName(user.getUserName());
		extendUser.setUserId(user.getId());
		extendUser.setEducation(user.getEducation());
		if(com.jiacer.modules.common.utils.StringUtils.isNotBlank(user.getCertNo())){
			idcard = new IdcardInfoExtractor(user.getCertNo());
			user.setSex(idcard.getGender());
			extendUser.setSex(idcard.getGender());
			extendUser.setAge(idcard.getAge());
			extendUser.setBirthplace(idcard.getProvinceId());
		}
		userDao.update(user);
		if(!com.jiacer.modules.common.utils.StringUtils.isAllBlank(extendUser.getCertNo(),
				extendUser.getUserName(),extendUser.getEducation(),extendUser.getSex(),extendUser.getBirthplace())){
			userExtendDao.update(extendUser);
		}

	}

	@Override
	public void updateUserExtendInfo(UserExtendInfo user) {
		UserExtendInfoEntity extendUser = new UserExtendInfoEntity();
		extendUser.setSex(user.getSex());

		if (userExtendDao.insert(extendUser) != 1) {
			throw new ServiceException("插入用户扩展信息发生异常");
		}
	}

	@Override
	public UserBaseInfoEntity getUserByInvideCode(String inviterCode){
		return userDao.getByInviteCode(inviterCode);
	}


	@Override
	public List<CertInfo> getOwnCertList(Integer userId) {
		return userCertMapper.getByUserId(userId);
	}
}
