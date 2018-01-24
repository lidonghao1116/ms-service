package com.jiacer.modules.common.httpServer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHelper {
	
	private static Logger logger = LoggerFactory.getLogger(RequestHelper.class);
	
	static class SingletonHolder {
		static RequestHelper instance = new RequestHelper();
	}

	public static RequestHelper getInstance() {
		return SingletonHolder.instance;
	}

	private RequestHelper() {
	}
	
	/**
	 * 请求参数放在请求体中
	 */
	public static final int CONTENT_TYPE_BODY = 1;
	/**
	 * 请求参数已form表达形式提交
	 */
	public static final int CONTENT_TYPE_FORM = 2;

	public static final String CHARSET_GBK = "GBK";
	
	public static final String CHARSET_UTF_8 = "UTF-8";

	public static PaymentResponse doRequest(MessageBuilder message) {
		return doRequest(message, CONTENT_TYPE_BODY, CHARSET_GBK);
	}
	
	public static PaymentResponse doRequestByForm(MessageBuilder message) {
		return doRequest(message, CONTENT_TYPE_FORM, CHARSET_GBK);
	}

	public static PaymentResponse doRequest(MessageBuilder message, Integer contentType, String charset) {
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = createSSLClientDefault();
			
			HttpPost httpPost = new HttpPost(message.getRequestUrl());
			
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(20000).setConnectTimeout(10000).setConnectionRequestTimeout(10000)
					.setExpectContinueEnabled(false).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			
			String requestMessage = message.getRequestMessage();
			logger.info("请求参数：");
			logger.info(requestMessage);
			
			if (contentType == null) {
				contentType = CONTENT_TYPE_BODY;
			}
			Charset cs = null;
			if (charset == null || "".equals(charset.trim())) {
				cs = Charset.forName(CHARSET_UTF_8);
			} else {
				cs = Charset.forName(charset);
			}
			
			if (contentType == CONTENT_TYPE_BODY) {
				ContentType contType = ContentType.create("text/plain", cs);
				StringEntity stringEntity = new StringEntity(requestMessage, contType);
				httpPost.setEntity(stringEntity);
			} else if (contentType == CONTENT_TYPE_FORM) {
				String[] pairs = requestMessage.split("&");
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				NameValuePair param = null;
				String[] nameValue = null;
				for (String pair : pairs) {
					nameValue = pair.split("=");
					if (nameValue.length == 2) {
						param = new BasicNameValuePair(nameValue[0], nameValue[1]);
						params.add(param);
					}
				}
				
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, cs);
				httpPost.setEntity(formEntity);
			} else {
				throw new IllegalArgumentException("未支持的请求方式");
			}
			
	        response = httpClient.execute(httpPost);
	        StatusLine status = response.getStatusLine();
	        PaymentResponse result = null;
	        if (status.getStatusCode() == HttpStatus.SC_OK) {
	        	String respContent = EntityUtils.toString(response.getEntity() , charset).trim();
	        	logger.info("请求返回：");
	        	logger.info(respContent);
	        	result = message.getResponseBean(respContent);
	        } else if (status.getStatusCode() == HttpStatus.SC_NOT_FOUND){
	        	logger.error("返回失败，返回码==" + status.getStatusCode());
	        	result = new PaymentResponse();
	        	result.setRequestResult(HttpResponseStatus.HTTP_REQUEST_FAILED);
	        } else {
	        	logger.error("返回失败，返回码==" + status.getStatusCode());
	        	result = new PaymentResponse();
	        	result.setRequestResult(HttpResponseStatus.UNNORMAL_RESPONSE);
	        }
	        return result;
		} catch (Exception e) {
			logger.error("发送请求异常，", e);
			PaymentResponse result = new PaymentResponse();
        	result.setRequestResult(HttpResponseStatus.UNNORMAL_RESPONSE);
        	return result;
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					logger.error("连接关闭异常，", e);
					PaymentResponse result = new PaymentResponse();
		        	result.setRequestResult(HttpResponseStatus.UNNORMAL_RESPONSE);
		        	return result;
				}
		}
	}
	
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					trustStore, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
			ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
			registryBuilder.register("http", plainSF);
			registryBuilder.register("https", sslsf);
			Registry<ConnectionSocketFactory> registry = registryBuilder.build();
			
			ConnectionConfig connectionConfig = ConnectionConfig.custom()
					.setCharset(Charset.forName(CHARSET_GBK))
					.build(); 
			SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(30000).build();
			//设置连接管理器
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
			
			connManager.setDefaultConnectionConfig(connectionConfig);
			connManager.setDefaultSocketConfig(socketConfig);
			
			HttpClientBuilder builder = HttpClients.custom();
			builder.setSSLSocketFactory(sslsf);
			builder.setConnectionManager(connManager);
			return builder.build();
		} catch (KeyManagementException e) {
			logger.error("createSSLClientDefault: ", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("createSSLClientDefault: ", e);
		} catch (KeyStoreException e) {
			logger.error("createSSLClientDefault: ", e);
		}
		return HttpClients.createDefault();
	}
}
