package com.jiacer.modules.clientInterface.jsonResult;

import com.jiacer.modules.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口返回红包信息
 */
public class RedpackJsonData implements Serializable{

    private Double  amount;
    private Date    date;
    private String  courseName;
    private String  userName;


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateView() {
        Integer send = Integer.valueOf(DateUtils.formatDate(getDate(), "yyyyMMdd"));
        Integer today = Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd"));
        if(send.equals(today)){
            return "今天";
        }else if(today - send == 1){
            return "昨天";
        }
        return DateUtils.formatDate(getDate(), "yyyy-MM-dd");
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
