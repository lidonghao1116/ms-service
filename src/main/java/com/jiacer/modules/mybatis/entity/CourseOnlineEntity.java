package com.jiacer.modules.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 在线课程
 * Created by gaoyan on 30/06/2017.
 */
public class CourseOnlineEntity implements Serializable{

    private Integer courseId;       //课程ID
    private String courseName;      //课程名称
    private String image;          //图片地址
    private String banner;          //banner地址
    private String courseType;      //课程类型
    private Double price;           //课程价格
    private String fitService;      //使用工种
    private Integer sortNo;         //排序号
    private String summary;         //简介
    private Date creatTime;         //创建时间
    private String reSign;          //推荐标识
    private String status;          //状态
    private String courseTime;      //课程规定答题时间

    private String judgeCount;   //判断题数量
    private String judgeScore;   //判断题分数
    private String singleCount;  //单选题数量
    private String singleScore;//单选题分数
    private String multiCount;//多选题数量
    private String multiScore;//多选题分数

    private Integer isOwn;  //是否已购买

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJudgeCount() {
        return judgeCount;
    }

    public void setJudgeCount(String judgeCount) {
        this.judgeCount = judgeCount;
    }

    public String getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(String judgeScore) {
        this.judgeScore = judgeScore;
    }

    public String getSingleCount() {
        return singleCount;
    }

    public void setSingleCount(String singleCount) {
        this.singleCount = singleCount;
    }

    public String getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(String singleScore) {
        this.singleScore = singleScore;
    }

    public String getMultiCount() {
        return multiCount;
    }

    public void setMultiCount(String multiCount) {
        this.multiCount = multiCount;
    }

    public String getMultiScore() {
        return multiScore;
    }

    public void setMultiScore(String multiScore) {
        this.multiScore = multiScore;
    }

    public Integer getIsOwn() {
        return isOwn;
    }

    public void setIsOwn(Integer isOwn) {
        this.isOwn = isOwn;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFitService() {
        return fitService;
    }

    public void setFitService(String fitService) {
        this.fitService = fitService;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getReSign() {
        return reSign;
    }

    public void setReSign(String reSign) {
        this.reSign = reSign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

}
