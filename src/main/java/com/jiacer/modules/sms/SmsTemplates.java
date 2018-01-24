package com.jiacer.modules.sms;

import java.util.HashMap;
import java.util.Map;

public class SmsTemplates {
	
	/**
	 * 注册验证码模板ID
	 */
	public static final String REG_SMS_CODE = "223159";
	/**
	 * 重置密码验证码模板ID
	 */
	public static final String RESET_PWD_SMS_CODE = "223157";

	
	/**
	 * 短信模板内容
	 */
	private static Map<String, String> CONTENT;
	
	static {
		CONTENT = new HashMap<String, String>();
		CONTENT.put(REG_SMS_CODE, "【家策商学院】注册验证码：{1}，{2}分钟内有效。为了确保您的账户安全，请勿向任何人泄露您的短信验证码。");
		CONTENT.put(RESET_PWD_SMS_CODE, "【家策商学院】密码重置验证码：{1}，{2}分钟内有效。为了确保您的账户安全，请勿向任何人泄露您的短信验证码。");
	}
	
	public static String getContent(String templateId) {
		return CONTENT.get(templateId);
	}

}
