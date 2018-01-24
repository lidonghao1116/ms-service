package com.jiacer.modules.mybatis.model;

import java.util.Date;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class ExamClass implements ModelSerializable{
    
	private static final long serialVersionUID = 1L;

	/**
     * 表：exam_class
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：exam_class
     * 字段：class_name
     * 注释：班级名称
     *
     * @mbggenerated
     */
    private String className;

    /**
     * 表：exam_class
     * 字段：course_id
     * 注释：课程Id
     *
     * @mbggenerated
     */
    private Integer courseId;

    /**
     * 表：exam_class
     * 字段：shool_id
     * 注释：报考学校
     *
     * @mbggenerated
     */
    private Integer shoolId;

    /**
     * 表：exam_class
     * 字段：class_number
     * 注释：班级标号
     *
     * @mbggenerated
     */
    private String classNumber;

    /**
     * 表：exam_class
     * 字段：exam_form
     * 注释：考试形式
     *
     * @mbggenerated
     */
    private String examForm;

    /**
     * 表：exam_class
     * 字段：exam_status
     * 注释：考试状态
     *
     * @mbggenerated
     */
    private String examStatus;

    /**
     * 表：exam_class
     * 字段：theory_date
     * 注释：理论日期
     *
     * @mbggenerated
     */
    private Date theoryDate;

    /**
     * 表：exam_class
     * 字段：theory_address
     * 注释：(理论)考场地址
     *
     * @mbggenerated
     */
    private String theoryAddress;

    /**
     * 表：exam_class
     * 字段：operation_date
     * 注释：实操日期
     *
     * @mbggenerated
     */
    private Date operationDate;

    /**
     * 表：exam_class
     * 字段：operation_address
     * 注释：(实操)考场地址
     *
     * @mbggenerated
     */
    private String operationAddress;

    /**
     * 表：exam_class
     * 字段：is_usable
     * 注释：是否可用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：exam_class
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：exam_class
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：exam_class
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：exam_class
     * 字段：modify_account
     * 注释：修改人
     *
     * @mbggenerated
     */
    private String modifyAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getShoolId() {
        return shoolId;
    }

    public void setShoolId(Integer shoolId) {
        this.shoolId = shoolId;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber == null ? null : classNumber.trim();
    }

    public String getExamForm() {
        return examForm;
    }

    public void setExamForm(String examForm) {
        this.examForm = examForm == null ? null : examForm.trim();
    }

    public String getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus == null ? null : examStatus.trim();
    }

    public Date getTheoryDate() {
        return theoryDate;
    }

    public void setTheoryDate(Date theoryDate) {
        this.theoryDate = theoryDate;
    }

    public String getTheoryAddress() {
        return theoryAddress;
    }

    public void setTheoryAddress(String theoryAddress) {
        this.theoryAddress = theoryAddress == null ? null : theoryAddress.trim();
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationAddress() {
        return operationAddress;
    }

    public void setOperationAddress(String operationAddress) {
        this.operationAddress = operationAddress == null ? null : operationAddress.trim();
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable == null ? null : isUsable.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddAccount() {
        return addAccount;
    }

    public void setAddAccount(String addAccount) {
        this.addAccount = addAccount == null ? null : addAccount.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyAccount() {
        return modifyAccount;
    }

    public void setModifyAccount(String modifyAccount) {
        this.modifyAccount = modifyAccount == null ? null : modifyAccount.trim();
    }
}