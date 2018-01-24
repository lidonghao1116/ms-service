package com.jiacer.modules.clientInterface.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.service.ConstantsService;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.mybatis.dao.ConstantsMapper;
import com.jiacer.modules.mybatis.entity.ConstantsEntity;

/** 
* @ClassName: ConstantsServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午5:14:33 
*  
*/
public class ConstantsServiceImpl implements ConstantsService{

	@Autowired
	ConstantsMapper constantsDao;
	
	
	@Override
	public String getValue(String key) {
		ConstantsEntity entity=constantsDao.getById(key);
		if(entity==null){
			return "";
		}
		return entity.getConstValue();
	}


	@Override
	public void modify() {
		ConstantsEntity entity=new ConstantsEntity();
		entity.setConstKey(Constants.PARAMS_CACHE);
		entity.setConstValue(Global.YES);
		constantsDao.update(entity);
	}

}
