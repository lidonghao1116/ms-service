package com.jiacer.modules.clientInterface.service.impl;

import com.jiacer.modules.cache.ParamsCache;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.service.CourseOnlineService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.dao.CourseOnlineMapper;
import com.jiacer.modules.mybatis.dao.CourseQuestionSumMapper;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.entity.CourseOnlineEntity;
import com.jiacer.modules.mybatis.entity.CourseQuestionSumEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线课程
 * Created by gaoyan on 30/06/2017.
 */
public class CourseOnlineServiceImpl implements CourseOnlineService {
    private static final Logger log = LoggerFactory.getLogger(CourseOnlineServiceImpl.class);

    @Autowired
    private CourseOnlineMapper courseOnlineDao;

    @Autowired
    private CourseQuestionSumMapper courseQuestionSumMapper;

    /**
     * 查询所有课程，如果该课程用户已购买，则隐藏价格
     */
    @Override
    public JsonResult findRecommand(Integer userId) {

        List<CourseOnlineEntity> list =  courseOnlineDao.findListWithUser(userId);

        return JsonResult.success(list);
    }

    /**
     * 查询客户已购买课程
     * @param userId
     * @return
     */
    @Override
    public JsonResult findOwnCourse(Integer userId){

        List<CourseOnlineEntity> list =  courseOnlineDao.findListByUser(userId);

        return JsonResult.success(list);
    }

    /**
     * 查询在线课程信息
     * @param id
     * @return
     */
    @Override
    public JsonResult get(Integer id, Integer userId) {
        Map<String,Integer> param = new HashMap<String,Integer>();
        param.put("userId",userId);
        param.put("courseId",id);
        CourseOnlineEntity coe = courseOnlineDao.getByIdWithUser(param);
        if(coe == null){
            log.info("课程不存在");
            return JsonResult.failure(ErrorCode.COURSE_NOT_FOUND_BY_ID);
        }
        coe = sumQuestionCountAndScores(id, coe);

        return JsonResult.success(coe);
    }

    public CourseOnlineEntity sumQuestionCountAndScores(Integer id, CourseOnlineEntity coe){
        //查询题目数量分数
        CourseQuestionSumEntity entity = new CourseQuestionSumEntity();
        entity.setCourseId(id);
        entity.setType("01"); //判断题
        CourseQuestionSumEntity e = courseQuestionSumMapper.find(entity);
        if(e!=null){
            coe.setJudgeCount(e.getCount());
            coe.setJudgeScore(e.getScore());
        }

        entity.setType("02"); //单选
        e = courseQuestionSumMapper.find(entity);
        if(e!=null){
            coe.setSingleCount(e.getCount());
            coe.setSingleScore(e.getScore());
        }

        entity.setType("03"); //多选
        e = courseQuestionSumMapper.find(entity);
        if(e!=null){
            coe.setMultiCount(e.getCount());
            coe.setMultiScore(e.getScore());
        }
        return coe;
    }
}
