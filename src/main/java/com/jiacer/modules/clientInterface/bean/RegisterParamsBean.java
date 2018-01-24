package com.jiacer.modules.clientInterface.bean;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: RegisterParamsBean 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月16日 上午11:09:12 
*  
*/
public class RegisterParamsBean implements ModelSerializable{
	
	private static final long serialVersionUID = 1L;

	private String userName;//用户姓名
	
	private String mobile;//手机号码

	private String wechatNick; //微信昵称
	private String wechatImage;//微信头像
	private String openId; //wx_openId
	private String sex;


	private String inviterCode;//邀请码
	private String isInvite;

	public String getIsInvite() {
		return isInvite;
	}

	public void setIsInvite(String isInvite) {
		this.isInvite = isInvite;
	}

	public String getInviterCode() {
		return inviterCode;
	}

	public void setInviterCode(String inviterCode) {
		this.inviterCode = inviterCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWechatNick() {
		return wechatNick;
	}

	public void setWechatNick(String wechatNick) {
		this.wechatNick = wechatNick;
	}

	public String getWechatImage() {
		return wechatImage;
	}

	public void setWechatImage(String wechatImage) {
		this.wechatImage = wechatImage;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
