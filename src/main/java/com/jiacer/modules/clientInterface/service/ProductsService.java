package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.clientInterface.jsonResult.CourseTimesJsonData;
import com.jiacer.modules.common.utils.JsonResult;

/** 
* @ClassName: ProductsService 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月17日 上午10:38:55 
*  
*/
public interface ProductsService {

	/**
	 * 获取推荐课程
	 * @return
	 */
	JsonResult getRecommendCourses( Integer userId, Integer schoolId);

	/**
	 * @return
	 */
	JsonResult getTimesInfo(Integer productId);

	/**
	 * 获取课程对应的详情
	 * @param courseId
	 * @return
	 */
	JsonResult getCourseInfo(Integer courseId);

	/**
	 * @return
	 */
	JsonResult getSysRecommendCourses(Integer schoolId);

}
