package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;

@MyBatisDao
public interface UserBaseInfoMapper extends CrudDao<UserBaseInfoEntity>{

	UserBaseInfoEntity getByMobile(String mobile);

	/**
	 * @param mobile
	 * @return
	 */
	int countByMobile(String mobile);

    UserBaseInfoEntity getByInviteCode(String inviteCode);
}