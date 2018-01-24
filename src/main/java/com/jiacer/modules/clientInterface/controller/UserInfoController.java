package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.common.SessionKeys;
import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.jsonResult.UserInfoBaseInfoJsonData;
import com.jiacer.modules.clientInterface.model.CertInfo;
import com.jiacer.modules.clientInterface.service.UserService;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import com.jiacer.modules.mybatis.model.WechatAuthrizeInfo;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 贺章鹏
 * @ClassName: UserInfoController
 * @Description: 我
 * @date 2016年11月16日 下午1:17:14
 */
@RestController
@RequestMapping(RequestMappingURL.USERS_INFO_URL)
public class UserInfoController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserService userService;

    /**
     * 个人信息
     *
     * @return
     */
    @Auth
    @RequestMapping(value = RequestMappingURL.INFO_WECHAT_URL, method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getUserWechatInfo() {

        HttpSession session = SessionManager.getSession();
        WechatAuthrizeInfo info = (WechatAuthrizeInfo)session.getAttribute(Constants.Session.WX_USER_INFO);
        return JsonResult.success(info);
    }


    /**
     * 个人信息
     *
     * @return
     */
    @RequestMapping(value = RequestMappingURL.INFO_URL, method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getUserInfo() {

        try {
            HttpSession session = SessionManager.getSession();
            UserBaseInfoEntity user = (UserBaseInfoEntity) session.getAttribute(SessionKeys.USER_SESSION);
            if (user == null || user.getId() == null) {
                return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
            }
            UserInfoBaseInfoJsonData jsonData = new UserInfoBaseInfoJsonData();
            try {
                UserBaseInfoEntity userInfo = userService.getUser(user.getMobile());
                if (userInfo == null) {
                    return JsonResult.failure(ErrorCode.QUERY_RESULT_EMPTY);
                }
                jsonData.setMobile(user.getMobile());
                jsonData.setUsername(userInfo.getUserName());
                jsonData.setCertNo(StringUtils.hideCertNo(userInfo.getCertNo()));
                jsonData.setCertType(userInfo.getCertType());
                jsonData.setHeadImg(userInfo.getWechatImage());
                jsonData.setNickname(userInfo.getWechatNick());
                jsonData.setStatus(userInfo.getUserStatus());
                jsonData.setInviteCode(userInfo.getInviteCode());
            } catch (Exception e) {
                log.warn(e.getMessage());
                jsonData.setUsername("");
            }

            return JsonResult.success(jsonData);
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }

    /**
     * 个人信息修改
     *
     * @return
     */
    @RequestMapping(value = RequestMappingURL.UPDATE_INFO_URL, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult update(UserInfoBaseInfoJsonData userInfo) {

        if (StringUtils.isAllBlank(userInfo.getUsername(),
                userInfo.getCertNo(), userInfo.getNickname())) {
            log.warn("参数为空，不做修改");
            return JsonResult.failure(ErrorCode.REQUEST_PARAM_EMPTY);
        }

        try {
            HttpSession session = SessionManager.getSession();
            UserBaseInfoEntity user = (UserBaseInfoEntity) session.getAttribute(SessionKeys.USER_SESSION);
            if (user == null || user.getId() == null) {
                return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
            }
            UserBaseInfoEntity param = new UserBaseInfoEntity();
            try {
                //param.setMobile(userInfo.getMobile());
                param.setId(user.getId());
                param.setUserName(StringUtils.isNotBlank(userInfo.getUsername())?userInfo.getUsername():null);
                param.setCertNo(StringUtils.isNotBlank(userInfo.getCertNo())?userInfo.getCertNo():null);
                param.setCertType(StringUtils.isNotBlank(userInfo.getCertType())?userInfo.getCertType():null);
                param.setEducation(StringUtils.isNotBlank(userInfo.getEducation())?userInfo.getEducation():null);
                param.setWechatNick(StringUtils.isNotBlank(userInfo.getNickname())?userInfo.getNickname():null);
                userService.updateUserBaseInfo(param);
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
            // 设置用户账号
            param.setCertNo(StringUtils.hideCertNo(param.getCertNo()));
            return JsonResult.success(param);
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }


    @RequestMapping(value = "/cert", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getOwnCert() {
        UserBaseInfo user = SessionManager.getUser();
        if (user == null) {
            return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
        }
        try {
            List<CertInfo> list = userService.getOwnCertList(user.getId());
            return JsonResult.success(list);
        } catch (Exception e) {
            log.error("查询我的证书失败：" + e.getMessage());
        }
        return JsonResult.failure(ErrorCode.QUERY_RESULT_ERROR);

    }
}
