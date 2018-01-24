package com.jiacer.modules.mybatis.entity;

import java.io.Serializable;

/**
 * Created by gaoyan on 18/07/2017.
 */
public class CourseQuestionSumEntity implements Serializable {

    private Integer courseId;
    private String type;
    private String count;
    private String score;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
