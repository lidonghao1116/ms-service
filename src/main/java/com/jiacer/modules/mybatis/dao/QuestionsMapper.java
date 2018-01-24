package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.QuestionsEntity;

@MyBatisDao
public interface QuestionsMapper extends CrudDao<QuestionsEntity>{

	QuestionsEntity getQuestionsById(Integer questionsId);

	List<QuestionsEntity> getQuestions(QuestionsEntity bean);
    
}