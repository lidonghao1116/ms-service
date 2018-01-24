package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class LoginRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/**
     * 表：login_record
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：login_record
     * 字段：cellphone
     * 注释：手机号
     *
     * @mbggenerated
     */
    private String cellphone;

    /**
     * 表：login_record
     * 字段：login_time
     * 注释：登陆时间
     *
     * @mbggenerated
     */
    private Date loginTime;

    /**
     * 表：login_record
     * 字段：login_ip
     * 注释：登陆ip
     *
     * @mbggenerated
     */
    private String loginIp;

    /**
     * 表：login_record
     * 字段：is_succeed
     * 注释：是否成功
     *
     * @mbggenerated
     */
    private String isSucceed;

    /**
     * 表：login_record
     * 字段：failed_reason
     * 注释：失败原因
     *
     * @mbggenerated
     */
    private String failedReason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(String isSucceed) {
        this.isSucceed = isSucceed == null ? null : isSucceed.trim();
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason == null ? null : failedReason.trim();
    }
}