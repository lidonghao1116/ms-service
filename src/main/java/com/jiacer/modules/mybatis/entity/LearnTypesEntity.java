package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.mybatis.model.LearnTypes;

public class LearnTypesEntity extends LearnTypes{
    
	private static final long serialVersionUID = 1L;
	
	private List<Integer> courseIds;
	
	private Integer userId;
	private String schoolName;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<Integer> courseIds) {
		this.courseIds = courseIds;
	}
	
}