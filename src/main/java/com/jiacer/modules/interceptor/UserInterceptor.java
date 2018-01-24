package com.jiacer.modules.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jiacer.modules.clientInterface.service.UserService;
import com.jiacer.modules.clientInterface.service.WechatService;

/**
 * Created by gaoyan on 28/06/2017.
 */
@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserInterceptor.class);

    private final static String UTF8 = "UTF8";

    @Autowired
    private UserService userService;
    @Autowired
    private WechatService wechatService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        HttpSession session = SessionManager.getSession(request);
//        String openId = request.getHeader(Constants.REQ_WX_OPENID);
//        WechatAuthrizeInfo wechat = wechatService.getWechatAuthrizeInfoByOpenId(openId);
//        session.setAttribute(Constants.Session.WX_USER_INFO, wechat);
//
//        //若请求方法允许游客访问，则不验证
//        if (handler instanceof HandlerMethod) {
//            log.info("method : " + ((HandlerMethod) handler).getMethod().getName());
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Guest guest = handlerMethod.getMethodAnnotation(Guest.class);
//            Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
//            if (guest != null) {
//                log.info("guest method, skipping");
//                return true;
//            }
//            if (auth != null) {
//                log.info("auth method , check openid and wxAuth");
//                //则需要微信授权验证
//                if (StringUtils.isNotBlank(openId) && wechat != null) {
//                    return true;
//                }
//                //若请求中不带openId，则请求没有授权，需要授权
//                //wx.jiacedu.com
//                StringBuffer host = new StringBuffer();
//                //request.getServerName());
//                host.append(request.getScheme() + "://" + "test.jiacersxy.com/");
//                if (request.getServerPort() != 80) {
//                    host.append(":").append(request.getServerPort());
//                }
//                host.append("/jiacerapps");
//                /**
//                 * https://open.weixin.qq.com/connect/oauth2/authorize?
//                 * appid=wxae59a35c51f7e95c
//                 * &redirect_uri=http://wx.jiacedu.com/jiacerapps/api/user/wechatAuthrize?redirect=/pages/register.html
//                 * ?&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
//                 */
//                String authpath = "/api/user/wechatAuthrize?redirect=";
//                String appURI = request.getRequestURI();
//                String suf = "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
//                StringBuffer sb = new StringBuffer();
//                sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?")
//                        .append("appid=").append(Constants.APPID)
//                        .append("&redirect_uri=")
//                        .append(URLEncoder.encode(host.toString(), UTF8))
//                        .append(URLEncoder.encode(authpath, UTF8))
//                        //.append(URLEncoder.encode(appURI, UTF8))
//                        .append(suf);
//                log.info("auth redirect: " + sb.toString());
//                response.sendRedirect(sb.toString());
//                return false;
//            }
//        }
//
//        //其他方法都是私有，需要注册用户信息
//        //若请求存在openId， 查询该Id对应的用户
//        UserBaseInfoEntity user = userService.getUserByOpenId(openId);
//        session.setAttribute(SessionKeys.USER_SESSION, user);
//        //如果带有openId，但没有用户信息，则需要注册
//        if (user == null) {
//            log.info("user need login");
//            //response.setHeader("content-type", "application/json;charset=UTF8");
//            //response.getWriter().println(JsonResult.failure(ErrorCode.USER_NEED_LOGIN));
//            response.sendRedirect("/register.html");
//            return false;
//        }
        return true;
    }
}
