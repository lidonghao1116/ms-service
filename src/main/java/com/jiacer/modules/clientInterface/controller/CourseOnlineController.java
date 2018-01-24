package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.service.CourseOnlineService;
import com.jiacer.modules.clientInterface.service.QuestionService;
import com.jiacer.modules.clientInterface.service.VideoSerivce;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.SessionUtils;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.VideosEntity;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 在线学堂课程接口
 * Created by gaoyan on 30/06/2017.
 */
@RestController
@RequestMapping(RequestMappingURL.COURSE_ONLINE_BASE_URL)
public class CourseOnlineController {

    private static final Logger log = LoggerFactory.getLogger(CourseOnlineController.class);

    @Autowired
    private CourseOnlineService courseOnlineService;

    @Autowired
    private VideoSerivce videoSerivce;

    @Autowired
    private QuestionService questionService;

    /**
     * 推荐在线课程
     * @return
     */
    @Auth
    @RequestMapping(value = RequestMappingURL.ONLINE_RECOMMAND_URL, method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRecommand(){

        UserBaseInfoEntity user=(UserBaseInfoEntity) SessionManager.getSession().getAttribute(Constants.USER_SESSION);

        if(user == null || user.getId() == null){
            //用户未登陆，查询所有课程
            user = new UserBaseInfoEntity();
            user.setId(0);
        }

        //查询在线课程
        return courseOnlineService.findRecommand(user.getId());

    }

    /**
     * 已购课程
     * @return
     */
    @RequestMapping(value = RequestMappingURL.ONLINE_OWN_URL, method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getOwnCourse(){
        UserBaseInfoEntity user=(UserBaseInfoEntity) SessionManager.getSession().getAttribute(Constants.USER_SESSION);

        if(user == null || user.getId() == null){
            log.info("获取用户信息失败，请登陆后访问");
            return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
        }

        //查询已购课程
        return courseOnlineService.findOwnCourse(user.getId());

    }

    /**
     * 详情课程
     * @return
     */
    @RequestMapping(value = RequestMappingURL.ONLINE_INFO_URL, method = RequestMethod.GET)
    @Auth
    @ResponseBody
    public JsonResult getCourseDetail(Integer courseId){
        UserBaseInfoEntity user=(UserBaseInfoEntity) SessionManager.getSession().getAttribute(Constants.USER_SESSION);

        //查询已购课程
        return courseOnlineService.get(courseId, user==null? 0: user.getId());

    }


    /**
     * 查询在线课程video
     * @return
     */
    @RequestMapping(value = RequestMappingURL.ONLINE_VIDEO_URL, method = RequestMethod.GET)
    @Auth
    @ResponseBody
    public JsonResult getVideoForCourse(Integer courseId){
        //查询在线课程的视频列表
        JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);

        try {
            if(courseId == null){
                jsonResult=JsonResult.failure(ResultCode.PARAMS_ERROR);

            }else{
                UserBaseInfo user = SessionManager.getUser();
                Integer userId = user == null ? 0 : user.getId();
                List<VideosEntity> list=videoSerivce.getVideosByCourseId(courseId, userId);
                jsonResult=JsonResult.success(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("获取课程视频失败，", e);
            jsonResult=JsonResult.failure(ResultCode.ERROR);
        }
        return jsonResult;
    }

    /**
     * 查询在线课程模拟答题
     * @return
     */
    @RequestMapping(value = RequestMappingURL.ONLINE_TEST_URL, method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getTestForCourse(Integer courseId){
        JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);

        try {
            HttpSession session = SessionUtils.getSession();
            UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
            if(user==null){
                log.info("请求wx-openid为空，请用微信登陆后获取openid");
                jsonResult=JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
            }else{
                Map<String,Object> reslut=questionService.dealGetQuestionsByCourseId(user.getId(),courseId);
                jsonResult=JsonResult.success(reslut);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("获取用户问题失败，", e);
            jsonResult=JsonResult.failure(ResultCode.ERROR);
        }
        return jsonResult;

    }

}
