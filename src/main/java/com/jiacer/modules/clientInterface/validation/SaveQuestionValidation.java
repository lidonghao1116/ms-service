package com.jiacer.modules.clientInterface.validation;

import com.jiacer.modules.clientInterface.bean.SaveQuestionBean;
import com.jiacer.modules.common.config.Global;

public class SaveQuestionValidation {

	public static boolean validateParams(SaveQuestionBean bean) {
		if(bean==null){
			return false;
		}
		if(bean.getTypeId()==null){
			return false;
		}
		if(bean.getBatchId()==null){
			return false;
		}
		
		return true;
	}
}
