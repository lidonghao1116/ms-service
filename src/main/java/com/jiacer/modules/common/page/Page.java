package com.jiacer.modules.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装 注意所有序号从1开始
 * Page中记录的类型
 */
public class Page<T> implements Serializable {
	// -- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	// -- 分页参数 --//
	protected Integer page = 1;
	protected Integer pageSize = 15; // 默认为每页10条记录
	protected Integer records= 0; // 总记录数
	protected Integer total = 0; //总分页数
	protected String orderBy = null; // 排序字段
	protected String order = null; // 排序方式
	protected List<T> rows = Collections.emptyList(); // 用于封装结果集

	// -- 构造函数 --//
	public Page() {
	}

	public Page(Integer pageSize) {
		setPageSize(pageSize);
	}

	public Page(Integer page, Integer pageSize) {
		setPage(page);
		setPageSize(pageSize);
	}

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPage(final Integer page) {
		this.page = page;
		if (page < 1) {
			this.page = 1;
		}
	}

	/**
	 * 获得每页的记录数量,默认为10
	 */
	public Integer getPageSize() {
		return pageSize == null ? 15 : pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为10.
	 */
	public void setPageSize(final Integer pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 1) {
			this.pageSize = 10;
		}
	}

	/**
	 * 设置总记录数.
	 */
	public void setRecords(final Integer records) {
		this.records = records;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始
	 */
	@JsonIgnore
	public Integer getFirst() {
		return ((page - 1) * pageSize) + 1;
	}

	@JsonIgnore
	public Integer getFirstIndex() {
		return ((page - 1) * pageSize);
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔
	 */
	@JsonIgnore
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	@JsonIgnore
	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * 获得排序方向.
	 */
	@JsonIgnore
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);
		// 检查order字符串的合法值
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}
		this.order = lowcaseOrder;
	}

	@JsonIgnore
	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值
	 */
	@JsonIgnore
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始 用于Mysql
	 */
	@JsonIgnore
	public Integer getOffset() {
		return ((page - 1) * pageSize);
	}

	/**
	 * 实现Iterable接口,可以for(Object item : page)遍历使用
	 */
	@SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
		return rows == null ? IteratorUtils.EMPTY_ITERATOR : rows.iterator();
	}

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setRows(final List<T> rows) {
		this.rows = rows;
	}

	/**
	 * 是否第一页.
	 */
	@JsonIgnore
	public boolean getFirstPage() {
		return page == 1;
	}

	/**
	 * 是否还有上一页
	 */
	@JsonIgnore
	public boolean getHasPrePage() {
		return (page - 1 >= 1);
	}

	/**
	 * 是否还有下一页.
	 */
	@JsonIgnore
	public boolean getHasNextPage() {
		return (page + 1 <= getRecords());
	}

	/**
	 * 是否最后一页.
	 */
	@JsonIgnore
	public boolean getLastPage() {
		return page == getRecords();
	}

	/**
	 * 取得下页的页号, 序号从1开始 当前页为尾页时仍返回尾页序号
	 */
	@JsonIgnore
	public Integer getNextPage() {
		if (getHasNextPage()) {
			return page + 1;
		} else {
			return page;
		}
	}

	/**
	 * 取得上页的页号, 序号从1开始 当前页为首页时返回首页序号
	 */
	@JsonIgnore
	public Integer getPrePage() {
		if (getHasPrePage()) {
			return page - 1;
		} else {
			return page;
		}
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为0
	 */
	public Integer getTotal() {
		if (records < 0) {
			return -1;
		}
		Integer count = records / pageSize;
		if (records % pageSize > 0) {
			count++;
		}
		return count;
	}

	@JsonIgnore
	public List<Integer> getPageNos() {
		return getPageNos(getTotal(), this.page);
	}

	@JsonIgnore
	public static List<Integer> getPageNos(Integer totalPages, Integer pageNo) {
		Integer N=5;
		Integer M=N/2;
		List<Integer> numList = new ArrayList<Integer>();
		if(totalPages>0){
			if(pageNo<1)
				return numList;
			if(pageNo>totalPages)
				return numList;
			Integer begin=pageNo-M;
			Integer end=(Integer)totalPages;
			if(pageNo<=N){
				begin=1;
				if(totalPages>N)
					end=N;
			}else if(pageNo>totalPages-N){
				begin=(Integer)totalPages-N+1;
			}else{
				end=pageNo+M;
			}
			for(Integer i=begin;i<=end;i++){
				numList.add(i);
			}
		}
		return numList;
	}

	public Integer getRecords() {
		return records;
	}
	
}