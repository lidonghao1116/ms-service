package com.jiacer.modules.wechat.bean;

import java.util.Arrays;

public class WechatUserinfoBean {
	private String openId;
	private String sex;
	private String province;
	private String city;
	private String country;
	private String headImgUrl;
	private String unionId;
	private Object[] privilege;
	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Object[] getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Object[] privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "WechatUserinfoBean [openId=" + openId + ", nickname=" + nickName + ", sex=" + sex + ", province="
				+ province + ", city=" + city + ", country=" + country + ", headImgUrl=" + headImgUrl + ", unionId="
				+ unionId + ", privilege=" + Arrays.toString(privilege) + "]";
	}
}
