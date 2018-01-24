package com.jiacer.modules.clientInterface.service;

import java.util.List;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.TypeChapterEntity;
import com.jiacer.modules.mybatis.entity.VideosEntity;

public interface VideoSerivce {

	/**
	 * 根据用户报取的课程分类
	 * @return
	 */
	List<LearnTypesEntity> getTypes(Integer userId);
	
	/**
	 * 根据分类获取视频列表
	 * @param type
	 * @return
	 */
	List<VideosEntity> getVideos(Integer type);

	/**
	 * 获取分类章节
	 * @param typeId
	 * @return
	 */
	List<TypeChapterEntity> getTypeChapters(Integer typeId);

	/**
	 * @param courseId
	 * @return
	 */
	JsonResult allChapters(Integer courseId);

    List<VideosEntity> getVideosByCourseId(Integer courseId, Integer userId);
}
