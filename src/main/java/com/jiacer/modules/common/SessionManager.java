package com.jiacer.modules.common;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionManager {
	


	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);

	/**
	 * Spring MVC获取request的三种方法：
	 * <a href="http://yeelor.iteye.com/blog/1554795">http://yeelor.iteye.com/blog/1554795</a>
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
	}

	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
	}

	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession(true);
	}

	public static HttpSession getSession() {
		return getRequest().getSession(true);
	}

	public static UserBaseInfo getUser() {
		return (UserBaseInfo)getRequest().getSession().getAttribute(Constants.USER_SESSION);
	}


}
