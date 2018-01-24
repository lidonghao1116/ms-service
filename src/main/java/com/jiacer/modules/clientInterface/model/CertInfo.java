package com.jiacer.modules.clientInterface.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 证书
 * Created by gaoyan on 14/07/2017.
 */
public class CertInfo implements Serializable {

    //工种名称
    private String workType;
    //鉴定等级
    private String authenticateGrade;
    //理论成绩
    private Double theoryScores;
    //技能成绩
    private Double poScores;
    //综合成绩
    private Double comprehensiveScores;
    //能力成绩
    private Double abilityScores;
    //评定结果
    private String dealResult;
    //考试结果
    private String examResult;
    //证书编号
    private String certificateNo;
    //发证日期
    private Date   issuingDate;
    //发证机构
    private String certAuthorityId;
    //发证机构名称
    private String certOrgName;
    //证书名称
    private String certName;

    private String gradeName;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public Date getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(Date issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getAuthenticateGrade() {
        return authenticateGrade;
    }

    public void setAuthenticateGrade(String authenticateGrade) {
        this.authenticateGrade = authenticateGrade;
    }

    public Double getTheoryScores() {
        return theoryScores;
    }

    public void setTheoryScores(Double theoryScores) {
        this.theoryScores = theoryScores;
    }

    public Double getPoScores() {
        return poScores;
    }

    public void setPoScores(Double poScores) {
        this.poScores = poScores;
    }

    public Double getComprehensiveScores() {
        return comprehensiveScores;
    }

    public void setComprehensiveScores(Double comprehensiveScores) {
        this.comprehensiveScores = comprehensiveScores;
    }

    public Double getAbilityScores() {
        return abilityScores;
    }

    public void setAbilityScores(Double abilityScores) {
        this.abilityScores = abilityScores;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCertAuthorityId() {
        return certAuthorityId;
    }

    public void setCertAuthorityId(String certAuthorityId) {
        this.certAuthorityId = certAuthorityId;
    }

    public String getCertOrgName() {
        return certOrgName;
    }

    public void setCertOrgName(String certOrgName) {
        this.certOrgName = certOrgName;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }
}
