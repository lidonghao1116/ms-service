package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.clientInterface.model.FinanceFlowInfo;
import com.jiacer.modules.clientInterface.model.SwiftpassCallbackInfo;

/**
 * Created by gaoyan on 11/07/2017.
 */
public interface FinanceFlowService {

    void addFinanceFlow(FinanceFlowInfo info);

    void modifyFinanceFlow(FinanceFlowInfo info);

    FinanceFlowInfo findByOutTradeNo(String outTradeNo);

    Object processSwift(SwiftpassCallbackInfo sp);
}
