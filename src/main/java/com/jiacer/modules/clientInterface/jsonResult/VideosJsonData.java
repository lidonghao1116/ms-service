package com.jiacer.modules.clientInterface.jsonResult;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: VideosJsonData 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月25日 下午5:23:46 
*  
*/
public class VideosJsonData implements ModelSerializable{
	private static final long serialVersionUID = 1L;
	
	private String videoId;;
	
	private String videoName;

    private String videoFormat;

    private String videoUrl;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoFormat() {
		return videoFormat;
	}

	public void setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

}
