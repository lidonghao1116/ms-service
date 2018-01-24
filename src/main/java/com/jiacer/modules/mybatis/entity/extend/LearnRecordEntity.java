package com.jiacer.modules.mybatis.entity.extend;

import java.math.BigDecimal;
import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: LearnRecordEntity 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月7日 下午3:50:49 
*  
*/
public class LearnRecordEntity implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private Integer userId;//用户id

	private Integer answersNum;//答题次数
	
	private BigDecimal scores;//得分
	
	private Integer couresId;//课程id

	private Integer courseInfoId; //在线课程ID

	public Integer getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(Integer courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAnswersNum() {
		return answersNum;
	}

	public void setAnswersNum(Integer answersNum) {
		this.answersNum = answersNum;
	}

	public BigDecimal getScores() {
		return scores;
	}

	public void setScores(BigDecimal scores) {
		this.scores = scores;
	}

	public Integer getCouresId() {
		return couresId;
	}

	public void setCouresId(Integer couresId) {
		this.couresId = couresId;
	}
}
