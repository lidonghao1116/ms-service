package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.service.SchoolService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gaoyan on 03/07/2017.
 */
@RestController
@RequestMapping("/api/school")
public class SchoolController {
    private static final Logger log = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    private SchoolService schoolService;

    /**
     * 获取学校列表
     * @return
     */
    @Auth
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getSchoolList(){
        List<SchoolsEntity> json = schoolService.find();
        return JsonResult.success(json);
    }

    /**
     * 获取学校列表
     * @return
     */
    @Auth
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getSchoolDetail(@PathVariable Integer id){
        SchoolsEntity se = schoolService.get(id);
        return JsonResult.success(se);
    }



}
