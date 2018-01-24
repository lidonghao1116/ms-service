package com.jiacer.modules.clientInterface.service;

import java.util.List;

import com.jiacer.modules.clientInterface.bean.form.ApplyOrderForm;
import com.jiacer.modules.common.utils.JsonResult;

/** 
* @ClassName: OrdersService 
* @Description: 订单服务接口
* @author 贺章鹏
* @date 2016年11月17日 上午10:32:14 
*  
*/
public interface OrdersService {

	/**
	 * 获取已报名课程
	 * @param userId
	 */
	JsonResult getEnrolledCourses(Integer userId);

	/**
	 * 
	 * @param userId
	 * @param productId 
	 * @return
	 */
	JsonResult applyOrders(Integer userId, List<ApplyOrderForm> form);

	/**
	 * @param userId
	 * @return
	 */
	JsonResult getSysEnrolledCourses();

}
