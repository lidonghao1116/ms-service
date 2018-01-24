package com.jiacer.modules.mybatis.model;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class UserScoresKey implements ModelSerializable{
	private static final long serialVersionUID = 1L;

	/**
     * 表：user_scores
     * 字段：class_id
     * 注释：班级id
     *
     * @mbggenerated
     */
    private Integer classId;

    /**
     * 表：user_scores
     * 字段：user_id
     * 注释：学员id
     *
     * @mbggenerated
     */
    private Integer userId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}