package com.jiacer.modules.common.page.vo;

import com.jiacer.modules.clientInterface.model.RedpackExtendInfo;
import com.jiacer.modules.common.page.Page;

public class RedpackVo extends Page<RedpackExtendInfo> {


    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
