package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.mybatis.model.BrokerageScheme;

/** 
* @ClassName: BrokerageSchemeEntity 
* @Description: 提成方案表
* @author 贺章鹏
* @date 2016年10月21日 下午2:09:45 
*  
*/
public class BrokerageSchemeEntity extends BrokerageScheme{

	private static final long serialVersionUID = 1L;

	private List<BrokerageRulesEntity> rules;

	public List<BrokerageRulesEntity> getRules() {
		return rules;
	}

	public void setRules(List<BrokerageRulesEntity> rules) {
		this.rules = rules;
	}
	
}
