package com.jiacer.modules.mybatis.model;

import java.util.Date;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class CfgParam implements ModelSerializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：cfg_param
     * 字段：ID
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：cfg_param
     * 字段：VERSION
     * 注释：版本
     *
     * @mbggenerated
     */
    private Integer version;

    /**
     * 表：cfg_param
     * 字段：SORT_NO
     * 注释：排序
     *
     * @mbggenerated
     */
    private Integer sortNo;

    /**
     * 表：cfg_param
     * 字段：TEXT
     * 注释：展示内容
     *
     * @mbggenerated
     */
    private String text;

    /**
     * 表：cfg_param
     * 字段：TYPE
     * 注释：类型
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 表：cfg_param
     * 字段：VALUE
     * 注释：code值
     *
     * @mbggenerated
     */
    private String value;

    /**
     * 表：cfg_param
     * 字段：FK_PARENT_PARAM_ID
     * 注释：父级
     *
     * @mbggenerated
     */
    private Integer fkParentParamId;

    /**
     * 表：cfg_param
     * 字段：is_usable
     * 注释：是否可用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：cfg_param
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：cfg_param
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：cfg_param
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：cfg_param
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getFkParentParamId() {
        return fkParentParamId;
    }

    public void setFkParentParamId(Integer fkParentParamId) {
        this.fkParentParamId = fkParentParamId;
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