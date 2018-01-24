package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.common.GenerateSerialNumber;
import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.anno.Guest;
import com.jiacer.modules.clientInterface.common.conts.*;
import com.jiacer.modules.clientInterface.model.GatewayPrepayInfo;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.clientInterface.model.PayInfo;
import com.jiacer.modules.clientInterface.service.GateWayService;
import com.jiacer.modules.clientInterface.service.OnlineOrderService;
import com.jiacer.modules.clientInterface.service.PayService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付网关
 * 支持  微信／支付宝  扫码支付
 * Created by gaoyan on 17/07/2017.
 */
@RestController
@RequestMapping(value = "/api/gateway")
public class GatewayController {

    private static final Logger log = LoggerFactory.getLogger("paymentLogger");

    @Autowired
    private GateWayService gateWayService;

    @Autowired
    private OnlineOrderService onlineOrderService;

    @Autowired
    private GenerateSerialNumber generater;

    @Autowired
    private PayService payService;

    /**
     * 预支付, 获取二维码等信息
     *
     * @param userId
     * @param courseId
     * @param type     01支付宝，02微信
     * @return
     */
    //@Auth
    @RequestMapping(value = "/prepay", method = RequestMethod.GET)
    @Guest
    @ResponseBody
    public JsonResult prepay(Integer userId, Integer courseId, String type) {
        //1. 下单
        OnlineOrderInfo onlineOrderInfo = new OnlineOrderInfo();
        onlineOrderInfo.setOriginChannel(FinanceConst.OriginChannel.JIACER_ONLINE_SCHOOL_WEB.getCode());
        onlineOrderInfo.setTradeChannel(type);
        ErrorCode code = onlineOrderService.apply(courseId, userId, null, onlineOrderInfo);
        if (code != ErrorCode.SUCCESS) {
            log.error("订单生成失败。");
            return JsonResult.failure(code);
        }
        //支付
        try {
            //创建预支付请求
            GatewayPrepayInfo prepayInfo = null;
            if (OrderConst.TradeChannel.WECHATPAY.getCode().equals(type)) {
                prepayInfo = GatewayPrepayInfo.getWxQRPay(generater.take(SequeConst.PAY_INFO), onlineOrderInfo.getAmount());
                prepayInfo.setService(OrderConst.TradeChannel.WECHATPAY.getService());
            } else if (OrderConst.TradeChannel.ALIPAY.getCode().equals(type)) {
                prepayInfo = GatewayPrepayInfo.getAliQRPay(generater.take(SequeConst.PAY_INFO), onlineOrderInfo.getAmount());
                prepayInfo.setService(OrderConst.TradeChannel.ALIPAY.getService());
            }
            if (prepayInfo == null) {
                log.error(String.format("交易渠道不正确[01,02,03] = param [%s]", type));
                return JsonResult.failure(ErrorCode.GATEWAY_TYPE_ERROR);
            }
            prepayInfo.setBody(onlineOrderInfo.getOrderDesc());
            Object result = gateWayService.pay(onlineOrderInfo, prepayInfo);
            return JsonResult.success(result);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonResult.failure(ErrorCode.ORDER_BUY_FAILURE);
    }

    /**
     * 支付状态查询
     *
     * @return
     */
    @Guest
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult queryPayStatus(String tradeNo) {
        if (StringUtils.isBlank(tradeNo)) {
            return JsonResult.failure(ErrorCode.REQUEST_PARAM_EMPTY);
        }
        PayInfo pay = payService.getPayInfoByNo(tradeNo);
        if (pay == null) {
            Map<String, String> result = new HashMap<String, String>();
            result.put("status", "NOTEXIST");
            result.put("msg", "订单不存在");
            return JsonResult.success(result);
        }
        if(OrderConst.PayStatus.PAID.getCode().equals(pay.getPayStatus())){
            Map<String, String> result = new HashMap<String, String>();
            result.put("status", "SUCCESS");
            result.put("msg", "订单已支付");
            return JsonResult.success(result);
        }
        if(OrderConst.PayStatus.CANCELED.getCode().equals(pay.getPayStatus())){
            Map<String, String> result = new HashMap<String, String>();
            result.put("status", "CLOSED");
            result.put("msg", "订单已关闭");
            return JsonResult.success(result);
        }
        Object obj = null;
        try {
            obj = gateWayService.query(tradeNo);
        } catch (ServletException e) {
            log.error("查询失败 ： " + e.getMessage());
        } catch (IOException e) {
            log.error("查询失败 ： " + e.getMessage());
        }
        return JsonResult.success(obj);
    }

    /**
     * 退款接口
     *
     * @return
     */
    //@Auth
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult refund(Integer userId, String orderNo) {
        //1. 判断用户ID
        //2. 判断订单是否支付
        //3. 发送退款请求
        OnlineOrderInfo onlineOrderInfo = onlineOrderService.getByNo(orderNo);
        if (onlineOrderInfo == null) {
            log.error("订单不存在。");
            return JsonResult.failure(ErrorCode.SYS_ERROR);
        }
        if (!onlineOrderInfo.getPayType().equals(OrderConst.PayStatus.PAID.getCode())) {
            log.error("订单未支付。");
            return JsonResult.failure(ErrorCode.SYS_ERROR);
        }
        try {
            //创建退款请求
            GatewayPrepayInfo prepayInfo = null;
            Object result = gateWayService.refund();
            return JsonResult.success(result);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonResult.failure(ErrorCode.ORDER_BUY_FAILURE);
    }


}
