package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.bean.RegisterParamsBean;
import com.jiacer.modules.clientInterface.bean.form.UserRegisteForm;
import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import com.jiacer.modules.clientInterface.common.SessionKeys;
import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.anno.Guest;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.common.conts.RedisConst;
import com.jiacer.modules.clientInterface.service.LoginStatusService;
import com.jiacer.modules.clientInterface.service.UserService;
import com.jiacer.modules.clientInterface.service.WechatService;
import com.jiacer.modules.clientInterface.validation.RegisterValidate;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.SmsCode;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.LoginStatusEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.WechatAuthrizeInfoEntity;
import com.jiacer.modules.mybatis.model.WechatAuthrizeInfo;
import com.jiacer.modules.wechat.bean.OAuthTokenBean;
import com.jiacer.modules.wechat.bean.WechatUserinfoBean;
import com.jiacer.modules.wechat.common.WechatLog;
import com.jiacer.modules.wechat.core.GetWechatAuthrize;
import com.jiacer.modules.wechat.core.TestGetWechatAuthrize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用户接口服务列类
 *
 * @author hzp
 */
@RestController
@RequestMapping(RequestMappingURL.USERS_BASE_URL)
public class UsersController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @Resource(name = "redisTemplate")
    private StringRedisTemplate redis;

    @Autowired
    UserService userService;


    @Autowired
    WechatService wechatService;

    @Autowired
    LoginStatusService loginStatusService;

    /**
     * 微信授权免登陆
     */
    @Guest
    @RequestMapping(value = RequestMappingURL.WECHATAUTHRIZE_URL, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView wechatAuthrize(ModelAndView mav, HttpServletRequest request, String code, String state,
                                       String redirect) {
        try {
            log.info(String.format("wechat authrize, {code: %s, state: %s, redirect:%s}", code, state, redirect));
            // 判断code或者state是否为空，是则返回登录页面
            if (StringUtils.isEmpty(code)) {
                log.warn("微信授权登陆失败，请重新发起请求");
                mav.addObject("e", ErrorCode.WX_AUTH_ERROR.getValue());
                return returnIndex(mav, "/error.html");
            }

            // 通过code获取网页授权access_token凭证ticket
            OAuthTokenBean ticket = null;
            if (!"prod".equalsIgnoreCase(Constants.ENV)) {
                ticket = TestGetWechatAuthrize.getAccessToken(code);
            } else {
                ticket = GetWechatAuthrize.getAccessToken(code);
            }

            if (ticket == null) {
                log.warn("获取微信ticket失败, 请重新发起请求");
                mav.addObject("e", ErrorCode.WX_TICKET_ERROR.getValue());
                return returnIndex(mav, "/error.html");
            }

            // 检查openId或accessToken是否为空，提示使用微信客户端访问
            String openId = ticket.getOpenId();
            String accessToken = ticket.getAccessToken();
            if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(accessToken)) {
                log.warn("获取微信openId 和 accessToken失败, 请重新发起请求");
                mav.addObject("e", ErrorCode.WX_OPENID_AUTH_ERROR.getValue());
                return returnIndex(mav, "/error.html");
            }

            log.info("wx auth open-id : " + openId);
            // 通过accessToken凭证,和openId获取用户微信信息
            WechatUserinfoBean wechatUserInfo = null;
            if (!"prod".equalsIgnoreCase(Constants.ENV)) {
                wechatUserInfo = TestGetWechatAuthrize.getWechatUserinfo(accessToken, openId);
            } else {
                wechatUserInfo = GetWechatAuthrize.getWechatUserinfo(accessToken, openId);
            }
            if (wechatUserInfo == null) {
                log.warn("获取微信用户资料失败, 请使用[snsapi_userinfo]授权发起请求");
                mav.addObject("e", ErrorCode.WX_GET_USER_INFO_ERROR.getValue());
                return returnIndex(mav, "/error.html");
            }

            // 通过openId查询授权表wechat_authrize_info，确认用户是否授权:不存在，则保存授权信息；存在，则更新授权信息
            WechatAuthrizeInfoEntity wechatAuthrizeInfo = wechatService.getWechatAuthrizeInfoByOpenId(openId);
            WechatAuthrizeInfoEntity wechatnfo = wechatAuthrizeInfo;
            if (wechatnfo == null) {
                wechatnfo = new WechatAuthrizeInfoEntity();
                wechatnfo.setOpenId(wechatUserInfo.getOpenId());
            }

            //更新用户微信授权信息，但不用更新用户个人信息
            wechatnfo.setNick(wechatUserInfo.getNickName());
            wechatnfo.setHeadImgUrl(wechatUserInfo.getHeadImgUrl());
            wechatnfo.setSex(wechatUserInfo.getSex());
            wechatnfo.setAccessToken(accessToken);
            wechatnfo.setRefreshToken(ticket.getRefreshToken());
            Calendar cal = Calendar.getInstance();
            int expiresIn = ticket.getExpiresIn() - 60 * 10;
            cal.add(Calendar.SECOND, expiresIn);
            wechatnfo.setExpiringTime(cal.getTime());

            if (wechatAuthrizeInfo == null) {
                wechatService.insertWechatAuthrizeInfo(wechatnfo);
            } else {
                wechatService.updateWechatAuthrizeInfo(wechatnfo);
            }


            //将用户的微信资料放入session
            SessionManager.getSession().setAttribute(Constants.Session.WX_USER_INFO, wechatnfo);

            // 判断用户是否绑定openId
            UserBaseInfoEntity user = userService.getUserByOpenId(openId);
            if (user == null) {
                log.info("用户openId没有对应客户信息，以游客身份访问");
                //return returnIndex(mav, "/register.html");
            } else {
                //用户登陆授权
                HttpSession session = SessionManager.getSession(request);
                session.setAttribute(SessionKeys.USER_SESSION, user);
                try {
                    saveLoginStatus(user, openId);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

            //跳转首页
            log.info("跳转到首页?=" + redirect);
            StringBuilder redirectUrl = new StringBuilder();
            redirectUrl.append("/index.html?wx-openid=").append(openId);
            redirectUrl.append("&invite-code=");
            redirectUrl.append(user == null ? "" : user.getInviteCode());
            redirectUrl.append("&inviter-code=");
            redirectUrl.append(state);
            redirectUrl.append("&to=");
            if (StringUtils.isNotBlank(redirect)) {
                redirectUrl.append(URLEncoder.encode(redirect, "UTF8"));
            }
            log.info("redirect to " + redirectUrl.toString());

            mav.setView(new RedirectView(redirectUrl.toString(), false));
            return mav;
        } catch (Exception e)
        {
            WechatLog.error("wechat authrize error:", e);
            return returnIndex(mav, redirect);
        }

    }

    private ModelAndView returnIndex(ModelAndView mav, String redirect) {
        redirect = StringUtils.isBlank(redirect) ? "/index.html" : redirect;
        mav.setView(new RedirectView(redirect, false));
        return mav;
    }

    private ModelAndView returnIndex(ModelAndView mav, String openId, String redirect) {
        StringBuilder redirectUrl = new StringBuilder(StringUtils.isBlank(redirect) ? "/index.html" : redirect);
        redirectUrl.append("?wx-openid=").append(openId);
        mav.setView(new RedirectView(redirectUrl.toString(), false));
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addUser(RegisterParamsBean form) {
        try {
            RegisterParamsBean params = new RegisterParamsBean();
            params.setMobile("111");
            params.setUserName("abc");
            params.setWechatNick("12312");
            params.setWechatImage("asaa");
            params.setOpenId("dfd");
            params.setSex("1");
            params.setInviterCode("1");
            params.setIsInvite("1");
            userService.register(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 注册／登陆／绑定
     *
     * @param request
     * @param form
     * @return
     */
    @Auth
    @RequestMapping(value = RequestMappingURL.REGISTER_BIND_URL, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult registerBind(HttpServletRequest request, UserRegisteForm form) {

        WechatAuthrizeInfo wxInfo = (WechatAuthrizeInfo) SessionManager.getSession().getAttribute(Constants.Session.WX_USER_INFO);
        if (wxInfo == null || org.apache.commons.lang3.StringUtils.isBlank(wxInfo.getOpenId())) {
            // openId为空，强制从微信客户端请求。
            return JsonResult.failure(ErrorCode.WX_AUTH_ERROR);
        }

        // 校验参数是否合法
        JsonResult jsonResult = RegisterValidate.firstRegisterValidate(form);

        if (!jsonResult.isSuccess()) {
            return jsonResult;
        }

        // 校验图片验证码
        String captcha = redis.opsForValue().get(RedisConst.Common.CAPTCHA_CODE.getKey());
        log.info(String.format("register redis-param captcha : %s-%s ", captcha, form.getCaptchaCode()));
        if (StringUtils.isBlank(captcha)) { // 验证码过期
            return JsonResult.failure(ErrorCode.REG_CAPTCHA_EXPIRED);
        } else if (!captcha.toUpperCase().equals(form.getCaptchaCode().toUpperCase())) { // 验证码不正确
            return JsonResult.failure(ErrorCode.REG_CAPTCHA_ERROR);
        }

        // 校验短信验证码
        String smsCode = redis.opsForValue().get(RedisConst.Common.SMS_CODE.getKey());
        if ("prod".equals(Constants.ENV)) {
            log.info(String.format("register redis-param sms code : %s-%s ", smsCode, form.getSmsCode()));
            if (StringUtils.isBlank(smsCode)) {
                return JsonResult.failure(ErrorCode.REG_SMS_EXPIRED);
            } else if (!form.getSmsCode().equals(smsCode)) {
                return JsonResult.failure(ErrorCode.REG_SMS_ERROR);
            }
        }
        /**
         * 1. 如果手机号有注册信息，则绑定openId，登陆
         * 2. 如果手机号没有注册信息，则注册用户
         */
        UserBaseInfoEntity userinfo = userService.getUserByMobile(form.getMobile());

        String inviterCode = request.getHeader("inviter-code");

        if (userinfo == null) {
            RegisterParamsBean params = new RegisterParamsBean();
            params.setMobile(form.getMobile());
            params.setUserName(wxInfo.getNick());
            params.setWechatNick(wxInfo.getNick());
            params.setWechatImage(wxInfo.getHeadImgUrl());
            params.setOpenId(wxInfo.getOpenId());
            params.setSex(wxInfo.getSex());
            params.setInviterCode(StringUtils.isBlank(inviterCode) ? "" : String.valueOf(inviterCode));
            params.setIsInvite(StringUtils.isBlank(inviterCode) ? "0" : "1");

            try {
                userService.register(params);
            } catch (Exception e) {
                log.error("register error.." + e.getMessage());
                return JsonResult.failure(ErrorCode.REG_FAILURE);
            }
        } else if (org.apache.commons.lang3.StringUtils.isBlank(userinfo.getOpenId())) {
            UserBaseInfoEntity up = new UserBaseInfoEntity();
            up.setId(userinfo.getId());
            up.setInviteCode(StringUtils.fillZero(5, userinfo.getId()));
            up.setWechatNick(wxInfo.getNick());
            up.setWechatImage(wxInfo.getHeadImgUrl());
            up.setOpenId(wxInfo.getOpenId());
            up.setGender(wxInfo.getSex());
            userService.updateUserBaseInfo(up);
        } else {
            //如果该手机已绑定微信号，则报错
            log.error(String.format("手机已绑定微信号[%s]", userinfo));
            return JsonResult.failure(ErrorCode.REG_MOBILE_ALREADY_BIND_ERROR);
        }

        userinfo = userService.getUserByMobile(form.getMobile());

        //注册／绑定／登陆
        HttpSession session = SessionManager.getSession();
        session.setAttribute(SessionKeys.USER_SESSION, userinfo);
        try {
            saveLoginStatus(userinfo, wxInfo.getOpenId());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        SessionManager.getSession().removeAttribute(SessionKeys.REGISTER_PARAM_KEY);
        SessionManager.getSession().removeAttribute(SmsCode.REG_SMS_CODE_KEY);
        return JsonResult.success(userinfo);

    }


    public void saveLoginStatus(UserBaseInfoEntity user, String openId) {
        try {
            // 保存用户登录状态
            LoginStatusEntity loginStatus = new LoginStatusEntity();
            loginStatus.setUserId(user.getId());
            loginStatus.setOpenId(openId);
            loginStatus.setMobile(user.getMobile());
            loginStatus.setLoginFlag(Constants.LoginFlag.YES);
            loginStatus.setLoginTime(new Date());
            loginStatus.setLastActiveTime(new Date());

            // 判断用户是否是微信端来的，如果是，则存储来源为微信登录状态为1
            if (openId != null) {
                loginStatus.setSource(Constants.WEBCHAT);
            }
            loginStatusService.insertLoginStatus(loginStatus);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.error("保存微信登陆记录失败，", e);
        }
    }

}
