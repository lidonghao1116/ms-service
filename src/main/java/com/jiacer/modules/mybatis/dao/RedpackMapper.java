package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.clientInterface.jsonResult.RedpackJsonData;
import com.jiacer.modules.clientInterface.model.RedpackExtendInfo;
import com.jiacer.modules.clientInterface.model.RedpackInfo;
import com.jiacer.modules.common.page.vo.RedpackVo;
import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface RedpackMapper extends CrudDao<RedpackInfo>{

	/**
	 * 查找所有需要重试的红包
	 * @return
     */
	List<RedpackExtendInfo> findRetryRedpack();

    List<RedpackJsonData> getListByUserId(RedpackVo vo);

	Map<String,Object> total(Integer userId);
}
