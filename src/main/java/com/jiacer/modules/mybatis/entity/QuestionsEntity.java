package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.mybatis.model.Questions;

public class QuestionsEntity extends Questions{

	private static final long serialVersionUID = 1L;
	
	private List<AnswersEntity> answers;//问题答案集合
	
	private String correctAnswers;//正确答案

	public List<AnswersEntity> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswersEntity> answers) {
		this.answers = answers;
	}

	public String getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(String correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

}
