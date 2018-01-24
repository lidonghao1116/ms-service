package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.TypeChapterEntity;

@MyBatisDao
public interface TypeChapterMapper extends CrudDao<TypeChapterEntity>{

	List<TypeChapterEntity> getTypeChapters(TypeChapterEntity typeChapterEntity);
    
}