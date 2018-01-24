package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class Videos implements Serializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：videos
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 表：videos
     * 字段：video_name
     * 注释：
     *
     * @mbggenerated
     */
    private String videoName;

    /**
     * 表：videos
     * 字段：cover_photo
     * 注释：
     *
     * @mbggenerated
     */
    private String coverPhoto;

    /**
     * 表：videos
     * 字段：video_size
     * 注释：
     *
     * @mbggenerated
     */
    private Integer videoSize;

    /**
     * 表：videos
     * 字段：video_format
     * 注释：
     *
     * @mbggenerated
     */
    private String videoFormat;

    /**
     * 表：videos
     * 字段：video_url
     * 注释：
     *
     * @mbggenerated
     */
    private String videoUrl;

    /**
     * 表：videos
     * 字段：video_desc
     * 注释：
     *
     * @mbggenerated
     */
    private String videoDesc;

    /**
     * 表：videos
     * 字段：type_id
     * 注释：
     *
     * @mbggenerated
     */
    private Integer typeId;

    /**
     * 表：videos
     * 字段：is_usable
     * 注释：
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：videos
     * 字段：add_time
     * 注释：
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：videos
     * 字段：add_account
     * 注释：
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：videos
     * 字段：modify_time
     * 注释：
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：videos
     * 字段：modify_account
     * 注释：
     *
     * @mbggenerated
     */
    private String modifyAccount;

    /**
     * 表：videos
     * 字段：chapter_code
     * 注释：章节代码
     *
     * @mbggenerated
     */
    private String chapterCode;

    private Integer courseId;
    private Integer isGuestWatch;
    private Integer chapterNo;

    public Integer getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(Integer chapterNo) {
        this.chapterNo = chapterNo;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getIsGuestWatch() {
        return isGuestWatch;
    }

    public void setIsGuestWatch(Integer isGuestWatch) {
        this.isGuestWatch = isGuestWatch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto == null ? null : coverPhoto.trim();
    }

    public Integer getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(Integer videoSize) {
        this.videoSize = videoSize;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat == null ? null : videoFormat.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc == null ? null : videoDesc.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable == null ? null : isUsable.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddAccount() {
        return addAccount;
    }

    public void setAddAccount(String addAccount) {
        this.addAccount = addAccount == null ? null : addAccount.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyAccount() {
        return modifyAccount;
    }

    public void setModifyAccount(String modifyAccount) {
        this.modifyAccount = modifyAccount == null ? null : modifyAccount.trim();
    }

    public String getChapterCode() {
        return chapterCode;
    }

    public void setChapterCode(String chapterCode) {
        this.chapterCode = chapterCode == null ? null : chapterCode.trim();
    }
}