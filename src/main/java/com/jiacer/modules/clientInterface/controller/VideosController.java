package com.jiacer.modules.clientInterface.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.service.VideoSerivce;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.SessionUtils;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.TypeChapterEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.VideosEntity;

@Controller
@RequestMapping(RequestMappingURL.VIDEO_BASE_URL)
public class VideosController {

	@Autowired
	VideoSerivce videoSerivce;

	@RequestMapping(value = RequestMappingURL.TYPES_URL, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult videoTypes(HttpServletRequest request) {
		
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
				jsonResult=JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}else{
				List<LearnTypesEntity> list=videoSerivce.getTypes(user.getId());
				jsonResult=JsonResult.success(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取用户课程分类失败，", e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}
		return jsonResult;
	}
	
	@RequestMapping(value = RequestMappingURL.TYPES_CHAPTER_URL, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult videoTypes(HttpServletRequest request,Integer typeId) {
		
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		
		try {
			
			if(typeId==null || typeId==0){
				return JsonResult.failure(ResultCode.PARAMS_ERROR);
			}
			
			
			List<TypeChapterEntity> list=videoSerivce.getTypeChapters(typeId);
			jsonResult=JsonResult.success(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取用户课程分类章节失败，", e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}
		return jsonResult;
	}
	
	@RequestMapping(value = RequestMappingURL.LIST_URL)
	@ResponseBody
	public JsonResult videoList(HttpServletRequest request,Integer typeChapterId) {
		
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		
		try {
			if(typeChapterId==null){
				jsonResult=JsonResult.failure(ResultCode.PARAMS_ERROR);
				
			}else{
				List<VideosEntity> list=videoSerivce.getVideos(typeChapterId);
				jsonResult=JsonResult.success(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取课程失败，", e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}
		return jsonResult;
	}
	
	@RequestMapping(value = RequestMappingURL.ALL_CHAPTERS_URL)
	@ResponseBody
	public JsonResult allChapters(HttpServletRequest request,Integer courseId) {
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
				jsonResult=JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}else{
				jsonResult=videoSerivce.allChapters(courseId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取用户课程分类失败，", e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}
		return jsonResult;
	}
}
