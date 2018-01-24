package com.jiacer.modules.mybatis.model;

import java.math.BigDecimal;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class BrokerageRules implements ModelSerializable{
	private static final long serialVersionUID = 1L;

	/**
     * 表：brokerage_rules
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：brokerage_rules
     * 字段：package_id
     * 注释：产品id
     *
     * @mbggenerated
     */
    private Integer packageId;

    /**
     * 表：brokerage_rules
     * 字段：bonus_type
     * 注释：提成方式
     *
     * @mbggenerated
     */
    private String bonusType;

    /**
     * 表：brokerage_rules
     * 字段：scheme_id
     * 注释：提成方案id
     *
     * @mbggenerated
     */
    private Integer schemeId;

    /**
     * 表：brokerage_rules
     * 字段：bonus_amount
     * 注释：提成金额或比例
     *
     * @mbggenerated
     */
    private BigDecimal bonusAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType == null ? null : bonusType.trim();
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }
}