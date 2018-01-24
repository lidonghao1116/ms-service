package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.cache.RegisterTryTimesCache;
import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.anno.Guest;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.common.conts.RedisConst;
import com.jiacer.modules.clientInterface.jsonResult.CaptchaJsonData;
import com.jiacer.modules.clientInterface.service.SmsService;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/** 
* @ClassName: CommonController 
* @Description: 公共接口
* @author 贺章鹏
* @date 2016年11月15日 上午11:07:47 
*  
*/
@RestController
@RequestMapping(RequestMappingURL.COMMON_BASE_URL)
public class CommonController {

	private static final Logger log = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private SmsService smsService;

	@Resource(name = "redisTemplate")
	private StringRedisTemplate redis;


	/**
	 * 获取验证码
	 * @param request
	 * @param response
     * @return
     */
	@Guest
	@RequestMapping(value = RequestMappingURL.CAPTCHA_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		CaptchaJsonData jsonData = new CaptchaJsonData();
		try {
			RandomValidateCode randomCode = new RandomValidateCode();
			String captcha = randomCode.getRandcode(request, response);
			if (StringUtils.isNotBlank(captcha)) {
				jsonData.setCaptcha(captcha);
				CaptchaCode captchaCode = (CaptchaCode)SessionManager.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
				log.info("captcha code : " + captchaCode.getCode());
				redis.opsForValue().set(RedisConst.Common.CAPTCHA_CODE.getKey(), captchaCode.getCode(), 10, TimeUnit.MINUTES);
				return JsonResult.success(jsonData);
			} else {
				return JsonResult.failure(ResultCode.User.CAPTCHA_FAILURE);
			}
		} catch (Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}
	}

	@Guest
	@RequestMapping(value = RequestMappingURL.SEND_SMS_CODE_URL, method = RequestMethod.GET)
	@ResponseBody
	public JsonResult sendRegisterSmsCode(String mobile) {
		if(org.apache.commons.lang3.StringUtils.isEmpty(mobile)){
			log.error("手机号不能为空");
			return JsonResult.failure(ErrorCode.REG_MOBILE_EMPTY);
		}
		try {
			HttpSession session = SessionManager.getSession();

			// 判断手机号尝试注册次数是否达到每天上限
			if (RegisterTryTimesCache.isOutLimit(mobile)) {
				return JsonResult.failure(ResultCode.User.TRY_REGISTER_OUT_LIMIT);
			}

			// 发送验证码短信
			SmsCode smsCodeObj = smsService.sendRegisterSmsCode(mobile);
			if (smsCodeObj == null) {
				return JsonResult.failure(ResultCode.User.SMS_CODE_FAILURE);
			}

			log.info("sms code : "+ smsCodeObj.getCode());
			redis.opsForValue().set(RedisConst.Common.SMS_CODE.getKey(), smsCodeObj.getCode(), 10, TimeUnit.MINUTES);

			RegisterTryTimesCache.addOneCount(mobile);

			return JsonResult.success(null);
		} catch (Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}
	}

	@Guest
	@RequestMapping(value = RequestMappingURL.CAPTCHA_CHECK_URL, method = RequestMethod.GET)
	@ResponseBody
	public JsonResult validateImgCode(String code){
		// 校验图片验证码
		String captcha = redis.opsForValue().get(RedisConst.Common.CAPTCHA_CODE.getKey());
		log.info(String.format("register redis-param captcha : %s-%s " , captcha, code));
		if (StringUtils.isBlank(captcha)) { // 验证码过期
			return JsonResult.failure(ErrorCode.REG_CAPTCHA_EXPIRED);
		} else if (!captcha.toUpperCase().equals(code.toUpperCase())) { // 验证码不正确
			return JsonResult.failure(ErrorCode.REG_CAPTCHA_ERROR);
		}
		return JsonResult.success("SUCCESS");
	}

}
