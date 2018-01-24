package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.AreasEntity;

import java.util.HashMap;
import java.util.List;

@MyBatisDao
public interface SubAreasMapper extends CrudDao<AreasEntity>{

	List<HashMap<String, String>> getCityKV();

	List<AreasEntity> getProvinces();

	List<AreasEntity> getCities(String province);

}