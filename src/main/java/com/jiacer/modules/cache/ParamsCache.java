package com.jiacer.modules.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;

/** 
* @ClassName: ParamsCache 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午4:47:58 
*  
*/
public class ParamsCache {
	private static Map<Integer, List<CfgParamEntity>> info = new HashMap<Integer, List<CfgParamEntity>>();
	
	
	public static void setData(Integer key,List<CfgParamEntity> params) {
		if (params == null || params.size() < 1) {
			return;
		}
		remove(key);
		info.put(key, params);
	}
	
	public static List<CfgParamEntity> getParams(Integer key){
		return info.get(key);
	}
	
	public static void clear() {
		info.clear();
	}
	
	public static void remove(Integer key) {
		info.remove(key);
	}
}
