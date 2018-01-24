package com.jiacer.modules.mybatis.model;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class Constants implements ModelSerializable{
 
	private static final long serialVersionUID = 1L;

	/**
     * 表：constants
     * 字段：CONST_KEY
     * 注释：键值
     *
     * @mbggenerated
     */
    private String constKey;

    /**
     * 表：constants
     * 字段：CONST_VALUE
     * 注释：常量值
     *
     * @mbggenerated
     */
    private String constValue;

    /**
     * 表：constants
     * 字段：CONST_DESC
     * 注释：常量描述
     *
     * @mbggenerated
     */
    private String constDesc;

    public String getConstKey() {
        return constKey;
    }

    public void setConstKey(String constKey) {
        this.constKey = constKey == null ? null : constKey.trim();
    }

    public String getConstValue() {
        return constValue;
    }

    public void setConstValue(String constValue) {
        this.constValue = constValue == null ? null : constValue.trim();
    }

    public String getConstDesc() {
        return constDesc;
    }

    public void setConstDesc(String constDesc) {
        this.constDesc = constDesc == null ? null : constDesc.trim();
    }
}