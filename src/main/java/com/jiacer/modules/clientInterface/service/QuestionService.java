package com.jiacer.modules.clientInterface.service;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.mybatis.entity.QuestionsEntity;
import com.jiacer.modules.mybatis.entity.UserAnswersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.extend.UserScoreBean;

public interface QuestionService {

	/**
	 * 处理用户获取的问题
	 * @param id
	 * @param typeId
	 * @return
	 * @throws Exception 
	 */
	Map<String,Object> getQuestions(Integer userId, Integer typeId) throws Exception;

	/**
	 * 保存用户答案
	 * @param userId
	 * @param typeId
	 * @param batchId
	 * @param answers
	 */
	void saveQuestions(Integer userId, Integer typeId, Integer batchId, List<QuestionsEntity> answers);

	/**
	 * 获取用户答题分数
	 * @param user
	 * @param batchId
	 * @return
	 */
	UserScoreBean getUserScore(UserBaseInfoEntity user, Integer batchId);

	/**
	 * 获取用户答题答题卡
	 * @param userId
	 * @param batchId
	 * @return
	 */
	List<UserAnswersEntity> getAnswerSheet(Integer userId, Integer batchId);

	/**
	 * 获取用户答题详情和问题正确答案
	 * @param id
	 * @return
	 */
	Map<String, Object> getQWDetails(Integer id);


    Map<String,Object> dealGetQuestionsByCourseId(Integer id, Integer courseId);

    List<UserScoreBean> getUserAllScore(UserBaseInfoEntity user, Integer courseId);
}
