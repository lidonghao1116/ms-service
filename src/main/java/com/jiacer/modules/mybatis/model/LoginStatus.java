package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class LoginStatus implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/**
     * 表：login_status
     * 字段：ID
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：login_status
     * 字段：USER_ID
     * 注释：用户ID
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 表：login_status
     * 字段：MOBILE
     * 注释：手机号
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * 表：login_status
     * 字段：SOURCE
     * 注释：来源 01微信
     *
     * @mbggenerated
     */
    private String source;

    /**
     * 表：login_status
     * 字段：OPEN_ID
     * 注释：微信openId
     *
     * @mbggenerated
     */
    private String openId;

    /**
     * 表：login_status
     * 字段：LOGIN_FLAG
     * 注释：是否登录状态 1：是 0：否
     *
     * @mbggenerated
     */
    private String loginFlag;

    /**
     * 表：login_status
     * 字段：LOGIN_TIME
     * 注释：登录时间
     *
     * @mbggenerated
     */
    private Date loginTime;

    /**
     * 表：login_status
     * 字段：LAST_ACTIVE_TIME
     * 注释：
     *
     * @mbggenerated
     */
    private Date lastActiveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag == null ? null : loginFlag.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
}