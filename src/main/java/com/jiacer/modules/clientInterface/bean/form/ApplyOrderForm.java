package com.jiacer.modules.clientInterface.bean.form;

import java.util.List;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: ApplyOrderForm 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午4:29:13 
*  
*/
public class ApplyOrderForm  implements ModelSerializable{

	private static final long serialVersionUID = 1L;

	private Integer productId;
	
	private Integer courseId;
	
	private String classTimes;
	
	private List<ApplyOrderForm> applys;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getClassTimes() {
		return classTimes;
	}

	public void setClassTimes(String classTimes) {
		this.classTimes = classTimes;
	}

	public List<ApplyOrderForm> getApplys() {
		return applys;
	}

	public void setApplys(List<ApplyOrderForm> applys) {
		this.applys = applys;
	}
	
}
