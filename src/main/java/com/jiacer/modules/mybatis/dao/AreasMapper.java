package com.jiacer.modules.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.AreasEntity;

@MyBatisDao
public interface AreasMapper extends CrudDao<AreasEntity>{

	/**
	 * @return
	 */
	List<AreasEntity> getAreas();

	List<HashMap<Integer, String>> getCityKV();
}