package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.UserScores;

/** 
* @ClassName: UserScoresEntity 
* @Description: 学员成绩表
* @author 贺章鹏
* @date 2016年10月21日 下午4:45:12 
*  
*/
public class UserScoresEntity extends UserScores{

	private static final long serialVersionUID = 1L;
	
	private UserBaseInfoEntity userInfo;
	
	private UserExtendInfoEntity userExtend;

	

	public UserBaseInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfoEntity userInfo) {
		this.userInfo = userInfo;
	}

	public UserExtendInfoEntity getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtendInfoEntity userExtend) {
		this.userExtend = userExtend;
	}
	
}
