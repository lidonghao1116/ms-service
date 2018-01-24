package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class WechatAuthrizeInfo implements Serializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：wechat_authrize_info
     * 字段：OPEN_ID
     * 注释：微信OPENID
     *
     * @mbggenerated
     */
    private String openId;

    /**
     * 表：wechat_authrize_info
     * 字段：NICK
     * 注释：昵称
     *
     * @mbggenerated
     */
    private String nick;

    /**
     * 表：wechat_authrize_info
     * 字段：HEAD_IMG_URL
     * 注释：头像链接
     *
     * @mbggenerated
     */
    private String headImgUrl;

    /**
     * 表：wechat_authrize_info
     * 字段：SEX
     * 注释：性别，值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * 表：wechat_authrize_info
     * 字段：ACCESS_TOKEN
     * 注释：调用凭证
     *
     * @mbggenerated
     */
    private String accessToken;

    /**
     * 表：wechat_authrize_info
     * 字段：REFRESH_TOKEN
     * 注释：刷新凭证
     *
     * @mbggenerated
     */
    private String refreshToken;

    /**
     * 表：wechat_authrize_info
     * 字段：EXPIRING_TIME
     * 注释：调用凭证过期时间
     *
     * @mbggenerated
     */
    private Date expiringTime;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    public Date getExpiringTime() {
        return expiringTime;
    }

    public void setExpiringTime(Date expiringTime) {
        this.expiringTime = expiringTime;
    }
}