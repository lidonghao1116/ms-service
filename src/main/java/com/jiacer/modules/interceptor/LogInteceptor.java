package com.jiacer.modules.interceptor;

import com.jiacer.modules.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by gaoyan on 13/07/2017.
 */
@Component
public class LogInteceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger("accessLogger");


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求地址信息
        StringBuffer req = new StringBuffer();
        req.append(request.getHeader("wx-openid")).append("|");
        //请求参数信息
        if (request != null) {
            req.append(request.getMethod()).append(" ").append(request.getServletPath()).append(" [");
            //请求参数信息
            req.append(JsonUtils.toJson(request.getParameterMap()));
            //请求头信息
            Enumeration<String> keys = request.getHeaderNames();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                req.append(key).append(":").append(request.getHeader(key)).append(";");
            }
            req.append("]").append("|");
        }
        req.append("Thread[").append(Thread.currentThread().getId()).append("]");
        logger.info("[REQUEST ]|{}", req.toString());

        return super.preHandle(request, response, handler);
    }

}
