package com.jiacer.modules.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.wechat.common.WechatLog;
import com.jiacer.modules.wechat.core.X509TrustManagerImpl;

import net.sf.json.JSONObject;

/**
 * 微信工具类
 * @author sk.chen
 */
public class WechatUtils
{	
	/**
	 * 发起https请求并获取结果，用于获取AccessToken
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param reqParam
	 *            提交的数据
	 * @return
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String reqParam)
	{
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try
		{
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new X509TrustManagerImpl() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if (Constants.RequestMethods.GET.equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != reqParam)
			{
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(reqParam.getBytes(Constants.CHARSET));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, Constants.CHARSET);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null)
			{
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			WechatLog.info("wechat response string: " + buffer.toString());
			jsonObject = JSONObject.fromObject(buffer.toString());
		}
		catch (ConnectException e)
		{
			WechatLog.error("connect to wechat server error:", e);
		}
		catch (Exception e)
		{
			WechatLog.error("wechat request error:", e);
		}
		return jsonObject;
	}

}
