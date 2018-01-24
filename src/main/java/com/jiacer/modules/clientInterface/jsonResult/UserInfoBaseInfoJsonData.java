package com.jiacer.modules.clientInterface.jsonResult;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: UserInfoBaseInfoJsonData 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月16日 下午1:21:28 
*  
*/
public class UserInfoBaseInfoJsonData implements ModelSerializable {
private static final long serialVersionUID = 1L;

	private String headImg;
	private String nickname;
	private String username;
	private String certType;
	private String certNo;
	private String status;
	private String education;

	private String inviteCode;
	private String mobile;

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
