package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.clientInterface.model.PayInfo;

/**
 * Created by gaoyan on 11/07/2017.
 */
public interface PayService {

    PayInfo getPayInfoByNo(String outTradeNo);

    void submitPayStatus(PayInfo payInfo);
}
