package com.jiacer.modules.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import com.jiacer.modules.clientInterface.common.conts.Constants;


/**
 * 系统环境配置类
 * 
 * @author sk.chen
 *
 */
public class EnvConfig {
	public static final String ENV = "env";

	public static final String WECHAT_APPID = "wechat.appid";
	public static final String WECHAT_APPSECRET = "wechat.appsecret";

	public static final String WECHAT_MERCHANT_ID = "wechat.merchant_id";
	public static final String WECHAT_MERCHANT_NAME = "wechat.merchant_name";
	public static final String WECHAT_REDPACK_URL = "wechat.redpack_url";
	public static final String WECHAT_MERCHANT_KEY = "wechat.merchant_key";
	public static final String HOSTNAME = "hostname";

	private String path = Constants.ENVFILEPATH;
	private Properties pro = null;

	private EnvConfig() {
		init();
	}

	public void init() {
		pro = new Properties();
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream(path);
			Reader reader = new InputStreamReader(in, Constants.CHARSET);
			pro.load(reader);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static EnvConfig env = null;

	public static EnvConfig getInstance() {
		if (env == null) {
			env = new EnvConfig();
		}
		return env;
	}

	public final String getProperty(String key) {
		String result = null;
		result = pro.getProperty(key);
		return result;
	}

}
