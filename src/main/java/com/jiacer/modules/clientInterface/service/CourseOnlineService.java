package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.CourseOnlineEntity;

/**
 * 在线课程
 * Created by gaoyan on 30/06/2017.
 */
public interface CourseOnlineService {

    /**
     * 查询在线课程推荐
     * @param
     * @return
     */
    JsonResult findRecommand(Integer userId);

    JsonResult findOwnCourse(Integer userId);

    JsonResult get(Integer id, Integer userId);

    CourseOnlineEntity sumQuestionCountAndScores(Integer id, CourseOnlineEntity coe);
}
