package com.jiacer.modules.clientInterface.jsonResult;

import java.math.BigDecimal;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: LearnRecordsJsonData 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午2:40:14 
*  
*/
public class LearnRecordsJsonData implements ModelSerializable{
	private static final long serialVersionUID = 1L;
	
	private String courseName;//课程名称
	
	private BigDecimal scores=BigDecimal.ZERO;//最高分
	
	private int count=0;//答题次数

	private String school;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public BigDecimal getScores() {
		return scores;
	}

	public void setScores(BigDecimal scores) {
		this.scores = scores;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
