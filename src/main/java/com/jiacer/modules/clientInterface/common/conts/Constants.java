package com.jiacer.modules.clientInterface.common.conts;

import com.jiacer.modules.clientInterface.common.SessionKeys;
import com.jiacer.modules.common.utils.EnvConfig;

public interface Constants {
	
	public static final String USER_SESSION = SessionKeys.USER_SESSION;
	
	/**
	 * session中保存注册短信验证码key
	 */
	public static final String REGISTER_MSG_CODE = "registerMsgCode";

	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 一年的天数
	 */
	public static final int YEAR_DAYS = 365;
	
	/**
	 * 订单支付限制时间，分钟
	 */
	public static final int PAY_TIME_MINUTES = 30;
	
	public static final String ENVFILEPATH = "/env.properties";

	public static final String CHARSET = "UTF-8";

	public static final String REQ_WX_OPENID = "wx-openid";
	public static final String REQ_INVITER_CODE = "invite-code";
	
	public static final String SERVER_IP = "server.ip";


	public static final String WECHAT_APPID = "wechat.appid";
	public static final String WECHAT_APPSECRET = "wechat.appsecret";

	public static final String WECHATPAY_MCHID = "wechatpay.mchid";
	public static final String WECHATPAY_SECRETKEY = "wechatpay.secretkey";

	public static final String YTX_DOMAIN = "sms.ytx.domain";
	public static final String YTX_PORT = "sms.ytx.port";
	public static final String YTX_ACCOUNT_SID = "sms.ytx.accountSid";
	public static final String YTX_AUTH_TOKEN = "sms.ytx.authToken";
	public static final String YTX_APP_ID = "sms.ytx.appId";
	
	public static final String  ENTRY_STRING= "{[(%s)]}";
	
	public static final String  PARAMS_CACHE="PARAMS_CACHE";
	
	/**
	 * 对外服务接口ip白名单，多个用英文逗号分隔
	 */
	public static final String SERVICE_API_ALLOWED_IPS = "serviceApiAllowedIps";

	
	/**
	 * 微信应用appID
	 * */
	public static final String APPID = EnvConfig.getInstance().getProperty(EnvConfig.WECHAT_APPID);
	/**
	 * 应用密钥
	 * */
	public static final String APPSECRET = EnvConfig.getInstance().getProperty(EnvConfig.WECHAT_APPSECRET);

	/**
	 * 微信商户号
     */
	public static final String WECHAT_MERCHANT_ID = EnvConfig.getInstance().getProperty(EnvConfig.WECHAT_MERCHANT_ID);
	public static final String WECHAT_MERCHANT_NAME = EnvConfig.getInstance().getProperty(EnvConfig.WECHAT_MERCHANT_NAME);
	public static final String WECHAT_MERCHANT_KEY = EnvConfig.getInstance().getProperty(EnvConfig.WECHAT_MERCHANT_KEY);
	public static final String WECHAT_REDPACK_URL = EnvConfig.getInstance().getProperty(EnvConfig.WECHAT_REDPACK_URL);

	public static final String HOSTNAME = EnvConfig.getInstance().getProperty(EnvConfig.HOSTNAME);

	/**
	 * 应用Token
	 * */
	public static final String TOKEN = "jiacer";

	public static final String ENV = EnvConfig.getInstance().getProperty(EnvConfig.ENV);

	/**
	 * 应用Request请求方法
	 * */
	public class RequestMethods {
		public static final String GET = "GET";
		public static final String POST = "POST";
	}
	/**
	 * 用户来源
	 * */
		public static final String WEBCHAT = "01";
	/**
	 * 用户登录状态
	 * */
	public class LoginFlag {
		public static final String YES = "1";
		public static final String NO = "0";
	}
	/**
	 * access_token过期时间
	 * */
	public static int EXPIRESIN = 7000;

	interface Session{
		//微信登陆后，用户的openId
		String WX_USER_INFO = "WX_USER_INFO";
	}
}
