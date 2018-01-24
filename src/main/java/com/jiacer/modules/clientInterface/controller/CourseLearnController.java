package com.jiacer.modules.clientInterface.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jiacer.modules.clientInterface.common.SessionKeys;
import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.clientInterface.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.clientInterface.bean.form.ApplyOrderForm;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.service.OrdersService;
import com.jiacer.modules.clientInterface.service.ProductsService;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.SessionUtils;
import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;

/**
* @ClassName: CourseLearnController
* @Description: 课程学习控制接口
* @author 贺章鹏
* @date 2016年11月16日 下午12:45:57
*
*/
@Controller
@RequestMapping(RequestMappingURL.COURSE_LEARN_BASE_URL)
public class CourseLearnController {


	@Autowired
	ProductsService productsService;

	@Autowired
	OrdersService ordersService;

	@Autowired
	UserInfoService userInfoService;

	/**
	 * 已报名课程接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.ENROLLED_COURSES_URL, method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult enrolledCourses(HttpServletRequest request) {
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
				return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
			}
			if(DBStatus.UserType.NORMAL.equals(user.getUserType())){
				jsonResult=ordersService.getEnrolledCourses(user.getId());
			}else if(DBStatus.UserType.SYSTEM_USER.equals(user.getUserType())){
				jsonResult=ordersService.getSysEnrolledCourses();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error("get enrolled courses error :"+e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}

		return jsonResult;
	}

	/**
	 * 推荐课程接口
	 * @param request
	 * @return
	 */
	@Auth
	@RequestMapping(value = RequestMappingURL.RECOMMEND_COURSES_URL, method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult recommendCourses(HttpServletRequest request, Integer schoolId) {
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
                jsonResult=productsService.getSysRecommendCourses(schoolId);
			}else {
                if (DBStatus.UserType.NORMAL.equals(user.getUserType())) {
                    jsonResult = productsService.getRecommendCourses(user.getId(), schoolId);
                } else if (DBStatus.UserType.SYSTEM_USER.equals(user.getUserType())) {
                    jsonResult = productsService.getSysRecommendCourses(schoolId);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("get enrolled courses error :"+e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}

		return jsonResult;
	}

	/**
	 * 产品对应的课程详情（上课时间列表）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.GET_TIMES_URL, method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult getTimesInfo(HttpServletRequest request,Integer productId) {
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		try {

			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
				return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
			}

			jsonResult=productsService.getTimesInfo(productId);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("get enrolled courses error :"+e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}

		return jsonResult;
	}


	/**
	 * 获取课程的对应的操作（视频、答题、等等）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.COURSES_INFO_URL, method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult getCourseInfo(HttpServletRequest request,Integer courseId) {
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
                return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
			}
			jsonResult=productsService.getCourseInfo(courseId);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("get enrolled courses error :"+e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}

		return jsonResult;
	}


	/**
	 * 课程产品申请（报名）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.APPLY_PRODUCTS_INFO_URL, method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult apply(HttpServletRequest request,ApplyOrderForm form) {
		JsonResult jsonResult=JsonResult.failure(ResultCode.ERROR);
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user=(UserBaseInfoEntity) session.getAttribute(Constants.USER_SESSION);
			if(user==null){
                return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
			}
			jsonResult=ordersService.applyOrders(user.getId(),form.getApplys());
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("get enrolled courses error :"+e);
			jsonResult=JsonResult.failure(ResultCode.ERROR);
		}

		return jsonResult;
	}



	/**
	 * 用户学习记录
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.MY_LEARN_RECORDS_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult getMyLearnRecords(HttpServletRequest request, String page) {
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBaseInfoEntity user =  (UserBaseInfoEntity) session.getAttribute(SessionKeys.USER_SESSION);
			if (user == null || user.getId() == null) {
                return JsonResult.failure(ErrorCode.USER_NEED_LOGIN);
			}
			JsonResult jsonResult= userInfoService.getLearnRecords(user.getId());
			return jsonResult;
		} catch (Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}
	}




}
