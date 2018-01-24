package com.jiacer.modules.clientInterface.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.jiacer.modules.clientInterface.bean.form.ApplyOrderForm;
import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.jsonResult.EnrolledCoursesJsonData;
import com.jiacer.modules.clientInterface.service.OrdersService;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.config.DBStatus;
import com.jiacer.modules.mybatis.dao.ApplyOrdersMapper;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.dao.CoursePackageMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.dao.UserScoresMapper;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.mybatis.model.UserScoresKey;

/**
 * @author 贺章鹏
 * @ClassName: OrdersServiceImpl
 * @Description: TODO
 * @date 2016年11月17日 上午10:32:37
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    private  static final Logger log = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Autowired
    ApplyOrdersMapper applyOrderDao;

    @Autowired
    LearnTypesMapper learnTypesDao;

    @Autowired
    CoursePackageMapper productDao;

    @Autowired
    UserExtendInfoMapper userExtendDao;
    @Autowired
    UserScoresMapper userScoreDao;
    
    

    @Override
    public JsonResult getEnrolledCourses(Integer userId) {
        List<EnrolledCoursesJsonData> jsonData = Lists.newArrayList();
        ApplyOrdersEntity oe = new ApplyOrdersEntity();
        ArrayList<String> a= new ArrayList<String>();
        a.add("02");
        a.add("04");
        oe.setHstatusList(a);
        oe.setUserId(userId);
        List<ApplyOrdersEntity> orders = applyOrderDao.getList(oe);
        if (orders == null || orders.size() < 1) {
            return JsonResult.success(jsonData);
        }
        EnrolledCoursesJsonData enrolledData = null;
        for(ApplyOrdersEntity od : orders){
            enrolledData = new EnrolledCoursesJsonData();
            enrolledData.setSignStatus(od.getHandleStatus());
            enrolledData.setSignDate(od.getHandleTime());
            enrolledData.setPackageName(od.getPackageName());
            enrolledData.setUserId(userId);
            LearnTypesEntity bean = learnTypesDao.getById(od.getCourseId());
            if(bean != null){
                enrolledData.setLearnCycle(bean.getTotalHours()+"");
                enrolledData.setExamForm(getExamFormText(bean.getExamType()));
                enrolledData.setSchool(bean.getSchoolName());
                enrolledData.setCourseId(bean.getId());
                enrolledData.setCourseName(bean.getTypeName());
                enrolledData.setRemarks(bean.getRemarks());
                enrolledData.setSchool(bean.getSchoolName());
                //取出对应成绩
                UserScoresKey key=new UserScoresKey();
                key.setClassId(od.getClassNumber());
                key.setUserId(userId);
                UserScoresEntity score=userScoreDao.getByKey(key);
                if(score!=null)
                {
                	enrolledData.setExamResult(score.getExamResult());
                }else {
                	enrolledData.setExamResult("09");
                }
            }

            jsonData.add(enrolledData);
        }
        return JsonResult.success(jsonData);
    }

    private String getExamFormText(String key){
        if("01".equals(key)){
            return "理论";
        }else if("02".equals(key)){
            return "实操";
        }else if("03".equals(key)){
            return "理论+实操";
        }
        return "";
    }

    @Override
    public JsonResult applyOrders(Integer userId, List<ApplyOrderForm> forms) {
        for (ApplyOrderForm form : forms) {
            log.info("productId:"+form.getProductId());
            log.info("classTimes:"+form.getClassTimes());
            log.info("courseId:"+form.getCourseId());

            JsonResult checkResult = this.checkProductStatus(form.getProductId());
            if (!checkResult.isSuccess()) {
                return checkResult;
            }

            CoursePackageEntity entity = productDao.getById(form.getProductId());
            ApplyOrdersEntity applyOrdersEntity = null;
            JsonResult isApply = checkExistApply(userId, form.getCourseId());
            if (!isApply.isSuccess()) {
                return isApply;
            }
            applyOrdersEntity = new ApplyOrdersEntity();
            applyOrdersEntity.setUserId(userId);
            applyOrdersEntity.setPackageId(form.getProductId());
            applyOrdersEntity.setCourseId(form.getCourseId());
            applyOrdersEntity.setOrderType(DBStatus.OrderType.YUYUE);
            applyOrdersEntity.setHandleStatus(DBStatus.HandleStatus.PENDING);
            applyOrdersEntity.setOrderAmount(entity.getPrice());
            applyOrdersEntity.setOrderStatus(DBStatus.OrderStatus.SUCCESS);
            applyOrdersEntity.setOrderTime(new Date());
            applyOrdersEntity.setSchoolId(entity.getSchoolId());
            applyOrdersEntity.setClassTime(form.getClassTimes());
            applyOrderDao.insert(applyOrdersEntity);
        }

        return JsonResult.success(ResultCode.SUCCESS);
    }

    private JsonResult checkProductStatus(Integer productId) {
        try {
            CoursePackageEntity entity = productDao.getById(productId);
            if (DBStatus.ProductStatus.ON_SHELF.equals(entity.getStatus())) {
                return JsonResult.success(null);
            } else {
                return JsonResult.failure(ResultCode.DATA_ERROR);
            }
        } catch (Exception e) {
            Log.error("校验购买产品购买时异常");
            return JsonResult.failure(ResultCode.ERROR);
        }

    }

    private JsonResult checkExistApply(Integer userId, Integer courseId) {
        try {
            ApplyOrdersEntity applyOrdersEntity = new ApplyOrdersEntity();
            applyOrdersEntity.setUserId(userId);
            applyOrdersEntity.setCourseId(courseId);
            List<String> hstatusList = Lists.newArrayList();
            hstatusList.add(DBStatus.HandleStatus.PENDING);
            hstatusList.add(DBStatus.HandleStatus.SUCCESS);
            applyOrdersEntity.setHstatusList(hstatusList);
            Integer count = applyOrderDao.findAllCount(applyOrdersEntity);
            if (count <= 0) {
                return JsonResult.success(null);
            } else {
                return JsonResult.failure(ResultCode.Product.EXIST_B_M);
            }
        } catch (Exception e) {
            Log.error("校验购买产品购买时异常");
            return JsonResult.failure(ResultCode.ERROR);
        }

    }

    @Override
    public JsonResult getSysEnrolledCourses() {
        List<EnrolledCoursesJsonData> jsonData = Lists.newArrayList();

        LearnTypesEntity entity = new LearnTypesEntity();
        entity.setStatus(DBStatus.CourseStatus.NOMAL);
        entity.setIsUsable(DBStatus.IsUseable.TRUE);
        List<LearnTypesEntity> courseList = learnTypesDao.findAllList(entity);
        if (courseList == null || courseList.size() < 1) {
            return JsonResult.failure(ResultCode.DATA_ERROR);
        }

        EnrolledCoursesJsonData enrolledData = null;
        for (LearnTypesEntity bean : courseList) {
            enrolledData = new EnrolledCoursesJsonData();
            enrolledData.setCourseId(bean.getId());
            enrolledData.setCourseName(bean.getTypeName());
            enrolledData.setRemarks(bean.getRemarks());
            jsonData.add(enrolledData);
        }
        return JsonResult.success(jsonData);
    }

}
