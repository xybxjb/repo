package cn.deepcoding.model;

import java.io.Serializable;

//学习时长
public class StudyTime implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2537438106437472915L;
	private Integer id;
	private Integer studyTime;//学习时长
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(Integer studyTime) {
		this.studyTime = studyTime;
	}
	
	
}
