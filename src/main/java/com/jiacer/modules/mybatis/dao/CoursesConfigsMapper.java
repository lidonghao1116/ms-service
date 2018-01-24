package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CoursesConfigsEntity;

@MyBatisDao
public interface CoursesConfigsMapper extends CrudDao<CoursesConfigsEntity>{

	/**
	 * @param courseId
	 * @return
	 */
	List<CoursesConfigsEntity> getOptionsByCourseId(Integer courseId);
    
}