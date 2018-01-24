package com.jiacer.modules.mybatis.entity;

import java.util.Date;

import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.model.SmsRecord;


public class SmsRecordEntity extends SmsRecord{
    
	private static final long serialVersionUID = 1L;
	
	public SmsRecordEntity() {
		setSpCode(DBStatus.SmsSpCode.YU_TONG_XUN);
		setAddTime(new Date());
	}
	
}