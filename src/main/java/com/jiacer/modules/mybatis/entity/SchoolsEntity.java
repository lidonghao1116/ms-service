package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.clientInterface.common.CityDictUtils;
import com.jiacer.modules.mybatis.model.Schools;

/**
 * 
* @ClassName: ShoolsEntity 
* @Description: 学校表
* @author 贺章鹏
* @date 2016年10月18日 下午12:08:20 
*
 */
public class SchoolsEntity extends Schools{
	private static final long serialVersionUID = 1L;

	private String learnTypes;

	public String getLearnTypes() {
		return learnTypes;
	}

	public void setLearnTypes(String learnTypes) {
		this.learnTypes = learnTypes;
	}


	public String getCityValue() {
		return CityDictUtils.get(this.getCity());
	}
	public String getProvinceValue() {
		return CityDictUtils.get(this.getProvince());
	}
	public String getAreaValue() {
		return CityDictUtils.get(this.getArea());
	}

	public String getSchoolAddressWholeText(){
		return this.getProvinceValue() + this.getCityValue() + this.getAreaValue() + this.getSchoolAddress();
	}
}
