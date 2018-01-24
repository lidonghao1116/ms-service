package com.jiacer.modules.clientInterface.service.impl;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.clientInterface.service.SmsService;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.utils.SmsCode;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.dao.SmsRecordMapper;
import com.jiacer.modules.mybatis.entity.SmsRecordEntity;
import com.jiacer.modules.sms.SendSms;
import com.jiacer.modules.sms.SmsSendResult;
import com.jiacer.modules.sms.SmsTemplates;


@Service("smsService")
public class SmsServiceImpl extends BaseService implements SmsService {
	
	@Autowired
	SendSms sendSms;
	@Autowired
	SmsRecordMapper smsRecordDao;
	
	@Override
	public SmsCode sendRegisterSmsCode(String mobile) {
		SmsCode smsCode = null;
		String code = String.valueOf(new Random().nextInt(9000) + 1000); // 注册验证码4位随机数字
		int liveMinites = 10; // 有效时间10分钟
		String[] datas = new String[]{code, liveMinites + ""};
		String templateId = SmsTemplates.REG_SMS_CODE; // 模板ID
		String content = generateSmsContent(templateId, datas); // 短信内容
		
		SmsRecordEntity entity = new SmsRecordEntity();
		entity.setMobile(mobile);
		entity.setContentType(DBStatus.SmsContentType.REG_SMS_CODE);
		entity.setContent(content);
		
		// 发送短信
		SmsSendResult sendResult = sendSms.send(mobile, templateId, datas);
		if (SmsSendResult.SUCCESS.equals(sendResult.getResultCode())) { // 发送成功
			entity.setResult(DBStatus.SmsResult.SUBMIT_SUCCESS);
			entity.setSmsId(sendResult.getSmsMessageSid());
			smsCode = new SmsCode(mobile, code, liveMinites);
		} else { // 发送失败
			entity.setResult(DBStatus.SmsResult.SUBMIT_FAILURE);
			entity.setFailedReason(sendResult.getResultMsg());
		}
		
//		entity.setResult(DBStatus.SmsResult.SUBMIT_SUCCESS);
//		entity.setSmsId("");
//		smsCode = new SmsCode(mobile, code, liveMinites);
		
		try {
			// 保存发送短信记录
			smsRecordDao.insert(entity);
		} catch (Exception e) {
			Log.error("保存发送短信记录异常", e);
		}
		
		return smsCode;
	}
	
	@Override
	public SmsCode sendResetPwdSmsCode(String mobile) {
		SmsCode smsCode = null;
		String code = String.valueOf(new Random().nextInt(900000) + 100000); // 重置密码验证码6位随机数字
		int liveMinites = 10; // 有效时间10分钟
		String[] datas = new String[]{code, liveMinites + ""};
		String templateId = SmsTemplates.RESET_PWD_SMS_CODE; // 模板ID
		String content = generateSmsContent(templateId, datas); // 短信内容
		
		SmsRecordEntity entity = new SmsRecordEntity();
		entity.setMobile(mobile);
		entity.setContentType(DBStatus.SmsContentType.RESET_PWD_SMS_CODE);
		entity.setContent(content);
		
		// 发送短信
		SmsSendResult sendResult = sendSms.send(mobile, templateId, datas);
		if (SmsSendResult.SUCCESS.equals(sendResult.getResultCode())) { // 发送成功
			entity.setResult(DBStatus.SmsResult.SUBMIT_SUCCESS);
			entity.setSmsId(sendResult.getSmsMessageSid());
			smsCode = new SmsCode(mobile, code, liveMinites);
		} else { // 发送失败
			entity.setResult(DBStatus.SmsResult.SUBMIT_FAILURE);
			entity.setFailedReason(sendResult.getResultMsg());
		}
		
		try {
			// 保存发送短信记录
			smsRecordDao.insert(entity);
		} catch (Exception e) {
			Log.error("保存发送短信记录异常", e);
		}
		
		return smsCode;
	}

	@Override
	public void confirmSmsSendResult(SmsRecordEntity entity) {
		smsRecordDao.update(entity);
	}

	@Override
	public SmsRecordEntity getSmsRecordBySmsId(String smsId) {
		if (StringUtils.isBlank(smsId)) {
			return null;
		}
		List<SmsRecordEntity> list = smsRecordDao.getBySmsId(smsId);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	

	/**
	 * 生成短信内容
	 * @param templateId 短信模板ID
	 * @param datas 参数
	 * @return
	 */
	private String generateSmsContent(String templateId, String[] datas) {
		String content = SmsTemplates.getContent(templateId); // 模板内容
		if (StringUtils.isNotBlank(content)) {
			int len = datas.length;
			String key = null;
			String value = null;
			for (int i = 0; i < len; i ++) {
				key = "{" + (i + 1) + "}";
				value = datas[i] == null ? "" : datas[i];
				try {
					content = content.replace(key, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return content;
	}

}
