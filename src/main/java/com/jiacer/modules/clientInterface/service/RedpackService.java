package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.clientInterface.common.conts.RedpackConst;
import com.jiacer.modules.clientInterface.jsonResult.RedpackJsonData;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.clientInterface.model.RedpackExtendInfo;
import com.jiacer.modules.clientInterface.model.RedpackInfo;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.page.vo.RedpackVo;

import java.util.List;
import java.util.Map;

/**
 * 红包业务
 * Created by gaoyan on 30/06/2017.
 */
public interface RedpackService {

    /**
     * 发送红包
     */
    void send(OnlineOrderInfo onlineOrderInfo);

    /**
     * 邀请码是否可用
     * @param inviteCode
     * @return
     */
    boolean isAvilabilityForInviterCode(String inviteCode);


    List<RedpackJsonData> getListByUserId(RedpackVo vo);

    Map<String,Object> total(Integer userId);
}
