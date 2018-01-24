package com.jiacer.modules.clientInterface.service.impl;

import com.jiacer.modules.clientInterface.common.GenerateSerialNumber;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.common.conts.OrderConst;
import com.jiacer.modules.clientInterface.common.conts.SequeConst;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.clientInterface.service.OnlineOrderService;
import com.jiacer.modules.clientInterface.service.UserService;
import com.jiacer.modules.common.mapper.JsonMapper;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.CourseOnlineMapper;
import com.jiacer.modules.mybatis.dao.OnlineOrderMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.entity.CourseOnlineEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 在线课程订单
 * Created by gaoyan on 04/07/2017.
 */
@Service
public class OnlineOrderServiceImpl implements OnlineOrderService {

    private static final Logger log = LoggerFactory.getLogger("paymentLogger");

    @Autowired
    private OnlineOrderMapper onlineOrderMapper;

    @Autowired
    private CourseOnlineMapper courseOnlineMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private GenerateSerialNumber generate;

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;

    @Override
    public ErrorCode apply(Integer courseId, Integer userId, String inviterCode, OnlineOrderInfo onlineOrderInfo) {
        //查询用户id是否存在
        if(userBaseInfoMapper.getById(userId) == null){
            log.error("用户不存在="+userId);
            return ErrorCode.USER_NOT_FOUND;
        }

        //1. 查询课程是否有效
        Map<String,Integer> param = new HashMap<String,Integer>();
        param.put("userId",userId);
        param.put("courseId",courseId);
        CourseOnlineEntity coe = courseOnlineMapper.getByIdWithUser(param);
        if (coe == null) {
            log.error("根据课程ID,查询课程为空="+courseId);
            return ErrorCode.COURSE_NOT_FOUND_BY_ID;
        }

        //查询是否已购买
        if(onlineOrderInfo == null){
            onlineOrderInfo = new OnlineOrderInfo();
        }
        onlineOrderInfo.setCourseId(courseId);
        onlineOrderInfo.setUserId(userId);
        OnlineOrderInfo oinfo = onlineOrderMapper.getByCourseId(onlineOrderInfo);
        if (oinfo != null && OrderConst.PayStatus.PAID.getCode().equals(oinfo.getPayType())) {
            //已支付，报错，不要重复够买
            log.warn("订单已支付，不要重复购买="+oinfo.getOrderNo());
            return ErrorCode.ORDER_ALREADY_PAID;
        } else if (oinfo != null && !OrderConst.PayStatus.PAID.getCode().equals(oinfo.getPayType())) {
            //已购买未支付，将原订单销毁
            log.warn("已存在未支付订单，取消后创建新订单。");
            oinfo.setPayType(OrderConst.PayStatus.CANCELED.getCode());
            oinfo.setModifyTime(new Date());
            oinfo.setModifyAccount(userId+"");
            onlineOrderMapper.update(oinfo);
        }
        //2. 创建订单
        onlineOrderInfo.setAddAccount(userId+"");
        onlineOrderInfo.setAddTime(new Date());
        onlineOrderInfo.setAmount(coe.getPrice());
        onlineOrderInfo.setOrderDesc(coe.getCourseName());
        onlineOrderInfo.setOrderTime(onlineOrderInfo.getAddTime());
        onlineOrderInfo.setOrderStatus(OrderConst.OrderStatus.WAIT_PROCESS.getCode());//待处理
        onlineOrderInfo.setPayType(OrderConst.PayStatus.WAIT_PAY.getCode());//待支付
        log.info("inviterCode === "+inviterCode);
        if(StringUtils.isNotBlank(inviterCode)){
            //添加邀请码
            UserBaseInfo info = userBaseInfoMapper.getByInviteCode(inviterCode);
            if(info != null){
                if(info.getId().equals(userId)){
                   log.info("不能使用自己的邀请码购买");
                   return ErrorCode.ORDER_OWN_INVITER;
                }
                log.info("订单添加邀请码="+info.getId());
                onlineOrderInfo.setInviterCode(info.getInviteCode());
                onlineOrderInfo.setIsInvited("1");
                onlineOrderInfo.setInviterId(info.getId());
            }else{
                log.info("邀请码不存在，请确认");
                return ErrorCode.ORDER_INVITER_NOT_EXIST;
            }
        }
        try {
            //生成订单编号
            onlineOrderInfo.setOrderNo(generate.take(SequeConst.ORDER));
            //创建订单
            onlineOrderMapper.insert(onlineOrderInfo);
            log.info("created order : " + JsonMapper.toJsonString(onlineOrderInfo));
        } catch (Exception e) {
            log.error("make order error : " + e.getMessage());
            return ErrorCode.ORDER_BUY_FAILURE;
        }
        if (onlineOrderInfo.getOrderNo() == null) {
            log.error("创建订单失败，订单id未返回。");
            return ErrorCode.ORDER_BUY_FAILURE;
        }

        return ErrorCode.SUCCESS;
    }

    @Override
    public void update(OnlineOrderInfo orderInfo) {
        onlineOrderMapper.update(orderInfo);
    }

    @Override
    public OnlineOrderInfo getByNo(String orderNo) {
        return onlineOrderMapper.getById(orderNo);
    }

    @Override
    public void submitPayStatus(OnlineOrderInfo orderInfo) {
        OnlineOrderInfo oo = new OnlineOrderInfo();
        oo.setOrderNo(orderInfo.getOrderNo());
        oo.setPayType(orderInfo.getPayType());
        oo.setOrderStatus(orderInfo.getOrderStatus());
        oo.setModifyTime(new Date());
        oo.setModifyAccount("callback");
        onlineOrderMapper.update(oo);
    }
}
