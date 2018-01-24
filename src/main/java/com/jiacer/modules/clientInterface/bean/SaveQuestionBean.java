package com.jiacer.modules.clientInterface.bean;

import java.io.Serializable;
import java.util.List;

import com.jiacer.modules.mybatis.entity.QuestionsEntity;

public class SaveQuestionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer typeId;
	
	private Integer batchId;
	
	private List<QuestionsEntity> answers;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public List<QuestionsEntity> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QuestionsEntity> answers) {
		this.answers = answers;
	}
	
	
}
