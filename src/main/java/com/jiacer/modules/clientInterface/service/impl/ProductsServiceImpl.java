package com.jiacer.modules.clientInterface.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jiacer.modules.mybatis.dao.*;
import com.jiacer.modules.mybatis.model.ClassTimesTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.jsonResult.CourseOptionJsonData;
import com.jiacer.modules.clientInterface.jsonResult.CourseTimesJsonData;
import com.jiacer.modules.clientInterface.jsonResult.RecommendCoursesJsonData;
import com.jiacer.modules.clientInterface.jsonResult.TimesJsonData;
import com.jiacer.modules.clientInterface.jsonResult.CourseOptionJsonData.Option;
import com.jiacer.modules.clientInterface.service.ParamsService;
import com.jiacer.modules.clientInterface.service.ProductsService;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.CoursesConfigsEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;

/** 
* @ClassName: ProductsServiceImpl 
* @Description: 产品数据
* @author 贺章鹏
* @date 2016年11月17日 上午10:39:27 
*  
*/
@Service
public class ProductsServiceImpl implements ProductsService{
	
	@Autowired
	CoursePackageMapper productDao;
	
	@Autowired
	LearnTypesMapper learnTypesDao;
	
	@Autowired
	ApplyOrdersMapper ordersDao;
	
	@Autowired
	CoursesConfigsMapper coursesConfigsDao;

	@Autowired
	ClassTimesTemplateMapper classTimesTemplateMapper;

	@Override
	public JsonResult getRecommendCourses( Integer userId, Integer schoolId) {
		List<RecommendCoursesJsonData> jsonData=Lists.newArrayList();
		
		CoursePackageEntity entity=new CoursePackageEntity();
		entity.setIsUsable(DBStatus.IsUseable.TRUE);
		List<String> statusList=Lists.newArrayList();
		statusList.add(DBStatus.ProductStatus.ON_SHELF);
		statusList.add(DBStatus.ProductStatus.STOPED);
		entity.setStatusList(statusList);
		entity.setSchoolId(schoolId);
		List<CoursePackageEntity> list=productDao.findAllList(entity);
		
		if(list==null || list.size()<1){
			return JsonResult.success(jsonData);
		}
		
		RecommendCoursesJsonData recommendCourses=null;
		for(CoursePackageEntity bean:list){
			recommendCourses=new RecommendCoursesJsonData();
			recommendCourses.setProductId(bean.getId());
			recommendCourses.setOriginalPrice(bean.getOriginalPrice());
			recommendCourses.setPrice(bean.getPrice());
			recommendCourses.setProductName(bean.getPackageName());
			if(DBStatus.ProductStatus.ON_SHELF.equals(bean.getStatus())){
				//判断该产品是否报名
				ApplyOrdersEntity o = new ApplyOrdersEntity();
				List<String> hsts = new ArrayList<String>();
				hsts.add(DBStatus.HandleStatus.SUCCESS);
				hsts.add(DBStatus.HandleStatus.PENDING);
				o.setPackageId(bean.getId());
				o.setUserId(userId);
				o.setHstatusList(hsts);
				List<ApplyOrdersEntity> os = ordersDao.getList(o);
				if(os!=null&& os.size()>0 && DBStatus.HandleStatus.SUCCESS.equals(os.get(0).getHandleStatus()) ){
					recommendCourses.setStatus(CoursePackageEntity.Status.YBM);
				}else if(o!=null && os.size()>0 && DBStatus.HandleStatus.PENDING.equals(os.get(0).getHandleStatus()) ) {
					recommendCourses.setStatus(CoursePackageEntity.Status.DSH);
				}else{
					//产品没有报名，判断状态
					recommendCourses.setStatus(this.showStatus(bean.getApplyCourses(),userId));
				}
			}else if(DBStatus.ProductStatus.OFF_SHELF.equals(bean.getStatus())){
				recommendCourses.setStatus(CoursePackageEntity.Status.BKBM);
			}else{
				recommendCourses.setStatus(CoursePackageEntity.Status.STOPED);
			}
			recommendCourses.setSummary(bean.getSummary());
			recommendCourses.setIsDiscount(bean.getIsDiscount());
			recommendCourses.setWorkType(bean.getWorkType());
			jsonData.add(recommendCourses);
		}
		
		return JsonResult.success(jsonData);
	}

	@Override
	public JsonResult getTimesInfo(Integer productId) {
		
		CoursePackageEntity entity=productDao.getById(productId);
		if(entity==null || StringUtils.isEmpty(entity.getApplyCourses())){
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}

		List<CourseTimesJsonData> jsonData=Lists.newArrayList();

		String[] courses=StringUtils.split(entity.getApplyCourses(), ",");
		List<Integer> courseIds=Lists.newArrayList();
		for(String courseId:courses){
			courseIds.add(Integer.parseInt(courseId));
		}
		LearnTypesEntity learnTypesEntity=new LearnTypesEntity();
		learnTypesEntity.setCourseIds(courseIds);
		List<LearnTypesEntity> list=learnTypesDao.getCourseList(learnTypesEntity);
		CourseTimesJsonData courseTimesJsonData=null;
		for(LearnTypesEntity bean:list){
			courseTimesJsonData=new CourseTimesJsonData();
			courseTimesJsonData.setCourseId(bean.getId());
			courseTimesJsonData.setCourseName(bean.getTypeName());
			courseTimesJsonData.setClassTimes(classTimesTemplateMapper.findByLearnType(bean.getId()+""));
			jsonData.add(courseTimesJsonData);
		}
		return JsonResult.success(jsonData);
	}

