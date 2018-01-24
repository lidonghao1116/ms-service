package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.mybatis.model.ApplyOrders;

/**
 * 
* @ClassName: ApplyOrdersEntity 
* @Description: 申请报班订单表
* @author 贺章鹏
* @date 2016年10月18日 下午12:04:49 
*
 */
public class ApplyOrdersEntity extends ApplyOrders{
	private static final long serialVersionUID = 1L;
	
	private List<String> hstatusList;
	
	private List<Integer> courseIdList;

	private Integer schoolId;

	private String packageName;

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getHstatusList() {
		return hstatusList;
	}

	public void setHstatusList(List<String> hstatusList) {
		this.hstatusList = hstatusList;
	}

	public List<Integer> getCourseIdList() {
		return courseIdList;
	}

	public void setCourseIdList(List<Integer> courseIdList) {
		this.courseIdList = courseIdList;
	}
	
}
