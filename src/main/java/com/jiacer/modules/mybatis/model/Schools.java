package com.jiacer.modules.mybatis.model;

import java.util.Date;

import com.jiacer.modules.clientInterface.common.CityDictUtils;
import com.jiacer.modules.common.persistence.ModelSerializable;

public class Schools implements ModelSerializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：shools
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：shools
     * 字段：shool_name
     * 注释：学校名称
     *
     * @mbggenerated
     */
    private String schoolName;
    private String schoolPhone;

    private String province;
    private String city;
    private String area;
    /**
     * 表：shools
     * 字段：shool_address
     * 注释：学校地址
     *
     * @mbggenerated
     */
    private String schoolAddress;

    /**
     * 表：shools
     * 字段：contacts
     * 注释：联系人
     *
     * @mbggenerated
     */
    private String contacts;

    /**
     * 表：shools
     * 字段：contact_phone
     * 注释：联系电话
     *
     * @mbggenerated
     */
    private String contactPhone;

    /**
     * 表：shools
     * 字段：remarks
     * 注释：备注
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * 表：shools
     * 字段：apply_courses
     * 注释：报考课程以逗号分隔
     *
     * @mbggenerated
     */
    private String applyCourses;

    /**
     * 表：shools
     * 字段：is_usable
     * 注释：是否可用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：shools
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：shools
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：shools
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：shools
     * 字段：modify_account
     * 注释：修改人
     *
     * @mbggenerated
     */
    private String modifyAccount;

    private String logoUrl;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress == null ? null : schoolAddress.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getApplyCourses() {
        return applyCourses;
    }

    public void setApplyCourses(String applyCourses) {
        this.applyCourses = applyCourses == null ? null : applyCourses.trim();
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSchoolPhone() {
        return schoolPhone;
    }

    public void setSchoolPhone(String schoolPhone) {
        this.schoolPhone = schoolPhone;
    }
}