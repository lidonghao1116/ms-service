package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.mybatis.model.CoursePackage;

/**
 * 
* @ClassName: CoursePackageEntity 
* @Description: 课程销售表
* @author 贺章鹏
* @date 2016年10月18日 下午12:06:23 
*
 */
public class CoursePackageEntity extends CoursePackage{
	private static final long serialVersionUID = 1L;

	private Integer schoolId;
	private List<String> statusList;

	private String workType;

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public static class Status{
		public static String LJBM="01";//立即报名
		public static String DSH="02";//待审核
		public static String STOPED="03";//停售
		public static String YBM="04";//已报名
		public static String BKBM="05";//不可报名
	}
}
