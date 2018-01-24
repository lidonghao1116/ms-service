package com.jiacer.modules.mybatis.dao;

import java.util.List;
import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;

@MyBatisDao
public interface LearnTypesMapper extends CrudDao<LearnTypesEntity>{

	List<LearnTypesEntity> getUserLearnTypes(LearnTypesEntity bean);

	/**
	 * @param entity
	 * @return
	 */
	List<LearnTypesEntity> getCourseList(LearnTypesEntity entity);

    List<LearnTypesEntity> getLearnListbySchoolId();
}