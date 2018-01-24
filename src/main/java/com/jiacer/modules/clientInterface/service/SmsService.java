package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.common.utils.SmsCode;
import com.jiacer.modules.mybatis.entity.SmsRecordEntity;

public interface SmsService {
	
	/**
	 * 发送注册验证码短信
	 * @param mobile
	 * @return
	 */
	public SmsCode sendRegisterSmsCode(String mobile);

	/**
	 * 发送重置密码短信
	 * @param mobile
	 * @return
	 */
	public SmsCode sendResetPwdSmsCode(String mobile);
	
	/**
	 * 确认短时发送结果
	 * @param entity
	 */
	public void confirmSmsSendResult(SmsRecordEntity entity);
	
	/**
	 * 获取短信发送结果
	 * @param smsId
	 * @return
	 */
	public SmsRecordEntity getSmsRecordBySmsId(String smsId);
	
}
