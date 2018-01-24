package com.jiacer.modules.mybatis.model;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class Areas implements ModelSerializable{
	private static final long serialVersionUID = 1L;

	/**
     * 表：areas
     * 字段：AREA_CODE
     * 注释：
     *
     * @mbggenerated
     */
    private String areaCode;

    /**
     * 表：areas
     * 字段：AREA_NAME
     * 注释：
     *
     * @mbggenerated
     */
    private String areaName;

    /**
     * 表：areas
     * 字段：PARENT_CODE
     * 注释：
     *
     * @mbggenerated
     */
    private String parentCode;

    /**
     * 表：areas
     * 字段：AREA_LEVEL
     * 注释：
     *
     * @mbggenerated
     */
    private String areaLevel;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel == null ? null : areaLevel.trim();
    }
}