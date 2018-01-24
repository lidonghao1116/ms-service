package com.jiacer.modules.mybatis.model;

import java.util.Date;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class Partners implements ModelSerializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：partners
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：partners
     * 字段：partner_code
     * 注释：合作商编号
     *
     * @mbggenerated
     */
    private String partnerCode;

    /**
     * 表：partners
     * 字段：partner_type
     * 注释：合作商类型
     *
     * @mbggenerated
     */
    private String partnerType;

    /**
     * 表：partners
     * 字段：partner_name
     * 注释：合作商名称
     *
     * @mbggenerated
     */
    private String partnerName;

    /**
     * 表：partners
     * 字段：zip_code
     * 注释：邮编
     *
     * @mbggenerated
     */
    private String zipCode;

    /**
     * 表：partners
     * 字段：province
     * 注释：所属省
     *
     * @mbggenerated
     */
    private String province;

    /**
     * 表：partners
     * 字段：city
     * 注释：所属市
     *
     * @mbggenerated
     */
    private String city;

    /**
     * 表：partners
     * 字段：county
     * 注释：所属区县
     *
     * @mbggenerated
     */
    private String county;

    /**
     * 表：partners
     * 字段：address
     * 注释：地址
     *
     * @mbggenerated
     */
    private String address;

    /**
     * 表：partners
     * 字段：contacts
     * 注释：联系人
     *
     * @mbggenerated
     */
    private String contacts;

    /**
     * 表：partners
     * 字段：contact_phone
     * 注释：联系电话
     *
     * @mbggenerated
     */
    private String contactPhone;

    /**
     * 表：partners
     * 字段：store_phone
     * 注释：门店电话
     *
     * @mbggenerated
     */
    private String storePhone;

    /**
     * 表：partners
     * 字段：brokerage_id
     * 注释：提成方案
     *
     * @mbggenerated
     */
    private Integer brokerageId;

    /**
     * 表：partners
     * 字段：is_usable
     * 注释：是否可用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：partners
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：partners
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：partners
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：partners
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

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode == null ? null : partnerCode.trim();
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType == null ? null : partnerType.trim();
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone == null ? null : storePhone.trim();
    }

    public Integer getBrokerageId() {
        return brokerageId;
    }

    public void setBrokerageId(Integer brokerageId) {
        this.brokerageId = brokerageId;
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