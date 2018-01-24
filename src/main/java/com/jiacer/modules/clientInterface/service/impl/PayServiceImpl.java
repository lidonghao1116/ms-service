package com.jiacer.modules.clientInterface.service.impl;

import com.jiacer.modules.clientInterface.model.PayInfo;
import com.jiacer.modules.clientInterface.service.PayService;
import com.jiacer.modules.mybatis.dao.PayInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gaoyan on 11/07/2017.
 */
public class PayServiceImpl implements PayService {

    @Autowired
    private PayInfoMapper dao;

    @Override
    public PayInfo getPayInfoByNo(String outTradeNo) {
        return dao.getById(outTradeNo);
    }

    @Override
    public void submitPayStatus(PayInfo payInfo) {
        PayInfo p = new PayInfo();
        p.setPayNo(payInfo.getPayNo());
        p.setPayStatus(payInfo.getPayStatus());
        p.setCallbackInfo(payInfo.getCallbackInfo());
        p.setCallbackTime(payInfo.getCallbackTime());
        dao.update(p);
    }
}
