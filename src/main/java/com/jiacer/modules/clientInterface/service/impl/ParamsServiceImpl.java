package com.jiacer.modules.clientInterface.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import com.jiacer.modules.cache.ParamsCache;
import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.service.ConstantsService;
import com.jiacer.modules.clientInterface.service.ParamsService;
import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import org.springframework.stereotype.Service;

/** 
* @ClassName: ParamsServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午4:49:23 
*  
*/
public class ParamsServiceImpl implements ParamsService{
	
	@Autowired
	CfgParamMapper paramsDao;
	
	@Autowired
	ConstantsService constantsService;

	@Override
	public String getText(Integer type,Integer pid,String values){
		
		if(Global.NO.equals(constantsService.getValue(Constants.PARAMS_CACHE))){
			init();
		}else{
			constantsService.modify();
		}
		List<CfgParamEntity> list=getParams(type,pid);
		for(CfgParamEntity cfgParamEntity:list){
			if(values.equals(cfgParamEntity.getValue())){
				return cfgParamEntity.getText();
			}
		}
		return "";
	}
	
	public void init(){
		CfgParamEntity entity=new CfgParamEntity();
		entity.setType(0);//基础参数类型
		List<CfgParamEntity> cfgparams=Lists.newArrayList();
		try {
			cfgparams=paramsDao.findAllList(entity);
			for(CfgParamEntity bean:cfgparams){
				ParamsCache.setData(Integer.parseInt(bean.getValue()), getParams(Integer.parseInt(bean.getValue()),entity));
			}
		} catch (Exception e) {
			Log.error("获取字典参数失败");
		}
	}


	public List<CfgParamEntity> getParams(Integer type,CfgParamEntity entity){
		entity.setType(type);
		List<CfgParamEntity> cfgparams=Lists.newArrayList();
		try {
			cfgparams=paramsDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取字典参数失败");
		}
		return cfgparams;
	}
	
	public static List<CfgParamEntity> getParams(Integer type,Integer pid){
		List<CfgParamEntity> cfgparams = ParamsCache.getParams(type);
		List<CfgParamEntity> list=Lists.newArrayList();
		for(CfgParamEntity cfg:cfgparams){
			if(pid==cfg.getFkParentParamId()){
				list.add(cfg);
			}
		}
		return list;
	}
}
