package com.jiacer.modules.common.utils;

import com.jiacer.modules.common.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession(true);
	}
	public static HttpSession getSession() {
		return SessionManager.getSession();
	}

}
