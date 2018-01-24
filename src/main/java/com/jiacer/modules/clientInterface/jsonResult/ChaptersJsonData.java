package com.jiacer.modules.clientInterface.jsonResult;

import java.util.List;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: ChaptersJsonData 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月25日 下午5:15:36 
*  
*/
public class ChaptersJsonData implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private Integer courseId;//所属课程
	
	private String examAreaName;//章节考区
	
	private String examAreaCode;//章节考区code
	
	private List<VideosJsonData> videos;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getExamAreaName() {
		return examAreaName;
	}

	public void setExamAreaName(String examAreaName) {
		this.examAreaName = examAreaName;
	}

	public String getExamAreaCode() {
		return examAreaCode;
	}

	public void setExamAreaCode(String examAreaCode) {
		this.examAreaCode = examAreaCode;
	}

	public List<VideosJsonData> getVideos() {
		return videos;
	}

	public void setVideos(List<VideosJsonData> videos) {
		this.videos = videos;
	}
}
