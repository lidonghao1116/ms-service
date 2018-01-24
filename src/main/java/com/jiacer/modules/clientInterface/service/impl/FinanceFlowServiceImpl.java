package com.jiacer.modules.clientInterface.service.impl;

import com.jiacer.modules.clientInterface.common.GenerateSerialNumber;
import com.jiacer.modules.clientInterface.common.conts.OrderConst;
import com.jiacer.modules.clientInterface.common.conts.SequeConst;
import com.jiacer.modules.clientInterface.model.FinanceFlowInfo;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.clientInterface.model.PayInfo;
import com.jiacer.modules.clientInterface.model.SwiftpassCallbackInfo;
import com.jiacer.modules.clientInterface.service.*;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.FinanceFlowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 资金流水
 * Created by gaoyan on 11/07/2017.
 */
@Service
public class FinanceFlowServiceImpl implements FinanceFlowService {

    private static final Logger log = LoggerFactory.getLogger("callbackLogger");

    private GenerateSerialNumber generate;

    @Autowired
    private OnlineOrderService orderService;
    @Autowired
    private PayService payService;
    @Autowired
    private RedpackService redpackService;
    @Autowired
    private UserService userService;
    @Autowired
    private FinanceFlowMapper financeFlowMapper;

    private static ConcurrentHashMap<String, AtomicBoolean> process = new ConcurrentHashMap<String, AtomicBoolean>();

    @Override
    public void addFinanceFlow(FinanceFlowInfo info) {
        info.setFinanceNo(generate.take(SequeConst.FINANCE_FLOW));
        financeFlowMapper.insert(info);
    }

    @Override
    public void modifyFinanceFlow(FinanceFlowInfo info) {
        financeFlowMapper.update(info);
    }

    @Override
    public FinanceFlowInfo findByOutTradeNo(String outTradeNo) {
        return financeFlowMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public Object processSwift(SwiftpassCallbackInfo sp) {
        try {
            if (!process.containsKey(sp.getOutTradeNo())) {
                process.put(sp.getOutTradeNo(), new AtomicBoolean());
            }
            //此处可以在添加相关处理业务，校验通知参数中的商户订单号out_trade_no和金额total_fee是否和商户业务系统的单号和金额是否一致，一致后方可更新数据库表中的记录。
            //如果是false，则准备处理。true表示正在处理，跳过。
            if (process.get(sp.getOutTradeNo()).compareAndSet(false, true)) {
                log.info(String.format("====== callback process begin, pay_no=[%s]", sp.getOutTradeNo()));
                //1. 查询支付请求是否被处理
                PayInfo payInfo = payService.getPayInfoByNo(sp.getOutTradeNo());
                if (payInfo == null) {
                    log.info("没有相应支付单。支付单号：" + sp.getOutTradeNo());
                    process.get(sp.getOutTradeNo()).compareAndSet(true, false);
                    return "fail";
                }
                if (OrderConst.PayStatus.PAID.getCode().equals(payInfo.getPayStatus())) {
                    log.info("支付单已经支付，返回成功。支付单号：" + payInfo.getPayNo());
                    process.remove(sp.getOutTradeNo());
                    return "success";
                }
                if (OrderConst.PayStatus.CANCELED.getCode().equals(payInfo.getPayStatus())) {
                    log.info("[ pay ]支付单已经作废，改为支付。支付单号：" + payInfo.getPayNo());
                    //process.remove(sp.getOutTradeNo());
                    //return "fail";
                }
                //2. 查询订单是否支付
                OnlineOrderInfo orderInfo = orderService.getByNo(payInfo.getOrderNo());
                if (orderInfo == null) {
                    log.info("没有相应订单。订单号：" + payInfo.getOrderNo());
                    process.get(sp.getOutTradeNo()).compareAndSet(true, false);
                    return "fail";
                }
                if (OrderConst.PayStatus.PAID.getCode().equals(orderInfo.getPayType())) {
                    log.info("订单已经支付，返回成功。支付单号：" + payInfo.getPayNo());
                    process.remove(sp.getOutTradeNo());
                    return "success";
                }
                if (OrderConst.PayStatus.CANCELED.getCode().equals(orderInfo.getPayType())) {
                    log.info("[refund]订单已经作废，不可支付，请申请退款。返回失败。支付单号：" + payInfo.getPayNo());
                    process.remove(sp.getOutTradeNo());
                    return "fail";
                }
                FinanceFlowInfo finance = findByOutTradeNo(orderInfo.getOrderNo());
                if (finance == null) {
                    //流水不存在
                    log.info("没有相应订单流水。订单号：" + payInfo.getOrderNo());
                    process.get(sp.getOutTradeNo()).compareAndSet(true, false);
                    return "fail";
                }
                if (finance.getPayStatus().equals(OrderConst.PayStatus.PAID.getCode())) {
                    //订单已支付
                    log.info("订单已经支付，返回成功。支付单号：" + payInfo.getPayNo());
                    process.remove(sp.getOutTradeNo());
                    return "success";
                }
                //3. 校验支付单价格信息是否正确
                if (!sp.getTotalFee().equals(payInfo.getAmount() + "")) {
                    log.info(String.format("支付金额不一致，请检查。callback=%s, pay_info=%s, pay_no[%s]", sp.getTotalFee(), payInfo.getAmount() + "", payInfo.getPayNo()));
                    process.get(sp.getOutTradeNo()).compareAndSet(true, false);
                    return "fail";
                }
                //4. 修改支付状态
                log.info(String.format("修改订单及支付单状态,order_no[%s],pay_No[%s]", orderInfo.getOrderNo(), payInfo.getPayNo()));
                payInfo.setPayStatus(OrderConst.PayStatus.PAID.getCode());
                payInfo.setCallbackInfo(sp.getResultXml());
                payInfo.setCallbackTime(new Date());
                payService.submitPayStatus(payInfo);
                orderInfo.setOrderStatus(OrderConst.OrderStatus.PROCESS_FINISHED.getCode());
                orderInfo.setPayType(OrderConst.PayStatus.PAID.getCode());
                orderService.submitPayStatus(orderInfo);
                //5. 修改支付流水状态
                // insert finance flow
                log.info("update finance flow ... begin");
                finance.setPayStatus(OrderConst.PayStatus.PAID.getCode());
                modifyFinanceFlow(finance);
                log.info("update finance flow ... end");
                //6. 添加红包
                log.info("是否需要发红包：1==" + orderInfo.getIsInvited() + "&inviterCode==" + orderInfo.getInviterCode());
                if ("1".equals(orderInfo.getIsInvited()) && StringUtils.isNotBlank(orderInfo.getInviterCode())) {
                    redpackService.send(orderInfo);
                }
                //7. 处理完成，返回成功
                log.info(String.format("支付回调处理完成，pay_no [%s]", sp.getOutTradeNo()));
                process.remove(sp.getOutTradeNo());
                return "success";
            } else {
                log.info(String.format("====== callback processing, pay_no=[%s]", sp.getOutTradeNo()));
            }
        } catch (Exception e) {
            log.error("操作失败，原因：" + e.getMessage());
            process.get(sp.getOutTradeNo()).compareAndSet(true, false);
            return "fail";
        }
        return "";
    }
}
