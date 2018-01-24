package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.SmsRecordEntity;


@MyBatisDao
public interface SmsRecordMapper extends CrudDao<SmsRecordEntity> {
	
	List<SmsRecordEntity> getBySmsId(String smsId);
}