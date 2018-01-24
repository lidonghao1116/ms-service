package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserAnswersBatchEntity;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;
import com.jiacer.modules.mybatis.model.UserAnswersBatch;

@MyBatisDao
public interface UserAnswersBatchMapper extends CrudDao<UserAnswersBatchEntity>{

	/**
	 * @param params
	 * @return
	 */
	List<LearnRecordEntity> getLearnRecord(Map<String, Object> params);
	
	/**
	 * 获取用户最高分数
	 * @param params
	 * @return
	 */
	LearnRecordEntity getUserMaxRecords(Map<String, Object> params);

    List<UserAnswersBatch> getByUserId(Map<String, Integer> params);
}