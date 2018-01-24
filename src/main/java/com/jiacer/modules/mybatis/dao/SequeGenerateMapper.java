package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;

import java.util.Map;

/**
 * Created by gaoyan on 07/07/2017.
 */
@MyBatisDao
public interface SequeGenerateMapper extends CrudDao<Map>{

    Integer getMaxSerialNum(Map<String, Object> param);

    void generateSN(Map<String, Object> param);

    void initGenerator(Map<String, Object> param);

}
