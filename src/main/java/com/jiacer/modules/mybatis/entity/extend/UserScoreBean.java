package com.jiacer.modules.mybatis.entity.extend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户成绩详情
 * @author hzp
 *
 */
public class UserScoreBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer batchId;//答题批次
	
	private String userName;//用户姓名
	
	private String certNo;//身份证
	
	private String typeName;//科目名称
	
	private String useTime;//答题用时
	
	private BigDecimal score;//答题分数
	private String  startTime;

	private String isFinished;

	public String getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
}
