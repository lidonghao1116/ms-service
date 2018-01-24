package com.jiacer.modules.clientInterface.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description: 响应返回码
 * @author hzp
 * @date 2016-5-11
 *
 */
public class ResultCode {
	
	public static final Integer SUCCESS = 0;
	public static final Integer ERROR = 999999;
	public static final Integer SESSION_TIMEOUT = 999010;
	public static final Integer PARAMS_ERROR = 999011;
	public static final Integer DATA_ERROR = 999012;
	public static final Integer ILLEGAL_REQUEST = 999013;
	public static final Integer AUTHORIZE_ERROR = 999014;

	public static final class User {
		public static final Integer MOBILE_FORMAT_ERROR = 100001;
		public static final Integer PASSWORD_FORMAT_ERROR = 100002;
		public static final Integer EXIST_MOBILE = 100003;
		
		public static final Integer MOBILE_PASSWORD_ERROR = 100004;
		public static final Integer LOGIN_OUT_LIMIT = 100005;
		public static final Integer MOBILE_ERROR = 100006;
		public static final Integer NONEXISTENT_MOBILE = 100007;
		
		public static final Integer CAPTCHA_FAILURE = 100008;
		public static final Integer CAPTCHA_ERROR = 100009;
		public static final Integer CAPTCHA_TIMEOUT = 100010;
		
		public static final Integer SMS_CODE_FAILURE= 100011;
		public static final Integer SMS_CODE_ERROR= 100012;
		public static final Integer SMS_CODE_TIMEOUT= 100013;
		
		public static final Integer TRY_REGISTER_OUT_LIMIT = 100014;
		public static final Integer TRY_RESET_PWD_OUT_LIMIT = 100015;

		public static final Integer CERT_NO_MOBILE = 100016;

		public static final Integer INVITE_CODE_ERROR = 100017;
		public static final Integer UN_BUNDED = 100018;
	}
	
	public static class Function {
		public static final Integer LOGIN_DISABLED = 500001;
	}
	
	public static class Product{
		public static final Integer EXIST_B_M = 600001;
	}
	
	public static final String getMsg(Integer code) {
		return resultMsg.get(code);
	}
	
	private static Map<Integer, String> resultMsg;
	
	static {
		resultMsg = new HashMap<Integer, String>();
		resultMsg.put(SUCCESS, "成功");
		resultMsg.put(ERROR, "系统错误");
		resultMsg.put(SESSION_TIMEOUT, "登陆超时,请重新登录");
		resultMsg.put(PARAMS_ERROR, "参数错误");
		resultMsg.put(DATA_ERROR, "数据异常");
		resultMsg.put(ILLEGAL_REQUEST, "非法请求");
		resultMsg.put(AUTHORIZE_ERROR, "授权失败");
		
		resultMsg.put(Function.LOGIN_DISABLED, "无法登陆");
		
		resultMsg.put(User.MOBILE_FORMAT_ERROR, "请输入正确的11位手机号");
		resultMsg.put(User.PASSWORD_FORMAT_ERROR, "密码有误，请输入6~12位，包含数字、字母和符号的组合");
		resultMsg.put(User.EXIST_MOBILE, "该手机号已经注册");
		resultMsg.put(User.MOBILE_PASSWORD_ERROR, "手机号或登录密码错误");
		resultMsg.put(User.LOGIN_OUT_LIMIT, "对不起，您的手机号或登录密码错误次数超限，请2小时后再尝试");
		resultMsg.put(User.MOBILE_ERROR, "请输入正确的手机号");
		resultMsg.put(User.NONEXISTENT_MOBILE, "该手机号已经注册，请直接登录~");
		resultMsg.put(User.CERT_NO_MOBILE, "该身份证已经存在");
		
		resultMsg.put(User.CAPTCHA_FAILURE, "图形验证码获取失败，请重新获取");
		resultMsg.put(User.CAPTCHA_ERROR, "图形验证码不正确，请重新填写");
		resultMsg.put(User.CAPTCHA_TIMEOUT, "图形验证码已失效，请重新填写");
		resultMsg.put(User.SMS_CODE_FAILURE, "短信验证码发送失败，请重新获取");
		resultMsg.put(User.SMS_CODE_ERROR, "短信验证码不正确");
		resultMsg.put(User.SMS_CODE_TIMEOUT, "短信验证码已失效，请重新获取");
		resultMsg.put(User.INVITE_CODE_ERROR, "该邀请码无效，请重新输入");

		resultMsg.put(User.TRY_REGISTER_OUT_LIMIT, "对不起，该手机号今天尝试注册的次数已达上限，请明天再尝试");
		resultMsg.put(User.TRY_RESET_PWD_OUT_LIMIT, "对不起，该手机号今天尝试重置密码的次数已达上限，请明天再尝试");
		
		resultMsg.put(User.UN_BUNDED, "未注册，请注册后使用该功能");
		resultMsg.put(Product.EXIST_B_M, "对不起，您已报名了该课程");
	}
}
