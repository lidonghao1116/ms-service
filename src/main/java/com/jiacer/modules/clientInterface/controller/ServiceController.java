package com.jiacer.modules.clientInterface.controller;

import java.util.Date;

import com.jiacer.modules.clientInterface.common.anno.Auth;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import com.jiacer.modules.clientInterface.service.ConstantsService;
import com.jiacer.modules.clientInterface.service.SmsService;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.entity.SmsRecordEntity;

/**
 * 服务相关接口
 */
@Controller
@RequestMapping(RequestMappingURL.SERVICE_BASE_URL)
public class ServiceController extends BaseController{
	
	@Autowired
	SmsService smsService;
	@Autowired
	ConstantsService constantsService;
	
	public boolean isAllowedIp() {
		String allowedIpsStr = constantsService.getValue(Constants.SERVICE_API_ALLOWED_IPS);
		if (StringUtils.isEmpty(allowedIpsStr)) {
			return false;
		}
		String[] allowedIps = allowedIpsStr.split(",");
		String requestIp = getIpAddr();
		Log.info("service request ip:" + requestIp);
		
		for (String ip : allowedIps) {
			if (requestIp.equals(ip.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 短信发送状态回执推送接口
	 * @param xmlStr xml字符串
	 */
	@Auth
	@RequestMapping(value = RequestMappingURL.SMS_RECEIPT_PUSH_URL, 
			method = {RequestMethod.GET, RequestMethod.POST}, 
			headers="Content-Type=application/xml")
	@ResponseBody
	public void smsReceiptPush(@RequestBody String xmlStr) {
		try {
			Log.info("接收到短信发送回执：" + xmlStr);
			Document document = DocumentHelper.parseText(xmlStr); 
			Element root = document.getRootElement();
			String smsType = root.elementText("smsType");
			if ("1".equals(smsType)) { // 手机接收状态报告
				String smsId = root.elementText("content");
				SmsRecordEntity smsRecord = smsService.getSmsRecordBySmsId(smsId);
				if (smsRecord != null) {
					String status = root.elementText("status");
					smsRecord.setResult(DBStatus.SmsResult.SEND_SUCCESS);
					if (!"0".equals(status)) { // 发送失败
						smsRecord.setResult(DBStatus.SmsResult.SEND_FAILURE);
						smsRecord.setFailedReason(root.elementText("deliverCode"));
					} else {
						smsRecord.setFailedReason("短信送达时间：" + root.elementText("recvTime"));
					}
					smsRecord.setResultConfrimTime(new Date());
					
					smsService.confirmSmsSendResult(smsRecord);
				}
			}
		} catch (Exception e) {
			Log.error("更新短信发送状态异常：", e);
		}
	}
	
	
}
