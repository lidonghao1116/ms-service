package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

/** 
* @ClassName: UserLearnRecordsService 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午2:36:50 
*  
*/
public interface UserInfoService {

	/**
	 * 获取学习记录
	 * @param
	 * @return
	 */
	JsonResult getLearnRecords(Integer userId);
	
	UserExtendInfoEntity getUserExtend(Integer userId); 

}
