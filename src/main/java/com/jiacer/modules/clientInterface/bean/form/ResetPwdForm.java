package com.jiacer.modules.clientInterface.bean.form;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: ResetPwdForm 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月16日 下午12:28:58 
*  
*/
public class ResetPwdForm implements ModelSerializable{
	private static final long serialVersionUID = 1L;

	private String mobile;//手机号码
	
	private String captchaCode;//图形验证码
	
	private String password;//密码

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
