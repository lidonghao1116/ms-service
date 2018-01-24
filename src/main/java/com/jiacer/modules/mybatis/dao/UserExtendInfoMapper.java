package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

@MyBatisDao
public interface UserExtendInfoMapper extends CrudDao<UserExtendInfoEntity>{

	/**
	 * @param certNo
	 * @return
	 */
	int countByCertNo(String certNo);
}