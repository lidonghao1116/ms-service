package com.jiacer.modules.clientInterface.service.impl;

import com.google.common.collect.Maps;
import com.jiacer.modules.clientInterface.service.CourseOnlineService;
import com.jiacer.modules.clientInterface.service.QuestionService;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.JsonUtils;
import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.config.DBStatus.QuestionScore;
import com.jiacer.modules.mybatis.dao.*;
import com.jiacer.modules.mybatis.entity.*;
import com.jiacer.modules.mybatis.entity.extend.UserScoreBean;
import com.jiacer.modules.mybatis.model.UserAnswersBatch;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	UserAnswersBatchMapper answerBatchDao;
	
	@Autowired
	QuestionsMapper questionsDao;
	
	@Autowired
	UserAnswersMapper userAnswersDao;
	
	@Autowired
	UserAnswersBatchMapper userAnswersBatchDao;
	
	@Autowired
	LearnTypesMapper learnTypesDao;

	@Autowired
	private CourseOnlineMapper courseOnlineDao;

	@Autowired
	private CourseOnlineService courseOnlineService;
	
	@Override
	@Deprecated
	public Map<String,Object> getQuestions(Integer courseId, Integer num) throws Exception{
		if(num == null || num == 0){
			num = 20;
		}
		CourseOnlineEntity coe = courseOnlineDao.getById(courseId);
		coe = courseOnlineService.sumQuestionCountAndScores(courseId, coe);

		QuestionsEntity bean=new QuestionsEntity();
		bean.setCourseId(courseId);
		bean.setQuestionType(DBStatus.QuestionScore.SINGLE);
		List<QuestionsEntity> singleList=questionsDao.getQuestions(bean);

		bean.setQuestionType(DBStatus.QuestionScore.RIGHT_AND_WRONG);
		List<QuestionsEntity> rwList=questionsDao.getQuestions(bean);

		bean.setQuestionType(DBStatus.QuestionScore.MULTI);
		List<QuestionsEntity> multi=questionsDao.getQuestions(bean);

		//返回用户问题

		List<QuestionsEntity> userQuestions=new ArrayList<QuestionsEntity>();
		if(singleList != null && singleList.size()>0) {
			Collections.shuffle(singleList);
			userQuestions.addAll(singleList.subList(0, Integer.valueOf(coe.getSingleCount())));
		}
		if(rwList != null && rwList.size()>0) {
			Collections.shuffle(rwList);
			userQuestions.addAll(rwList.subList(0, Integer.valueOf(coe.getJudgeCount())));
		}
		//小练习中不包含多选题
//		ArrayList<QuestionsEntity> multiQuestions=new ArrayList<QuestionsEntity>();
//		if(multi != null && multi.size()>0) {
//			Collections.shuffle(multi);
//			userQuestions.addAll(multi.subList(0, Integer.valueOf(coe.getMultiCount())));
//		}

		Collections.shuffle(userQuestions);
		Map<String, Object> resultMap=Maps.newHashMap();
		resultMap.put("num", userQuestions.size());
		if(userQuestions.size() > num){
			resultMap.put("questions", userQuestions.subList(0,num));
		}else{
			resultMap.put("questions", userQuestions);
		}

		return resultMap;
	}

	@Override
	public Map<String,Object> dealGetQuestionsByCourseId(Integer userId, Integer courseId) {

		CourseOnlineEntity coe = courseOnlineDao.getById(courseId);
		coe = courseOnlineService.sumQuestionCountAndScores(courseId, coe);

		//1.保存批次
		UserAnswersBatchEntity entity=new UserAnswersBatchEntity();
		Date thisDay=new Date();
		entity.setUserId(userId);
		entity.setIsFinished(Global.NO);
		entity.setStartTime(thisDay);
		entity.setEndTime(DateUtils.addMinutes(thisDay,Integer.valueOf(coe.getCourseTime())));
		entity.setCourseId(courseId);
		answerBatchDao.insert(entity);
		//获取对应批次问题
		QuestionsEntity bean=new QuestionsEntity();
		bean.setCourseId(courseId);
		bean.setQuestionType(DBStatus.QuestionScore.SINGLE);
		List<QuestionsEntity> singleList=questionsDao.getQuestions(bean);

		bean.setQuestionType(DBStatus.QuestionScore.RIGHT_AND_WRONG);
		List<QuestionsEntity> rwList=questionsDao.getQuestions(bean);

		bean.setQuestionType(DBStatus.QuestionScore.MULTI);
		List<QuestionsEntity> multi=questionsDao.getQuestions(bean);

		//返回用户问题

		List<QuestionsEntity> userQuestions=new ArrayList<QuestionsEntity>();
		if(singleList != null && singleList.size()>0) {
			Collections.shuffle(singleList);
			userQuestions.addAll(singleList.subList(0, Integer.valueOf(coe.getSingleCount())));
		}
		if(rwList != null && rwList.size()>0) {
			Collections.shuffle(rwList);
			userQuestions.addAll(rwList.subList(0, Integer.valueOf(coe.getJudgeCount())));
		}
		ArrayList<QuestionsEntity> multiQuestions=new ArrayList<QuestionsEntity>();
		if(multi != null && multi.size()>0) {
			Collections.shuffle(multi);
			userQuestions.addAll(multi.subList(0, Integer.valueOf(coe.getMultiCount())));
		}

		Collections.shuffle(userQuestions);

		Map<String, Object> resultMap=Maps.newHashMap();
		resultMap.put("batch", entity);
		resultMap.put("questions", userQuestions);
		return resultMap;
	}

	@Override
	public void saveQuestions(Integer userId, Integer typeId, Integer batchId, List<QuestionsEntity> answers) {
		
		Date thisDay=new Date();
		UserAnswersEntity userAnwers=null;
		BigDecimal score=BigDecimal.ZERO;
		UserAnswersBatch ent = userAnswersBatchDao.getById(batchId);
		if(ent == null){
			log.error("UserAnswersBatch is null : batchId=" + batchId);
			throw new RuntimeException("保存答题失败。 ");
		}
		CourseOnlineEntity coe = courseOnlineDao.getById(ent.getCourseId());
		if(coe == null){
			log.error("答题的课程查询失败= batchId=" + batchId + "=courseId:" + ent.getCourseId());
			throw new RuntimeException("保存答题失败。 ");
		}

		for(QuestionsEntity bean:answers){
			userAnwers=new UserAnswersEntity();
			userAnwers.setAnswerKeys(bean.getCorrectAnswers());
			userAnwers.setAddTime(thisDay);
			userAnwers.setBatchId(batchId);
			userAnwers.setQuestionsId(bean.getId());
			userAnwers.setUserId(userId);
			userAnswersDao.insert(userAnwers);
			//判断问题是否是正确答案计算分数
			Map<String, Object> result=this.dealQuestionAnswer(bean.getId(),bean.getCorrectAnswers(), coe);
			if(result != null){
				log.info("result add scores: " + result.get("score"));
				score=score.add((BigDecimal) result.get("score"));
			}
		}
		
		UserAnswersBatchEntity userAnswersBatch=new UserAnswersBatchEntity();
		userAnswersBatch.setId(batchId);
		userAnswersBatch.setFinishTime(thisDay);
		userAnswersBatch.setIsFinished(Global.YES);
		userAnswersBatch.setScore(score);
		userAnswersBatchDao.update(userAnswersBatch);
		
	}

	@Override
	public UserScoreBean getUserScore(UserBaseInfoEntity user, Integer batchId) {
		
		UserAnswersBatch userAnswersBatch=userAnswersBatchDao.getById(batchId);
		CourseOnlineEntity ent = courseOnlineDao.getById(userAnswersBatch.getCourseId());
		UserScoreBean bean=new UserScoreBean();
		bean.setBatchId(userAnswersBatch.getId());
		bean.setScore(userAnswersBatch.getScore());
		bean.setStartTime(DateFormatUtils.format(userAnswersBatch.getStartTime().getTime(),"yyyy-MM-dd HH:mm:ss"));
		bean.setIsFinished(userAnswersBatch.getIsFinished());
		bean.setUserName(user.getUserName());
		bean.setCertNo(user.getCertNo());
		bean.setUseTime(DateUtils.getHssOfTwoDate(userAnswersBatch.getStartTime(),userAnswersBatch.getFinishTime()==null?userAnswersBatch.getEndTime():userAnswersBatch.getFinishTime()));
		if(ent != null){
			bean.setTypeName(ent.getCourseName());
		}
		log.info("getUserScore ====== "+ JsonUtils.toJson(bean));
		return bean;
	}

	@Override
	public List<UserScoreBean> getUserAllScore(UserBaseInfoEntity user, Integer courseId) {

		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("userId",user.getId());
		param.put("courseId",courseId);
		List<UserAnswersBatch> userAnswers=userAnswersBatchDao.getByUserId(param);
		List<UserScoreBean> beans = new ArrayList<UserScoreBean>();
		for(UserAnswersBatch batch : userAnswers){
			LearnTypesEntity typeBean=learnTypesDao.getById(batch.getTypeId());
			UserScoreBean bean=new UserScoreBean();
			bean.setBatchId(batch.getId());
			bean.setScore(batch.getScore());
			bean.setUserName(user.getUserName());
			bean.setIsFinished(bean.getIsFinished());
			bean.setStartTime(DateFormatUtils.format(batch.getStartTime().getTime(),"yyyy-MM-dd HH:mm:ss"));
			bean.setCertNo(user.getCertNo());
			bean.setUseTime(DateUtils.getHssOfTwoDate(batch.getStartTime(),batch.getFinishTime()==null?batch.getEndTime():batch.getFinishTime()));
			if(typeBean != null){
				bean.setTypeName(typeBean.getTypeName());
			}
			beans.add(bean);
		}

		return beans;
	}

	@Override
	public List<UserAnswersEntity> getAnswerSheet(Integer userId, Integer batchId) {
		UserAnswersEntity bean=new UserAnswersEntity();
		bean.setBatchId(batchId);
		bean.setUserId(userId);
		UserAnswersBatch uabe = userAnswersBatchDao.getById(batchId);
		CourseOnlineEntity coe = courseOnlineDao.getById(uabe.getCourseId());
		List<UserAnswersEntity> list=userAnswersDao.findAllList(bean);
		for(UserAnswersEntity userAnswers:list){
			//判断问题是否是正确答案计算分数
			Map<String, Object> result=this.dealQuestionAnswer(userAnswers.getQuestionsId(),userAnswers.getAnswerKeys(), coe);
			userAnswers.setIsCorrect((String) result.get("isCorrect"));
		}
		return list;
	}

	@Override
	public Map<String, Object> getQWDetails(Integer id) {
		UserAnswersEntity bean=userAnswersDao.getById(id);
		QuestionsEntity quesiotns=questionsDao.getQuestionsById(bean.getQuestionsId());
		Map<String,Object> resultMap=Maps.newHashMap();
		resultMap.put("userAnswer", bean.getAnswerKeys());
		resultMap.put("quesiotns", quesiotns);
		return resultMap;
	}

	
	public Map<String,Object> dealQuestionAnswer(Integer questionsId,String userAnswerKey, CourseOnlineEntity coe){
		Map<String, Object> result=Maps.newHashMap();
		QuestionsEntity quesiotns=questionsDao.getQuestionsById(questionsId);
		StringBuffer sb=new StringBuffer();
		for(AnswersEntity bean:quesiotns.getAnswers()){
			if(Global.YES.equals(bean.getIsAnswer())){
				sb.append(bean.getId()).append(",");
			}
		}
		String answer="";
		if(sb.length()>0){
			answer=sb.substring(0, sb.length()-1);
		}

		coe = courseOnlineService.sumQuestionCountAndScores(coe.getCourseId(),coe);
		
		if(answer.equals(userAnswerKey)){//回答正确
			result.put("isCorrect", Global.YES);
			if(quesiotns.getQuestionType().equals(QuestionScore.SINGLE)){
				result.put("score", new BigDecimal(coe.getSingleScore()));
			}else if(quesiotns.getQuestionType().equals(QuestionScore.RIGHT_AND_WRONG)){
				result.put("score", new BigDecimal(coe.getJudgeScore()));
			}else if(quesiotns.getQuestionType().equals(QuestionScore.MULTI)){
				result.put("score", new BigDecimal(coe.getMultiScore()));
			}else{
				result.put("score", BigDecimal.ZERO);
			}
		}else{
			result.put("isCorrect", Global.NO);
			result.put("score", BigDecimal.ZERO);
		}
		
		return result;
	}

}
