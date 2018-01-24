package com.jiacer.modules.common.httpServer;


/**
 * 发送请求类，将参数置于请求体中
 * @author lenovo
 *
 */
public class BodyPostRequestSender implements RequestSender{
	
	private MessageBuilder message;
	
	public BodyPostRequestSender(MessageBuilder message) {
		this.message = message;
	}

	@Override
	public PaymentResponse doRequest() {
		return RequestHelper.doRequest(message);
	}

	public MessageBuilder getMessage() {
		return message;
	}

	public void setMessage(MessageBuilder message) {
		this.message = message;
	}

}
