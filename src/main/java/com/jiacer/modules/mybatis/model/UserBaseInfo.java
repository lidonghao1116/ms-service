package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class UserBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 表：user_base_info 字段：id 注释：主键
	 *
	 * @mbggenerated
	 */
	private Integer id;

	/**
	 * 表：user_base_info 字段：mobile 注释：手机号
	 *
	 * @mbggenerated
	 */
	private String mobile;

	/**
	 * 表：user_base_info 字段：salt 注释：加密盐值
	 *
	 * @mbggenerated
	 */
	private String salt;

	/**
	 * 表：user_base_info 字段：login_password 注释：登陆密码
	 *
	 * @mbggenerated
	 */
	private String loginPassword;

	/**
	 * 表：user_base_info 字段：user_status 注释：用户状态 00 正常
	 *
	 * @mbggenerated
	 */
	private String userStatus;

	/**
	 * 表：user_base_info 字段：wechat_nick 注释：微信昵称
	 *
	 * @mbggenerated
	 */
	private String wechatNick;

	/**
	 * 表：user_base_info 字段：wechat_image 注释：微信头像
	 *
	 * @mbggenerated
	 */
	private String wechatImage;

	/**
	 * 表：user_base_info 字段：openId 注释：微信openId
	 *
	 * @mbggenerated
	 */
	private String openId;

	/**
	 * 表：user_base_info 字段：register_time 注释：注册时间
	 *
	 * @mbggenerated
	 */
	private Date registerTime;

	/**
	 * 表：user_base_info 字段：pwd_try_count 注释：密码尝试次数
	 *
	 * @mbggenerated
	 */
	private Integer pwdTryCount;

	/**
	 * 表：user_base_info 字段：is_locked 注释：是否锁定 0 未锁定 1锁定
	 *
	 * @mbggenerated
	 */
	private String isLocked;

	/**
	 * 表：user_base_info 字段：lock_time 注释：锁定时间
	 *
	 * @mbggenerated
	 */
	private Date lockTime;

	/**
	 * 表：user_base_info 字段：last_login_time 注释：最后登陆时间
	 *
	 * @mbggenerated
	 */
	private Date lastLoginTime;

	/**
	 * 表：user_base_info 字段：add_time 注释：添加时间
	 *
	 * @mbggenerated
	 */
	private Date addTime;

	/**
	 * 表：user_base_info 字段：modify_time 注释：修改时间
	 *
	 * @mbggenerated
	 */
	private Date modifyTime;

	/**
	 * 表：user_base_info 字段：first_wrong_time 注释：第一次出错时间
	 *
	 * @mbggenerated
	 */
	private Date firstWrongTime;

	/**
	 * 表：user_base_info 字段：cert_no 注释：证件号
	 *
	 * @mbggenerated
	 */
	private String certNo;

	/**
	 * 表：user_base_info 字段：cert_type 注释：证件类型
	 *
	 * @mbggenerated
	 */
	private String certType;

	/**
	 * 表：user_base_info 字段：owned_store 注释：所属门店
	 *
	 * @mbggenerated
	 */
	private String ownedStore;

	/**
	 * 表：user_base_info 字段：owned_teacher 注释：所属老师
	 *
	 * @mbggenerated
	 */
	private String ownedTeacher;

	/**
	 * 表：user_base_info 字段：user_name 注释：用户姓名
	 *
	 * @mbggenerated
	 */
	private String userName;

	/**
	 * 表：user_base_info 字段：user_type 注释：用户类型：00会员用户 01：学校系统账号
	 *
	 * @mbggenerated
	 */
	private String userType;

	/**
	 * 表：user_base_info 字段：invite_code 注释：邀请码
	 */
	private String inviteCode;

	private String isInvite;

	public String getIsInvite() {
		return isInvite;
	}

	public void setIsInvite(String isInvite) {
		this.isInvite = isInvite;
	}

	private String gender; //性别

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword == null ? null : loginPassword.trim();
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus == null ? null : userStatus.trim();
	}

	public String getWechatNick() {
		return wechatNick;
	}

	public void setWechatNick(String wechatNick) {
		this.wechatNick = wechatNick == null ? null : wechatNick.trim();
	}

	public String getWechatImage() {
		return wechatImage;
	}

	public void setWechatImage(String wechatImage) {
		this.wechatImage = wechatImage == null ? null : wechatImage.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getPwdTryCount() {
		return pwdTryCount;
	}

	public void setPwdTryCount(Integer pwdTryCount) {
		this.pwdTryCount = pwdTryCount;
	}

	public String getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked == null ? null : isLocked.trim();
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getFirstWrongTime() {
		return firstWrongTime;
	}

	public void setFirstWrongTime(Date firstWrongTime) {
		this.firstWrongTime = firstWrongTime;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo == null ? null : certNo.trim();
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType == null ? null : certType.trim();
	}

	public String getOwnedStore() {
		return ownedStore;
	}

	public void setOwnedStore(String ownedStore) {
		this.ownedStore = ownedStore == null ? null : ownedStore.trim();
	}

	public String getOwnedTeacher() {
		return ownedTeacher;
	}

	public void setOwnedTeacher(String ownedTeacher) {
		this.ownedTeacher = ownedTeacher == null ? null : ownedTeacher.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType == null ? null : userType.trim();
	}
}