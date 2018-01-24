package com.jiacer.modules.clientInterface.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.jiacer.modules.clientInterface.jsonResult.ChaptersJsonData;
import com.jiacer.modules.clientInterface.jsonResult.VideosJsonData;
import com.jiacer.modules.clientInterface.service.VideoSerivce;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.TypeChapterMapper;
import com.jiacer.modules.mybatis.dao.VideosMapper;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.TypeChapterEntity;
import com.jiacer.modules.mybatis.entity.VideosEntity;

@Service
public class VideoServiceImpl implements VideoSerivce{
	
	@Autowired
	VideosMapper videosDao;
	
	@Autowired
	LearnTypesMapper learnTypeDao;
	
	@Autowired
	TypeChapterMapper typeChapterDao;

	@Override
	public List<LearnTypesEntity> getTypes(Integer userId) {
		LearnTypesEntity bean=new LearnTypesEntity();
		bean.setUserId(userId);
		List<LearnTypesEntity> list=learnTypeDao.getUserLearnTypes(bean);
		return list;
	}

	@Override
	public List<VideosEntity> getVideos(Integer typeChapterId) {
		
		TypeChapterEntity bean=typeChapterDao.getById(typeChapterId);
		
		VideosEntity videosEntity =new VideosEntity();
		videosEntity.setTypeId(bean.getTypeId());
		videosEntity.setChapterCode(bean.getChapterCode());
		List<VideosEntity> list=videosDao.getVideos(videosEntity);
		return list;
	}

	@Override
	public List<TypeChapterEntity> getTypeChapters(Integer typeId) {
		TypeChapterEntity typeChapterEntity =new TypeChapterEntity();
		typeChapterEntity.setTypeId(typeId);
		List<TypeChapterEntity> list= typeChapterDao.getTypeChapters(typeChapterEntity);
		return list;
	}

	@Override
	public JsonResult allChapters(Integer courseId) {
		List<ChaptersJsonData> jsonData=Lists.newArrayList();
		TypeChapterEntity typeChapterEntity =new TypeChapterEntity();
		typeChapterEntity.setTypeId(courseId);
		List<TypeChapterEntity> list= typeChapterDao.getTypeChapters(typeChapterEntity);
		VideosEntity videosEntity=null;
		ChaptersJsonData chaptersJsonData=null;
		VideosJsonData videos=null;
		for(TypeChapterEntity entity:list){
			chaptersJsonData=new ChaptersJsonData();
			chaptersJsonData.setCourseId(entity.getTypeId());
			chaptersJsonData.setExamAreaCode(entity.getChapterCode());
			chaptersJsonData.setExamAreaName(entity.getChapterName());
			
			videosEntity =new VideosEntity();
			videosEntity.setTypeId(entity.getTypeId());
			videosEntity.setChapterCode(entity.getChapterCode());
			List<VideosEntity> vlist=videosDao.getVideos(videosEntity);
			List<VideosJsonData> videosJsonData=Lists.newArrayList();
			for(VideosEntity vbean:vlist){
				videos=new VideosJsonData();
				videos.setVideoId(vbean.getId());
				videos.setVideoFormat(vbean.getVideoFormat());
				videos.setVideoName(vbean.getVideoName());
				videos.setVideoUrl(vbean.getVideoUrl());
				videosJsonData.add(videos);
			}
			chaptersJsonData.setVideos(videosJsonData);
			jsonData.add(chaptersJsonData);
		}
		
		return JsonResult.success(jsonData);
	}

	@Override
	public List<VideosEntity> getVideosByCourseId(Integer courseId, Integer userId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", userId);
		param.put("courseId", courseId);
		List<VideosEntity> list=videosDao.getVideosByCourseId(param);
		return list;
	}

}
