package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.mybatis.model.CfgParam;

/**
 * 
* @ClassName: CfgParamEntity 
* @Description: 参数表
* @author 贺章鹏
* @date 2016年10月18日 下午12:05:15 
*
 */
public class CfgParamEntity extends CfgParam{

	private static final long serialVersionUID = 1L;
	
	private List<CfgParamEntity> childList;

	public List<CfgParamEntity> getChildList() {
		return childList;
	}

	public void setChildList(List<CfgParamEntity> childList) {
		this.childList = childList;
	}

}
