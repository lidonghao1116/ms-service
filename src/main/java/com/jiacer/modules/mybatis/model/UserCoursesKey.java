package com.jiacer.modules.mybatis.model;

public class UserCoursesKey {
    /**
     * 表：user_courses
     * 字段：user_id
     * 注释：用户ID
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 表：user_courses
     * 字段：type_id
     * 注释：课程分类id
     *
     * @mbggenerated
     */
    private Integer typeId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}