package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.jsonResult.RedpackJsonData;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.clientInterface.model.RedpackInfo;
import com.jiacer.modules.clientInterface.service.OnlineOrderService;
import com.jiacer.modules.clientInterface.service.OrdersService;
import com.jiacer.modules.clientInterface.service.RedpackService;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.page.vo.RedpackVo;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 红包服务
 * Created by gaoyan on 30/06/2017.
 */
@RestController
@RequestMapping("/api/redpack")
public class RedpackController {
    private final static Logger log = LoggerFactory.getLogger(RedpackController.class);

    @Autowired
    private RedpackService redpackService;

    @Autowired
    private OnlineOrderService ordersService;

    /**
     * 查询用户红包
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult getRedpackRecord(RedpackVo vo){

        UserBaseInfo user = SessionManager.getUser();
        if(user == null){
            log.info("用户未登陆");
            return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
        }
        Map<String, Object> total = redpackService.total(user.getId());
        vo.setRecords(Integer.valueOf(""+total.get("count")));
        vo.setUserId(user.getId());
        List<RedpackJsonData> list = redpackService.getListByUserId(vo);
        return JsonResult.success(list, vo.getPage(),vo.getTotal(),vo.getRecords());
    }

    /**
     * 统计用户红包
     * @return
     */
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public JsonResult statistic(){

        UserBaseInfo user = SessionManager.getUser();
        if(user == null){
            log.info("用户未登陆");
            return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
        }
        Map<String, Object> map = redpackService.total(user.getId());

        return JsonResult.success(map);
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult send(String orderNo){

        OnlineOrderInfo orderInfo = ordersService.getByNo(orderNo);
        if(orderInfo == null){
            return JsonResult.failure(ErrorCode.ORDER_NOT_EXIST);
        }
        redpackService.send(orderInfo);

        return JsonResult.success("end...");

    }

}
