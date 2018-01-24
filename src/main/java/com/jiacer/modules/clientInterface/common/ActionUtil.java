package com.jiacer.modules.clientInterface.common;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaoyan on 14/07/2017.
 */
public class ActionUtil {

    /**
     * Spring MVC获取request的三种方法：
     * <a href="http://yeelor.iteye.com/blog/1554795">http://yeelor.iteye.com/blog/1554795</a>
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    public static String getUserOpenId(){
        return getRequest().getHeader(Constants.REQ_WX_OPENID);
    }
    public static String getUserInviterCode(){
        return getRequest().getHeader(Constants.REQ_INVITER_CODE);
    }

    /**获取请求IP*/
    public static String getIP() {
        HttpServletRequest request = ActionUtil.getRequest();
        String addr = request.getRemoteAddr();
        //haproxy代理后把真实访问ip放在http header的 “X-Forwarded-For”中
        String headerXForwardedFor = request.getHeader("X-Forwarded-For");
        return headerXForwardedFor == null ? addr : headerXForwardedFor;
    }

    /**获取服务器真实路径*/
    public static String getRealPath(String path) {
        HttpServletRequest request = ActionUtil.getRequest();
        return request.getSession().getServletContext().getRealPath(path);
    }

}
