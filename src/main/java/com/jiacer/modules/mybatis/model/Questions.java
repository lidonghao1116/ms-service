package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class Questions implements Serializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：questions
     * 字段：ID
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：questions
     * 字段：QUESTION_TYPE
     * 注释：题目类型
     *
     * @mbggenerated
     */
    private String questionType;

    /**
     * 表：questions
     * 字段：QUESTION
     * 注释：题目
     *
     * @mbggenerated
     */
    private String question;

    /**
     * 表：questions
     * 字段：IS_USABLE
     * 注释：是否可用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：questions
     * 字段：ADD_ACCOUNT
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：questions
     * 字段：ADD_TIME
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：questions
     * 字段：UPDATE_ACCOUNT
     * 注释：更新人
     *
     * @mbggenerated
     */
    private String updateAccount;

    /**
     * 表：questions
     * 字段：UPDATE_TIME
     * 注释：更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 表：questions
     * 字段：type_id
     * 注释：课程分类id
     *
     * @mbggenerated
     */
    private Integer typeId;

    //在线课程id
    private Integer courseId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable == null ? null : isUsable.trim();
    }

    public String getAddAccount() {
        return addAccount;
    }

    public void setAddAccount(String addAccount) {
        this.addAccount = addAccount == null ? null : addAccount.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(String updateAccount) {
        this.updateAccount = updateAccount == null ? null : updateAccount.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}