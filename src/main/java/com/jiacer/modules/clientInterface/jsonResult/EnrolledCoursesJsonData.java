package com.jiacer.modules.clientInterface.jsonResult;

import com.jiacer.modules.common.persistence.ModelSerializable;

import java.util.Date;

/** 
* @ClassName: EnrolledCoursesJsonData 
* @Description: 已报名课程返回数据结构
* @author 贺章鹏
* @date 2016年11月16日 下午3:49:41 
*  
*/
public class EnrolledCoursesJsonData implements ModelSerializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer courseId;//课程id
	private Integer userId;

	private String courseName;//课程名称
	
	private String remarks;//课程简介
	
	private String cover;//封面图片

	private String signStatus;  //报名状态
	private Date signDate;      //报名时间
	private String learnCycle;  //学习周期
	private String examForm;    //考核类型
	private String school;     //培训学校
	private Integer schoolId;
	private String packageName; //产品名称
    private String examResult;//考试结果-01.02.03等
	
	
	public String getExamResult() {
		return examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getLearnCycle() {
		return learnCycle;
	}

	public void setLearnCycle(String learnCycle) {
		this.learnCycle = learnCycle;
	}

	public String getExamForm() {
		return examForm;
	}

	public void setExamForm(String examForm) {
		this.examForm = examForm;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
}
