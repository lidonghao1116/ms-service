package com.jiacer.modules.clientInterface.service;

import com.jiacer.modules.mybatis.entity.SchoolsEntity;

import java.util.List;

/**
 * Created by gaoyan on 03/07/2017.
 */
public interface SchoolService {

    List<SchoolsEntity> find();

    SchoolsEntity get(Integer id);
}
