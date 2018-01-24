package com.jiacer.modules.mybatis.model;

public class CoursesConfigs extends CoursesConfigsKey {
    
	private static final long serialVersionUID = 1L;
	/**
     * 表：courses_configs
     * 字段：configs_name
     * 注释：配置类型名称
     *
     * @mbggenerated
     */
    private String configsName;

    public String getConfigsName() {
        return configsName;
    }

    public void setConfigsName(String configsName) {
        this.configsName = configsName == null ? null : configsName.trim();
    }
}