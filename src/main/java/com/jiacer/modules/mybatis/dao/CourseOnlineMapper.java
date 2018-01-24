package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CourseOnlineEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by gaoyan on 30/06/2017.
 */
@MyBatisDao
public interface CourseOnlineMapper extends CrudDao<CourseOnlineEntity>{

    List<CourseOnlineEntity> findListWithUser(Integer userId);

    List<CourseOnlineEntity> findListByUser(Integer userId);

    CourseOnlineEntity getByIdWithUser(Map<String, Integer> param);
}
