package com.jiacer.modules.clientInterface.jsonResult;

import java.util.List;

import com.google.common.collect.Lists;
import com.jiacer.modules.common.persistence.ModelSerializable;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;

/** 
* @ClassName: CourseInfoJsonData 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月21日 下午5:27:42 
*  
*/
public class CourseOptionJsonData implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private String cover;//封面
	
	private Integer courseId;
	
	private String courseName;
	
	private List<Option> options;
	
	public class Option {
		private String code;//操作代码 视频、答题、简介
		private String name;//名称
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	
	public CourseOptionJsonData getCourseOptions(LearnTypesEntity learnTypesEntity){
		CourseOptionJsonData data=new CourseOptionJsonData();
		if(learnTypesEntity.getId()==1){
			data.setCourseId(learnTypesEntity.getId());
			data.setCourseName(learnTypesEntity.getTypeName());
			List<Option> option=Lists.newArrayList();
			Option optionBean=null;
			optionBean=new Option();
			optionBean.setCode("SP");
			optionBean.setName("视频");
			option.add(optionBean);
			optionBean=new Option();
			optionBean.setCode("DT");
			optionBean.setName("答题");
			option.add(optionBean);
			data.setOptions(option);
		}else{
			data.setCourseId(learnTypesEntity.getId());
			data.setCourseName(learnTypesEntity.getTypeName());
		}
		return data;
	}
	
	public Option createOption(){
		return new Option();
	}
}
