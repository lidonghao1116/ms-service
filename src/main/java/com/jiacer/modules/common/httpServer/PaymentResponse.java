package com.jiacer.modules.common.httpServer;

import java.util.Map;

/**
 * 响应结果对象
 *
 */
public class PaymentResponse {
	
	/**
	 * 保存http请求结果
	 */
	private HttpResponseStatus requestResult;
	/**
	 * 保存接口返回的数据的状态，仅表示支付接口数据是否正常返回
	 * 当前有三个状态：成功；异常错误；验签失败
	 * 接口的业务结果根据不同接口从resultData中取值判断
	 */
	private MessageStatus result;
	private String resultDesc;
	private String originalString;
	private Map<String, Object> resultData;
	
	public HttpResponseStatus getRequestResult() {
		return requestResult;
	}
	public void setRequestResult(HttpResponseStatus requestResult) {
		this.requestResult = requestResult;
	}
	public MessageStatus getResult() {
		return result;
	}
	public void setResult(MessageStatus result) {
		this.result = result;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getOriginalString() {
		return originalString;
	}
	public void setOriginalString(String originalString) {
		this.originalString = originalString;
	}
	public Map<String, Object> getResultData() {
		return resultData;
	}
	public void setResultData(Map<String, Object> resultData) {
		this.resultData = resultData;
	}
}
