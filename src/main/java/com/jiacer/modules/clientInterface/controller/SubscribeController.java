package com.jiacer.modules.clientInterface.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.common.anno.Guest;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.service.SubscribeService;
import com.jiacer.modules.clientInterface.service.UserService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.SessionUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.SubAreasMapper;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.model.SubscribeInfo;

@RestController
@RequestMapping("/api/subscribe")
public class SubscribeController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(SubscribeController.class);
    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private SubAreasMapper subAreasMapper;
    @Autowired
    private UserService    userService;

    /**
     * 设置订阅提醒
     * SubscribeServiceImpl
     *
     * @return
     */
    @Guest
    @RequestMapping(value = "/setting", method = RequestMethod.POST)
    public JsonResult onSubscribe(@RequestBody SubscribeInfo info) {
    	
        HttpSession session = SessionUtils.getSession();
        UserBaseInfoEntity userBean = (UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
        if (userBean == null) {//租户信息
            return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
        }
        info.setUserId(userBean.getId());
        info.setUserName(userBean.getUserName());
        info.setOpenid(userBean.getOpenId());
        try {
            subscribeService.settingSubscribe(info);
        } catch (ServiceException e) {
            return JsonResult.failure(ResultCode.ERROR, e.getMessage());
        }

        return JsonResult.success(null);
    }


    /**
     * 查询当前订阅
     *
     * @return
     */
    @Guest
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResult get() {
        
        HttpSession session = SessionUtils.getSession();
        UserBaseInfoEntity userBean = (UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
        if (userBean == null) {//租户信息
            return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
        }
        try {
            return JsonResult.success(subscribeService.get(userBean.getId()));
        } catch (ServiceException e) {
            return JsonResult.failure(ResultCode.ERROR, e.getMessage());
        }

    }

    /**
     * 地址列表-省
     *
     * @return
     */
    @Guest
    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public JsonResult getSubServices() {
        try {
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            Map<String,String> m1 = new HashMap<String,String>();
            m1.put("code","01");
            m1.put("value", "月嫂");
            Map<String,String> m2 = new HashMap<String,String>();
            m2.put("code","02");
            m2.put("value", "育儿嫂");
            Map<String,String> m3 = new HashMap<String,String>();
            m3.put("code","03");
            m3.put("value", "保姆");
            Map<String,String> m4 = new HashMap<String,String>();
            m4.put("code","04");
            m4.put("value", "老人陪护");
            Map<String,String> m5 = new HashMap<String,String>();
            m5.put("code","05");
            m5.put("value", "钟点工");
            Map<String,String> m6 = new HashMap<String,String>();
            m6.put("code","06");
            m6.put("value", "家庭管家");
            Map<String,String> m7 = new HashMap<String,String>();
            m7.put("code","07");
            m7.put("value", "医院护工");
            list.add(m1);
            list.add(m2);
            list.add(m3);
            list.add(m4);
            list.add(m5);
            list.add(m6);
            list.add(m7);
            return JsonResult.success(list);
        } catch (ServiceException e) {
            return JsonResult.failure(ResultCode.ERROR, e.getMessage());
        }

    }

    /**
     * 地址列表-省
     *
     * @return
     */
    @Guest
    @RequestMapping(value = "/provinces", method = RequestMethod.GET)
    public JsonResult getSubAreasProvinces() {
        try {
            return JsonResult.success(subAreasMapper.getProvinces());
        } catch (ServiceException e) {
            return JsonResult.failure(ResultCode.ERROR, e.getMessage());
        }

    }

    /**
     * 地址列表-市
     *
     * @return
     */
    @Guest
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public JsonResult getSubAreasCities(String province) {
        try {
            if (StringUtils.isBlank(province)) {
                return JsonResult.failure(ResultCode.PARAMS_ERROR);
            }
            return JsonResult.success(subAreasMapper.getCities(province));
        } catch (ServiceException e) {
            return JsonResult.failure(ResultCode.ERROR, e.getMessage());
        }

    }

}
