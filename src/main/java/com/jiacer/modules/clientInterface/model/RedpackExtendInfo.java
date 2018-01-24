package com.jiacer.modules.clientInterface.model;

import java.util.Date;

/**
 * 红包扩展信息
 * Created by gaoyan on 11/07/2017.
 */
public class RedpackExtendInfo extends RedpackInfo {

    private String orderNo;
    private Integer userId;
    private String userName;
    private Integer inviterId;
    private String inviterName;
    private String inviterOpenid;
    private String inviterMobile;
    private String payStatus;
    private String status;
    private String isUsable;


    private String requestInfo;
    private String responseInfo;
    private Date   requestTime;
    private Date   responseTime;
    private String failCode;
    private String failReason;
    private String isNeedRetry;
    private Integer retryTimes;
    private String isReminded;

    private String redpackWxNo;

    public String getRedpackWxNo() {
        return redpackWxNo;
    }

    public void setRedpackWxNo(String redpackWxNo) {
        this.redpackWxNo = redpackWxNo;
    }

    public RedpackExtendInfo(){super();}
    public RedpackExtendInfo(RedpackInfo red){
        super(red);
    }

    public String getInviterMobile() {
        return inviterMobile;
    }

    public void setInviterMobile(String inviterMobile) {
        this.inviterMobile = inviterMobile;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getInviterId() {
        return inviterId;
    }

    public void setInviterId(Integer inviterId) {
        this.inviterId = inviterId;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public String getInviterOpenid() {
        return inviterOpenid;
    }

    public void setInviterOpenid(String inviterOpenid) {
        this.inviterOpenid = inviterOpenid;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable;
    }

    public String getRedpackNo(){
        return getMchBillno();
    }
    public void setRedpackNo(String redpackNo){
        setMchBillno(redpackNo);
    }

    public Double getStandAmount(){
        return Double.valueOf(getTotalAmount())/100;
    }
    public void setStandAmount(Double standAmount){
        setTotalAmount(standAmount * 100 + "");
    }

    public String getIsReminded() {
        return isReminded;
    }

    public void setIsReminded(String isReminded) {
        this.isReminded = isReminded;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getFailCode() {
        return failCode;
    }

    public void setFailCode(String failCode) {
        this.failCode = failCode;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getIsNeedRetry() {
        return isNeedRetry;
    }

    public void setIsNeedRetry(String isNeedRetry) {
        this.isNeedRetry = isNeedRetry;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }



}
