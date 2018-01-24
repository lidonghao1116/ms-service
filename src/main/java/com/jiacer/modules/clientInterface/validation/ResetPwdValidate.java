package com.jiacer.modules.clientInterface.validation;

import com.jiacer.modules.clientInterface.bean.form.ResetPwdForm;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;

/** 
* @ClassName: ResetPwdValidate 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月16日 下午12:31:10 
*  
*/
public class ResetPwdValidate {
	//注册第一步参数校验
		public static JsonResult resetParamsValidate(ResetPwdForm form){
			
			if(StringUtils.isEmpty(form.getMobile())){
				return JsonResult.failure(ResultCode.PARAMS_ERROR);
			}
			
			if(BaseValidate.isMobile(form.getMobile())){
				return JsonResult.failure(ResultCode.PARAMS_ERROR);
			}
			
			if(StringUtils.isEmpty(form.getPassword())){
				return JsonResult.failure(ResultCode.PARAMS_ERROR);
			}
			
			if(StringUtils.isEmpty(form.getCaptchaCode())){
				return JsonResult.failure(ResultCode.PARAMS_ERROR);
			}
			
			return JsonResult.success(null);
		}
}
