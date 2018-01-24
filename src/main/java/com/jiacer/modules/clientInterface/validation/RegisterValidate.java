package com.jiacer.modules.clientInterface.validation;

import com.jiacer.modules.clientInterface.bean.form.UserRegisteForm;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.utils.*;

import javax.servlet.http.HttpSession;

/** 
* @ClassName: RegisterValidate 
* @Description: 学员注册数据交易
* @author 贺章鹏
* @date 2016年11月16日 上午10:15:28 
*  
*/
public class RegisterValidate{

	//注册第一步参数校验
	public static JsonResult firstRegisterValidate(UserRegisteForm form){

		if(StringUtils.isEmpty(form.getMobile())){
			return JsonResult.failure(ErrorCode.REG_MOBILE_EMPTY);
		}
		
		if(!BaseValidate.isMobile(form.getMobile())){
			return JsonResult.failure(ErrorCode.REG_MOBILE_ERROR);
		}

		if(StringUtils.isEmpty(form.getCaptchaCode())){
			return JsonResult.failure(ErrorCode.REG_CAPTCHA_EMPTY);
		}

		if(StringUtils.isEmpty(form.getSmsCode())){
			return JsonResult.failure(ErrorCode.REG_SMS_EMPTY);
		}
		
		return JsonResult.success(null);
	}
	
	//注册第二步参数校验
	public static JsonResult secRegisterValidate(UserRegisteForm form){
		
		if(StringUtils.isEmpty(form.getSmsCode())){
			return JsonResult.failure(ResultCode.PARAMS_ERROR);
		}
		return JsonResult.success(null);
	}
}
