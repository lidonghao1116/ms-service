package com.jiacer.modules.wechat.bean;

public class OAuthTokenBean {
	private String accessToken;
	private int expiresIn;
	private String refreshToken;
	private String openId;
	private String scope;
	private String unionId;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	@Override
	public String toString() {
		return "OAuthTokenBean [accessToken=" + accessToken + ", expiresIn="
				+ expiresIn + ", refreshToken=" + refreshToken + ", openId="
				+ openId + ", scope=" + scope + ", unionId=" + unionId + "]";
	}
	
}
