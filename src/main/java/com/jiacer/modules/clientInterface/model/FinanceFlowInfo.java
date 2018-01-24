package com.jiacer.modules.clientInterface.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 财务流水
 *
 *  `finance_no` varchar(20) NOT NULL COMMENT '流水号 主键',
    `user_id` int not null comment '用户id',
    `out_trade_no`  varchar(20) not null comment '交易单id（支付单号／红包编号）',
    `amount`  decimal(8,2) not null comment '流水金额',
    `flow_type` varchar(10) not null comment '流水类型（交易／红包／转账／退款／尾款）',
    `trade_channel` varchar(100) comment '交易渠道（家策微课堂／家策好服务／家策公众号）',
    `pay_type` varchar(10) not null comment '支付方式（微信app，微信扫码，支付宝扫码，现金）',
    `direction` char(2) not null comment '收入0/支出1',
    `status` 	  char(2) not null comment '状态1有效／0无效',
    `remark` 	  char(2) not null comment '备注',
    `add_time` timestamp not null comment '流水时间',
    `add_account` varchar(20) null comment '添加人',
    `modify_time` timestamp null comment '修改时间',
    `modify_account` timestamp null comment '修改操作人',
     PRIMARY KEY (`finance_no`)
 ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='资金流水表';

 * Created by gaoyan on 05/07/2017.
 */
public class FinanceFlowInfo implements Serializable {

    private String financeNo;  //资金流水号
    private Integer userId;     //用户ID
    private String outTradeNo;    //支付单号／红包编号（根据流水类型）
    private String payNo;       //支付单ID
    private Double amount;        //金额
    private String flowType;      //流水类型（100:订单支付，200:收取红包）
    private String tradeChannel;   //支付方式
    private String originChannel;  //交易渠道
    private String direction;      //资金流向（1支出／0收入）
    private String payStatus;        //支付状态
    private String status;        //状态(1有效／0无效)
    private String remark;        //备注
    private Date   addTime;
    private Date   modifyTime;
    private String addAccount;
    private String modifyAccount;

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getFinanceNo() {
        return financeNo;
    }

    public void setFinanceNo(String financeNo) {
        this.financeNo = financeNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getTradeChannel() {
        return tradeChannel;
    }

    public void setTradeChannel(String tradeChannel) {
        this.tradeChannel = tradeChannel;
    }


    public String getOriginChannel() {
        return originChannel;
    }

    public void setOriginChannel(String originChannel) {
        this.originChannel = originChannel;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getAddAccount() {
        return addAccount;
    }

    public void setAddAccount(String addAccount) {
        this.addAccount = addAccount;
    }

    public String getModifyAccount() {
        return modifyAccount;
    }

    public void setModifyAccount(String modifyAccount) {
        this.modifyAccount = modifyAccount;
    }
}
