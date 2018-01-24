package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.UserBaseInfo;

public class UserBaseInfoEntity extends UserBaseInfo{

	private static final long serialVersionUID = 1L;

	private String education;
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
}