package com.jiacer.modules.clientInterface.bean;

import java.io.Serializable;

public class ResetPwdParamsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mobile;
	
	private String password;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
