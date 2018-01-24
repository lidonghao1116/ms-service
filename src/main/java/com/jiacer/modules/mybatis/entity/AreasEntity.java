package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.model.Areas;

/** 
* @ClassName: AreasEntity 
* @Description: 区域实体类
* @author 贺章鹏
* @date 2016年11月1日 下午5:55:57 
*  
*/
public class AreasEntity extends Areas{

	private static final long serialVersionUID = 1L;
	
	private AreasEntity parent;
	
	private List<AreasEntity> childList;

	public AreasEntity getParent() {
		return parent;
	}

	public void setParent(AreasEntity parent) {
		this.parent = parent;
	}
	
	public static String getRootCode(){
		return "000000";
	}
	
	public List<AreasEntity> getChildList() {
		return childList;
	}

	public void setChildList(List<AreasEntity> childList) {
		this.childList = childList;
	}

	public static void sortList(List<AreasEntity> list, List<AreasEntity> sourcelist, String parentCode, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			AreasEntity e = sourcelist.get(i);
			if (e.getParent()!=null && StringUtils.isNotEmpty(e.getParent().getAreaCode())
					&& e.getParent().getAreaCode().equals(parentCode)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					List<AreasEntity> childList=Lists.newArrayList();
					e.setChildList(childList);
					for (int j=0; j<sourcelist.size(); j++){
						AreasEntity child = sourcelist.get(j);
						if (child.getParent()!=null && StringUtils.isNotEmpty(child.getParent().getAreaCode())
								&& child.getParent().getAreaCode().equals(e.getAreaCode())){
							sortList(e.getChildList(), sourcelist, e.getAreaCode(), true);
							break;
						}
					}
				}
			}
		}
	}
}
