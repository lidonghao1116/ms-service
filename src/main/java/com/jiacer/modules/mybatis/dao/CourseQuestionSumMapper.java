package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CourseQuestionSumEntity;
import com.jiacer.modules.mybatis.model.ClassTimesTemplate;

import java.util.List;

/**
 * Created by gaoyan on 18/07/2017.
 */
@MyBatisDao
public interface CourseQuestionSumMapper extends CrudDao<CourseQuestionSumEntity>{

    CourseQuestionSumEntity find(CourseQuestionSumEntity entity);
}
