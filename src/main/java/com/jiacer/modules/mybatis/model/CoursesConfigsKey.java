package com.jiacer.modules.mybatis.model;

import java.io.Serializable;

public class CoursesConfigsKey implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/**
     * 表：courses_configs
     * 字段：course_id
     * 注释：课程id
     *
     * @mbggenerated
     */
    private Integer courseId;

    /**
     * 表：courses_configs
     * 字段：configs_code
     * 注释：配置类型code SP：视频 DT:答题 ZL:资料
     *
     * @mbggenerated
     */
    private String configsCode;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getConfigsCode() {
        return configsCode;
    }

    public void setConfigsCode(String configsCode) {
        this.configsCode = configsCode == null ? null : configsCode.trim();
    }
}