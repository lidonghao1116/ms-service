package com.jiacer.modules.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class CoursePackage implements ModelSerializable{
   
	private static final long serialVersionUID = 1L;

	 /**
     * 表：course_package
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：course_package
     * 字段：package_name
     * 注释：课程包名
     *
     * @mbggenerated
     */
    private String packageName;

    /**
     * 表：course_package
     * 字段：remarks
     * 注释：备注
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * 表：course_package
     * 字段：price
     * 注释：价格
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * 表：course_package
     * 字段：original_price
     * 注释：原价
     *
     * @mbggenerated
     */
    private BigDecimal originalPrice;

    /**
     * 表：course_package
     * 字段：is_discount
     * 注释：促销标签
     *
     * @mbggenerated
     */
    private String isDiscount;

    /**
     * 表：course_package
     * 字段：is_usable
     * 注释：是否可用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：course_package
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：course_package
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：course_package
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：course_package
     * 字段：modify_account
     * 注释：修改人
     *
     * @mbggenerated
     */
    private String modifyAccount;

    /**
     * 表：course_package
     * 字段：status
     * 注释：状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 表：course_package
     * 字段：summary
     * 注释：简介
     *
     * @mbggenerated
     */
    private String summary;

    /**
     * 表：course_package
     * 字段：apply_courses
     * 注释：报考课程,以逗号分隔
     *
     * @mbggenerated
     */
    private String applyCourses;
    
    /**
     * 表：course_package
     * 字段：sort_no
     * 注释：排序
     *
     * @mbggenerated
     */
    private Integer sortNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(String isDiscount) {
        this.isDiscount = isDiscount == null ? null : isDiscount.trim();
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getApplyCourses() {
        return applyCourses;
    }

    public void setApplyCourses(String applyCourses) {
        this.applyCourses = applyCourses == null ? null : applyCourses.trim();
    }
    
    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}