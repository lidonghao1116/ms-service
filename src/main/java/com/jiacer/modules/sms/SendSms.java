package com.jiacer.modules.sms;

public interface SendSms {

	/**
	 * 发送短信
	 * @param to 发送对象手机号，多个用英文逗号分隔，上限100个
	 * @param templateId 模板ID
	 * @param datas 参数
	 * @return
	 */
	public SmsSendResult send(String to, String templateId, String[] datas);
}
