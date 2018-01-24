package com.jiacer.modules.clientInterface.common.conts;

/**
 * 序列号类型
 * Created by gaoyan on 29/06/2017.
 */
public enum SequeConst {

    ORDER("10", "order"),
    PAY_INFO("12", "pay_info"),
    FINANCE_FLOW("13", "finance_flow"),
    REDPACK("14", "redpack");

    private String code;
    private String type;

    private SequeConst(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public static SequeConst get(String code) {
        for (SequeConst pair : values()) {
            if (pair.code.equals(code)) {
                return pair;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
