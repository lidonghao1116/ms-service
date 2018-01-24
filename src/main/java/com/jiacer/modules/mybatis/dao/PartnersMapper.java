package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.PartnersEntity;

@MyBatisDao
public interface PartnersMapper extends CrudDao<PartnersEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);

	/**
	 * @param map
	 * @return
	 */
	List<PartnersEntity> getPageList(Map<Object, Object> map);
}