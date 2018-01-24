package com.jiacer.modules.common.httpServer;


public interface MessageBuilder {
	/**
	 * 生成请求报文
	 * @return
	 */
	public String getRequestMessage();
	/**
	 * 生成请求地址
	 * @return
	 */
	public String getRequestUrl();
	/**
	 * 生成响应结果对象
	 * @param respString
	 * @return
	 */
	public PaymentResponse getResponseBean(String respString);
	
}
