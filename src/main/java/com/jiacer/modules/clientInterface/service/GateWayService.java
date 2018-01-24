package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.clientInterface.model.GatewayPrepayInfo;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by gaoyan on 04/07/2017.
 */
public interface GateWayService {
    Object pay(OnlineOrderInfo onlineOrderInfo, GatewayPrepayInfo prepay) throws ServletException, IOException;

    Map<String, String> query(String trade) throws ServletException, IOException ;

    Map<String, String> refundQuery() throws ServletException, IOException ;

    Map<String, String> refund() throws ServletException, IOException ;
}
