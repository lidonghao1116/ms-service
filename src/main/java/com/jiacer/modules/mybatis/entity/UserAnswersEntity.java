package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.UserAnswers;

public class UserAnswersEntity extends UserAnswers{

	private static final long serialVersionUID = 1L;
	
	private String isCorrect;//是否正确答案

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}
	
}
