package com.jiacer.modules.common.utils;

import java.io.Serializable;
import java.util.Date;

public class CaptchaCode implements Serializable {
	private static final long serialVersionUID = 6461440170680145798L;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 创建时间，Date.getTime()
	 */
	private long createTime;
	/**
	 * 存活时间，毫秒数
	 */
	private long liveTime;
	
	public CaptchaCode() {}
	
	public CaptchaCode(String code, long createTime, long liveTime) {
		this.code = code;
		this.createTime = createTime;
		this.liveTime = liveTime;
	}
	
	/**
	 * 是否过期，
	 * @return true：过期
	 */
	public boolean isExpired(){
		long currentTime = new Date().getTime();
		if (currentTime - createTime > liveTime) {
			return true;
		}
		return false;
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
}
