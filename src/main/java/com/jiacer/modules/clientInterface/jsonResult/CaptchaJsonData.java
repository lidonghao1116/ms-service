package com.jiacer.modules.clientInterface.jsonResult;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: CaptchaJsonData 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月15日 上午11:16:15 
*  
*/
public class CaptchaJsonData implements ModelSerializable{
	private static final long serialVersionUID = 1L;
	
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
