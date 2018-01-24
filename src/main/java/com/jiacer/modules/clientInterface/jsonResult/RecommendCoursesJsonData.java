package com.jiacer.modules.clientInterface.jsonResult;

import java.math.BigDecimal;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: RecommendCoursesJsonData 
* @Description: 推荐课程数据结构
* @author 贺章鹏
* @date 2016年11月16日 下午3:50:34 
*  
*/
public class RecommendCoursesJsonData implements ModelSerializable{

	private static final long serialVersionUID = 1L;

	private Integer productId;//产品id
	
	private String productName;//产品名称
	
	private String summary;//简介
	
	private BigDecimal price;//价格
	
	private BigDecimal originalPrice;//原价
	
	private String isDiscount;//是否打折
	
	private String status;//状态

	private String workType;

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
