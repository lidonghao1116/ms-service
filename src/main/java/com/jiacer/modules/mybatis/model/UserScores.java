package com.jiacer.modules.mybatis.model;

import java.math.BigDecimal;

public class UserScores extends UserScoresKey {
	private static final long serialVersionUID = 1L;

	/**
     * 表：user_scores
     * 字段：order_no
     * 注释：所属订单
     *
     * @mbggenerated
     */
    private BigDecimal orderNo;

    /**
     * 表：user_scores
     * 字段：theory_scores
     * 注释：理论成绩
     *
     * @mbggenerated
     */
    private BigDecimal theoryScores;

    /**
     * 表：user_scores
     * 字段：po_scores
     * 注释：实操成绩
     *
     * @mbggenerated
     */
    private BigDecimal poScores;

    /**
     * 表：user_scores
     * 字段：exam_result
     * 注释：考试结果
     *
     * @mbggenerated
     */
    private String examResult;

    /**
     * 表：user_scores
     * 字段：deal_result
     * 注释：处理结果
     *
     * @mbggenerated
     */
    private String dealResult;

    /**
     * 表：user_scores
     * 字段：certificate_no
     * 注释：证书编号
     *
     * @mbggenerated
     */
    private String certificateNo;

    /**
     * 表：user_scores
     * 字段：makeup_exam_free
     * 注释：补考费
     *
     * @mbggenerated
     */
    private BigDecimal makeupExamFree;

    public BigDecimal getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(BigDecimal orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTheoryScores() {
        return theoryScores;
    }

    public void setTheoryScores(BigDecimal theoryScores) {
        this.theoryScores = theoryScores;
    }

    public BigDecimal getPoScores() {
        return poScores;
    }

    public void setPoScores(BigDecimal poScores) {
        this.poScores = poScores;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult == null ? null : examResult.trim();
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult == null ? null : dealResult.trim();
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo == null ? null : certificateNo.trim();
    }

    public BigDecimal getMakeupExamFree() {
        return makeupExamFree;
    }

    public void setMakeupExamFree(BigDecimal makeupExamFree) {
        this.makeupExamFree = makeupExamFree;
    }
}