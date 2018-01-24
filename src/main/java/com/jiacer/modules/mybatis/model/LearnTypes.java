package com.jiacer.modules.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class LearnTypes implements ModelSerializable{
  
	private static final long serialVersionUID = 1L;

	/**
     * 表：learn_types
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：learn_types
     * 字段：type_name
     * 注释：
     *
     * @mbggenerated
     */
    private String typeName;

    /**
     * 表：learn_types
     * 字段：sort_no
     * 注释：
     *
     * @mbggenerated
     */
    private Integer sortNo;

    /**
     * 表：learn_types
     * 字段：is_usable
     * 注释：
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：learn_types
     * 字段：add_time
     * 注释：
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：learn_types
     * 字段：add_account
     * 注释：
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：learn_types
     * 字段：modify_time
     * 注释：
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：learn_types
     * 字段：modify_account
     * 注释：
     *
     * @mbggenerated
     */
    private String modifyAccount;

    /**
     * 表：learn_types
     * 字段：status
     * 注释：课程状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 表：learn_types
     * 字段：total_hours
     * 注释：总课时
     *
     * @mbggenerated
     */
    private Integer totalHours;

    /**
     * 表：learn_types
     * 字段：exam_type
     * 注释：考试形式
     *
     * @mbggenerated
     */
    private String examType;

    /**
     * 表：learn_types
     * 字段：remarks
     * 注释：备注
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * 表：learn_types
     * 字段：exam_fee
     * 注释：监考费
     *
     * @mbggenerated
     */
    private BigDecimal examFee;

    /**
     * 表：learn_types
     * 字段：certificate_fee
     * 注释：证书费
     *
     * @mbggenerated
     */
    private BigDecimal certificateFee;

    /**
     * 表：learn_types
     * 字段：other_fee
     * 注释：其他费
     *
     * @mbggenerated
     */
    private BigDecimal otherFee;

    /**
     * 表：learn_types
     * 字段：class_times
     * 注释：上课时间
     *
     * @mbggenerated
     */
    private List<ClassTimesTemplate> classTimes;
    
    /**
     * 表：learn_types
     * 字段：is_need_has_pf
     * 注释：是否上海交金
     *
     * @mbggenerated
     */
    private String isNeedHasPf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType == null ? null : examType.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public BigDecimal getExamFee() {
        return examFee;
    }

    public void setExamFee(BigDecimal examFee) {
        this.examFee = examFee;
    }

    public BigDecimal getCertificateFee() {
        return certificateFee;
    }

    public void setCertificateFee(BigDecimal certificateFee) {
        this.certificateFee = certificateFee;
    }

    public BigDecimal getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public List<ClassTimesTemplate> getClassTimes() {
        return classTimes;
    }

    public void setClassTimes(List<ClassTimesTemplate> classTimes) {
        this.classTimes = classTimes;
    }

    public String getIsNeedHasPf() {
        return isNeedHasPf;
    }

    public void setIsNeedHasPf(String isNeedHasPf) {
        this.isNeedHasPf = isNeedHasPf == null ? null : isNeedHasPf.trim();
    }
}