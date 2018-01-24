package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class UserAnswers implements Serializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：user_answers
     * 字段：ID
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：user_answers
     * 字段：USER_ID
     * 注释：用户id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 表：user_answers
     * 字段：ADD_TIME
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：user_answers
     * 字段：QUESTIONS_ID
     * 注释：问题ID
     *
     * @mbggenerated
     */
    private Integer questionsId;

    /**
     * 表：user_answers
     * 字段：ANSWER_KEYS
     * 注释：答案集合 逗号分隔
     *
     * @mbggenerated
     */
    private String answerKeys;

    /**
     * 表：user_answers
     * 字段：BATCH_ID
     * 注释：答题批次
     *
     * @mbggenerated
     */
    private Integer batchId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(Integer questionsId) {
        this.questionsId = questionsId;
    }

    public String getAnswerKeys() {
        return answerKeys;
    }

    public void setAnswerKeys(String answerKeys) {
        this.answerKeys = answerKeys == null ? null : answerKeys.trim();
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
}