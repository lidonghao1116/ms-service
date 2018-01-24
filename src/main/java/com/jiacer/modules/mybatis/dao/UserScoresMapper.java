package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.mybatis.model.UserScoresKey;

@MyBatisDao
public interface UserScoresMapper extends CrudDao<UserScoresEntity> {

    /**
     * @param userScoresKey
     */
    UserScoresEntity getByKey(UserScoresKey userScoresKey);

}