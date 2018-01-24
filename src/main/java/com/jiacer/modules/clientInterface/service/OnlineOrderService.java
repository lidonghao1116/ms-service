package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;

/**
 * 在线课程订单／支付
 * Created by gaoyan on 04/07/2017.
 */
public interface OnlineOrderService {

    /**
     * 创建订单
     */
    ErrorCode apply(Integer courseId, Integer userId, String inviterCode, OnlineOrderInfo resultOrder);

    void update(OnlineOrderInfo orderInfo);

    OnlineOrderInfo getByNo(String orderNo);

    void submitPayStatus(OnlineOrderInfo orderInfo);
}
