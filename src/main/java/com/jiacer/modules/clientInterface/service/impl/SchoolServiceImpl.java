package com.jiacer.modules.clientInterface.service.impl;

import com.jiacer.modules.clientInterface.service.SchoolService;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.SchoolsMapper;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 学校业务
 * Created by gaoyan on 03/07/2017.
 */
public class SchoolServiceImpl implements SchoolService{

    private static final Logger log = LoggerFactory.getLogger(SchoolServiceImpl.class);

    @Autowired
    private SchoolsMapper schoolDao;
    @Autowired
    private LearnTypesMapper learnDao;

    @Override
    public List<SchoolsEntity> find(){
        SchoolsEntity se = new SchoolsEntity();
        se.setIsUsable("1");
        List<SchoolsEntity> lists = schoolDao.findAllList(se);
        return lists;

    }

    @Override
    public SchoolsEntity get(Integer id) {
        return schoolDao.getById(id);
    }

}
