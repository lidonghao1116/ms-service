package com.jiacer.modules.clientInterface.jsonResult;

import java.util.List;

import com.jiacer.modules.common.persistence.ModelSerializable;
import com.jiacer.modules.mybatis.model.ClassTimesTemplate;

/** 
* @ClassName: CourseTimesJsonData 
* @Description: 课程时间
* @author 贺章鹏
* @date 2016年11月21日 下午2:43:36 
*  
*/
public class CourseTimesJsonData implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private Integer courseId;

	private String courseName;//课程名称

	private List<ClassTimesTemplate> classTimes;

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

	public List<ClassTimesTemplate> getClassTimes() {
		return classTimes;
	}

	public void setClassTimes(List<ClassTimesTemplate> classTimes) {
		this.classTimes = classTimes;
	}
}
