package com.jiacer.modules.common.httpServer;


/**
 * 发送请求类,已form表单方式发送请求
 * @author lenovo
 *
 */
public class FormPostRequestSender implements RequestSender{
	
	private MessageBuilder message;
	
	public FormPostRequestSender(MessageBuilder message) {
		this.message = message;
	}

	@Override
	public PaymentResponse doRequest() {
		return RequestHelper.doRequestByForm(message);
	}

	public MessageBuilder getMessage() {
		return message;
	}
}
