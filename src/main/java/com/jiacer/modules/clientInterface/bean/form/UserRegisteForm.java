package com.jiacer.modules.clientInterface.bean.form;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: UserRegisteFrom 
* @Description: 注册／绑定页面
* @author 贺章鹏
* @date 2016年11月16日 上午10:19:12 
*  
*/
public class UserRegisteForm implements ModelSerializable{
	
	private static final long serialVersionUID = 1L;
	
	private String mobile;//手机号码
	
	private String captchaCode;//图形验证码
	
	private String smsCode;//短信验证码

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

}
