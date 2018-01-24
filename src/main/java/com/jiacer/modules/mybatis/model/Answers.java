package com.jiacer.modules.mybatis.model;

import java.io.Serializable;

public class Answers implements Serializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：answers
     * 字段：ID
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：answers
     * 字段：ANSWER_DESC
     * 注释：答案描述
     *
     * @mbggenerated
     */
    private String answerDesc;

    /**
     * 表：answers
     * 字段：QUESTIONS_ID
     * 注释：问题主键
     *
     * @mbggenerated
     */
    private Integer questionsId;

    /**
     * 表：answers
     * 字段：IS_ANSWER
     * 注释：是否是答案
     *
     * @mbggenerated
     */
    private String isAnswer;

    /**
     * 表：answers
     * 字段：IS_USABLE
     * 注释：是否可用
     *
     * @mbggenerated
     */
    private String isUsable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerDesc() {
        return answerDesc;
    }

    public void setAnswerDesc(String answerDesc) {
        this.answerDesc = answerDesc == null ? null : answerDesc.trim();
    }

    public Integer getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(Integer questionsId) {
        this.questionsId = questionsId;
    }

    public String getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer == null ? null : isAnswer.trim();
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable == null ? null : isUsable.trim();
    }
}