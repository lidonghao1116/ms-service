package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.common.GenerateSerialNumber;
import com.jiacer.modules.clientInterface.common.conts.*;
import com.jiacer.modules.clientInterface.model.GatewayPrepayInfo;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.clientInterface.service.GateWayService;
import com.jiacer.modules.clientInterface.service.OnlineOrderService;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.mapper.JsonMapper;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 订单支付
 * Created by gaoyan on 04/07/2017.
 */
@RestController
@RequestMapping("/api/trade")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger("paymentLogger");


    @Autowired
    private GateWayService service;
    @Autowired
    private OnlineOrderService onlineOrderService;

    @Autowired
    private GenerateSerialNumber generate;

    /**
     * 申请订单
     */
    @RequestMapping(value = "/prepay", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult prepay(Integer courseId, String inviterCode) {
        try {
            log.info("prepay == courseId:"+courseId+";inviterCode:"+inviterCode);
            //0. 判断用户登陆
            UserBaseInfoEntity user = (UserBaseInfoEntity) SessionManager.getSession().getAttribute(Constants.USER_SESSION);
            if (user == null) {
                log.warn("未查询到用户登陆信息");
                return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
            }

            //生成订单
            OnlineOrderInfo orderInfo = new OnlineOrderInfo();
            orderInfo.setTradeChannel(OrderConst.TradeChannel.WX_JS_PAY.getCode());
            orderInfo.setOriginChannel(FinanceConst.OriginChannel.JIACER_ONLINE_SCHOOL_APP.getCode());
            ErrorCode code = onlineOrderService.apply(courseId, user.getId(), inviterCode, orderInfo);
            if(code != ErrorCode.SUCCESS){
                log.error("订单生成失败。");
                return JsonResult.failure(code);
            }
            //3. 支付请求
            Object result = service.pay(orderInfo, GatewayPrepayInfo.getWxJsPayInfo(generate.take(SequeConst.PAY_INFO),orderInfo.getAmount(),user.getOpenId(), orderInfo.getOrderDesc()));
            log.info("prepay end .. "+JsonMapper.toJsonString(result));
            //3. 将支付信息返回给页面发起支付
            return JsonResult.success(result);
        } catch (Exception e) {
            log.error("操作失败，原因："+ e.getMessage());
            return JsonResult.failure(ErrorCode.SYS_ERROR);
        }

    }

    /**
     * 订单查询
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult query(String tradeNo) {
        UserBaseInfoEntity user = (UserBaseInfoEntity) SessionManager.getSession().getAttribute(Constants.USER_SESSION);
        if (user == null) {
            log.warn("未查询到用户登陆信息");
            return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
        }
        try {
            Object obj = service.query(tradeNo);
            return JsonResult.success(obj);
        } catch (Exception e) {
            log.error("操作失败，原因："+ e.getMessage());
            return JsonResult.failure(ErrorCode.SYS_ERROR);
        }

    }

    /**
     * 退款查询
     */
    @RequestMapping(value = "/refund", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult queryRefund(String method) {
        try {
            Map<String, String> result = service.refundQuery();
            return JsonResult.success(result);
        } catch (Exception e) {
            log.error("操作失败，原因："+ e.getMessage());
            return JsonResult.failure(ErrorCode.SYS_ERROR);
        }

    }

    /**
     * 申请退款
     */
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult refund() {
        try {
            Map<String, String> result = service.refund();
            return JsonResult.success(result);
        } catch (Exception e) {
            log.error("操作失败，原因："+ e.getMessage());
            return JsonResult.failure(ErrorCode.SYS_ERROR);
        }

    }


}
