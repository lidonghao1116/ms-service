package com.jiacer.modules.clientInterface.jsonResult;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: TimesJsonData 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午2:46:34 
*  
*/
public class TimesJsonData implements ModelSerializable{
	private static final long serialVersionUID = 1L;

	private String timesCode;//代码code
	
	private String timesName;//名称
	
	public String getTimesCode() {
		return timesCode;
	}

	public void setTimesCode(String timesCode) {
		this.timesCode = timesCode;
	}

	public String getTimesName() {
		return timesName;
	}

	public void setTimesName(String timesName) {
		this.timesName = timesName;
	}
}
