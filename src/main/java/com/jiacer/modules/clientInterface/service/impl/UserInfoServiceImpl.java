package com.jiacer.modules.clientInterface.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiacer.modules.clientInterface.jsonResult.LearnRecordsJsonData;
import com.jiacer.modules.clientInterface.service.UserInfoService;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.dao.ApplyOrdersMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.UserAnswersBatchMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;

/** 
* @ClassName: UserInfoServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午2:37:45 
*  
*/
@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	LearnTypesMapper learnTypesDao;
	
	@Autowired
	UserExtendInfoMapper userExtendDao;
	
	@Autowired
	UserAnswersBatchMapper userAnswersBatchDao;
	
	@Autowired
	ApplyOrdersMapper ordersDao;

	@Override
	public JsonResult getLearnRecords(Integer userId) {
		List<LearnRecordsJsonData> jsonData=Lists.newArrayList(); 
		List<Integer> courseIdList=ordersDao.getUserAnswerCourses(userId);
		
		if(courseIdList==null || courseIdList.size()<1){
			return JsonResult.success(jsonData);
		}
		
		Map<String,Object> params=Maps.newHashMap();
		params.put("isFinished", Global.YES);
		params.put("userId", userId);
		
		LearnRecordEntity maxRecords=null;
		LearnRecordsJsonData learnRecords=null;
		LearnTypesEntity learnTypesEntity=null;
		for(Integer courseId:courseIdList){
			params.put("couresId", courseId);
			learnRecords=new LearnRecordsJsonData();
			maxRecords=userAnswersBatchDao.getUserMaxRecords(params);
			learnTypesEntity=learnTypesDao.getById(courseId);
			if(learnTypesEntity!=null){
				learnRecords.setCourseName(learnTypesEntity.getTypeName());
				learnRecords.setSchool(learnTypesEntity.getSchoolName());
			}
			if(maxRecords!=null){
				learnRecords.setCount(maxRecords.getAnswersNum());;
				learnRecords.setScores(maxRecords.getScores());
			}
			jsonData.add(learnRecords);
		}
	
		return JsonResult.success(jsonData);
	}

	@Override
	public UserExtendInfoEntity getUserExtend(Integer userId) {
		return userExtendDao.getById(userId);
	}
	
}
