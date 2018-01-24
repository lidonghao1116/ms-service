package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.VideosEntity;

@MyBatisDao
public interface VideosMapper extends CrudDao<VideosEntity>{

	List<VideosEntity> getVideos(VideosEntity videosEntity);

    List<VideosEntity> getVideosByCourseId(Map<String,Object> param);
}