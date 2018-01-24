package com.jiacer.modules.common.utils;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class SmsCode {
	/**
	 * session中保存注册短信验证码key
	 */
	public static final String REG_SMS_CODE_KEY = "regSmsCodeKey";
	/**
	 * session中保存重置密码短信验证码key
	 */
	public static final String RESET_PWD_SMS_CODE = "restSmsCodeKey";
	
	/**
	 * 绑定的手机号
	 */
	private String mobile;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 创建时间
	 */
	private long createTime;
	/**
	 * 有效时长，毫秒数
	 */
	private long liveTime;
	
	public SmsCode() {}
	
	/**
	 * 
	 * @param mobile
	 * @param code
	 */
	public SmsCode(String mobile, String code, int liveMinites) {
		this.mobile = mobile;
		this.code = code;
		this.createTime = new Date().getTime();
		this.liveTime = liveMinites * DateUtils.MILLIS_PER_MINUTE;
	}
	
	/**
	 * 是否失效
	 * @return true：过期
	 */
	public boolean isExpired(){
		long currentTime = new Date().getTime();
		if ((currentTime - createTime) > liveTime) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否是绑定的手机号
	 * @param mobile
	 * @return true：是
	 */
	public boolean isBindingMobile(String mobile) {
		return this.mobile.equals(mobile);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(long liveTime) {
		this.liveTime = liveTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