	@Override
	public JsonResult getCourseInfo(Integer courseId) {
		CourseOptionJsonData courseOptionJsonData=new CourseOptionJsonData();
		LearnTypesEntity learnTypesEntity=learnTypesDao.getById(courseId);
		if(learnTypesEntity==null){
			return JsonResult.failure(ResultCode.ERROR);
		}

		List<ClassTimesTemplate> cct = classTimesTemplateMapper.findByLearnType(learnTypesEntity.getId()+"");
		learnTypesEntity.setClassTimes(cct);

		courseOptionJsonData.setCourseId(learnTypesEntity.getId());
		courseOptionJsonData.setCourseName(learnTypesEntity.getTypeName());
		List<CoursesConfigsEntity> coursesConfigs=coursesConfigsDao.getOptionsByCourseId(courseId);
		List<Option> options=Lists.newArrayList();
		Option option=null;
		for(CoursesConfigsEntity bean:coursesConfigs){
			option=courseOptionJsonData.createOption();
			option.setCode(bean.getConfigsCode());
			option.setName(bean.getConfigsName());
			options.add(option);
		}
		courseOptionJsonData.setOptions(options);
		return JsonResult.success(courseOptionJsonData);
	}
	
	
	private String showStatus(String applyCourses,Integer userId){
		//如果所有课程都
		if(StringUtils.isEmpty(applyCourses)){
			return CoursePackageEntity.Status.LJBM;
		}
		
		String[] allCourses=StringUtils.split(applyCourses,",");
		
		int i=0;
		for(String courseId:allCourses){
			String flag=checekOrderStatus(userId,Integer.parseInt(courseId));
			if(DBStatus.HandleStatus.SUCCESS.equals(flag)){
				return CoursePackageEntity.Status.BKBM;
			};
			if(DBStatus.HandleStatus.PENDING.equals(flag)){
				return CoursePackageEntity.Status.BKBM;
			};
			if(DBStatus.HandleStatus.FAILED.equals(flag)){
				i++;
			};
		}
		if(i==allCourses.length){
			return CoursePackageEntity.Status.LJBM;
		}
		
		return CoursePackageEntity.Status.BKBM;
	}
	
	@Override
	public JsonResult getSysRecommendCourses(Integer schoolId) {
		List<RecommendCoursesJsonData> jsonData=Lists.newArrayList();
		
		CoursePackageEntity entity=new CoursePackageEntity();
		entity.setIsUsable(DBStatus.IsUseable.TRUE);
		entity.setSchoolId(schoolId);
		List<String> ss = new ArrayList<String>();
		ss.add("01");
		ss.add("03");
		entity.setStatusList(ss);

		List<CoursePackageEntity> list=productDao.findAllList(entity);
		
		if(list==null || list.size()<1){
			return JsonResult.success(jsonData);
		}
		
		RecommendCoursesJsonData recommendCourses=null;
		for(CoursePackageEntity bean:list){
			recommendCourses=new RecommendCoursesJsonData();
			recommendCourses.setProductId(bean.getId());
			recommendCourses.setOriginalPrice(bean.getOriginalPrice());
			recommendCourses.setPrice(bean.getPrice());
			recommendCourses.setProductName(bean.getPackageName());
			recommendCourses.setStatus(bean.getStatus());
			recommendCourses.setSummary(bean.getSummary());
			recommendCourses.setIsDiscount(bean.getIsDiscount());
			recommendCourses.setWorkType(bean.getWorkType());
			jsonData.add(recommendCourses);
		}
		
		return JsonResult.success(jsonData);
	}
	
	private String checekOrderStatus(Integer userId,Integer courseId){
		try {
			ApplyOrdersEntity applyOrdersEntity=new ApplyOrdersEntity();
			applyOrdersEntity.setUserId(userId);
			applyOrdersEntity.setCourseId(courseId);
			applyOrdersEntity.setOrderStatus(DBStatus.OrderStatus.SUCCESS);
			List<String> hstatusList=Lists.newArrayList();
			hstatusList.add(DBStatus.HandleStatus.PENDING);
			hstatusList.add(DBStatus.HandleStatus.SUCCESS);
			applyOrdersEntity.setHstatusList(hstatusList);
			List<ApplyOrdersEntity> list=ordersDao.findAllList(applyOrdersEntity);
			if(list==null || list.size()<1){
				return DBStatus.HandleStatus.FAILED;
			}else{
				return list.get(0).getHandleStatus();
			}
		} catch (Exception e) {
			Log.error("校验购买产品下课程状态失败");
			return DBStatus.HandleStatus.FAILED;
		}
		
	}
}
