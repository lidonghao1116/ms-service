package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.bean.SaveQuestionBean;
import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.service.QuestionService;
import com.jiacer.modules.clientInterface.validation.SaveQuestionValidation;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.SessionUtils;
import com.jiacer.modules.mybatis.entity.UserAnswersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.extend.UserScoreBean;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 问答题目
 */
@Controller
@RequestMapping(RequestMappingURL.QUESTION_BASE_URL)
public class QuestionsController {
	
	@Autowired
	QuestionService questionService;

	/**
	 * 问答题目查询
     * @return
     */
	@RequestMapping(value = RequestMappingURL.INFO_URL, method = RequestMethod.GET)
	@ResponseBody
	public JsonResult questions(Integer courseId, Integer num) {

		if(courseId == null || courseId == 0){
			return JsonResult.failure(ErrorCode.REQUEST_PARAM_EMPTY);
		}

		
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		
		try {
			UserBaseInfo user= SessionManager.getUser();
			if(user==null){
				jsonResult=JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
			}else{
				Map<String,Object> reslut=questionService.getQuestions(courseId,num);
				jsonResult=JsonResult.success(reslut);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取用户问题失败，", e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}
		return jsonResult;
	}

	/**
	 * 答题保存
	 * @param request
	 * @param bean
     * @return
     */
	@RequestMapping(value = RequestMappingURL.SAVE_URL, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult save(HttpServletRequest request,@RequestBody SaveQuestionBean bean) {
		
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		
		if(!SaveQuestionValidation.validateParams(bean)){
			return JsonResult.failure(ResultCode.PARAMS_ERROR);
		}
		
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
				jsonResult=JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
			}else{
				questionService.saveQuestions(user.getId(),bean.getTypeId(),bean.getBatchId(),bean.getAnswers());
				jsonResult=JsonResult.success(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取保存用户答题失败，", e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}
		return jsonResult;
	}

	/**
	 * 获取分数
	 * @param request
	 * @param batchId
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.GET_SCORE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getUserScore(HttpServletRequest request,Integer batchId) {

		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);

		if(batchId==null){
			return JsonResult.failure(ResultCode.PARAMS_ERROR);
		}

		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
				jsonResult=JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}else{
				UserScoreBean bean=questionService.getUserScore(user,batchId);
				jsonResult=JsonResult.success(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取获取用户答题分数，", e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}
		return jsonResult;
	}

	/**
	 * 获取用户所有答题记录分数
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.GET_USER_ALL_SCORE, method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getUserAllScore(HttpServletRequest request, Integer courseId) {

		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);

		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
				jsonResult=JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
			}else{
				List<UserScoreBean> bean=questionService.getUserAllScore(user,courseId);
				jsonResult=JsonResult.success(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取获取用户答题分数，", e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}
		return jsonResult;
	}
	
	@RequestMapping(value = RequestMappingURL.GET_ANSWER_SHEET, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getAnswerSheet(HttpServletRequest request,Integer batchId) {
		
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		
		if(batchId==null){
			return JsonResult.failure(ResultCode.PARAMS_ERROR);
		}
		
		try {
			HttpSession session = SessionUtils.getSession();
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
				jsonResult=JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}else{
				List<UserAnswersEntity> list=questionService.getAnswerSheet(user.getId(),batchId);
				jsonResult=JsonResult.success(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取用户答题卡失败，", e);
			jsonResult=JsonResult.failure(ResultCode.PARAMS_ERROR);
		}
		return jsonResult;
	}
	
	@RequestMapping(value = RequestMappingURL.QUESTIONS_ANSWER_DETAILS, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getQWDetails(HttpServletRequest request,Integer id) {
		
		JsonResult jsonResult=JsonResult.failure(ResultCode.PARAMS_ERROR);
		
		if(id==null){
			return JsonResult.failure(ResultCode.PARAMS_ERROR);
		}
		
		try {
			Map<String,Object> result=questionService.getQWDetails(id);
			jsonResult=JsonResult.success(result);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取用户答题详情失败，", e);
			jsonResult=JsonResult.failure(ResultCode.PARAMS_ERROR);
		}
		return jsonResult;
	}
	

}
