package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.BrokerageRulesEntity;

@MyBatisDao
public interface BrokerageRulesMapper extends CrudDao<BrokerageRulesEntity>{

	/**
	 * @param rule
	 */
	BrokerageRulesEntity getByKeys(BrokerageRulesEntity rule);

	/**
	 * @param schemeId
	 * @return
	 */
	List<BrokerageRulesEntity> getRulesBySchemeId(Integer schemeId);
}