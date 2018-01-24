package com.jiacer.modules.mybatis.model;

import com.jiacer.modules.common.persistence.ModelSerializable;

/**
 * 课程时间模版
 * Created by gaoyan on 18/07/2017.
 */
public class ClassTimesTemplate implements ModelSerializable {

    private String   templateId;
    private String   templateName;

    private String   openCycle;
    private String   beginTime;
    private String   endTime;
    private String   templateType;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getOpenCycle() {
        return openCycle;
    }

    public void setOpenCycle(String openCycle) {
        this.openCycle = openCycle;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }
}
