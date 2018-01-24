package com.jiacer.modules.mybatis.model;

import java.io.Serializable;

public class TypeChapter implements Serializable{
 
	private static final long serialVersionUID = 1L;

	/**
     * 表：type_chapter
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：type_chapter
     * 字段：type_id
     * 注释：分类id
     *
     * @mbggenerated
     */
    private Integer typeId;

    /**
     * 表：type_chapter
     * 字段：chapter_code
     * 注释：章节代码
     *
     * @mbggenerated
     */
    private String chapterCode;

    /**
     * 表：type_chapter
     * 字段：chapter_name
     * 注释：章节名称
     *
     * @mbggenerated
     */
    private String chapterName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getChapterCode() {
        return chapterCode;
    }

    public void setChapterCode(String chapterCode) {
        this.chapterCode = chapterCode == null ? null : chapterCode.trim();
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }
}