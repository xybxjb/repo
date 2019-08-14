package cn.deepcoding.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

public class Classroom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4516964942839525946L;
	private int id;
	private String name;
	private String beginTime;// 创建时间
	private String endTime;// 创建时间
	private Set<Teacher> teacher;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Set<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teacher> teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Classroom [id=" + id + ", name=" + name + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", teacher=" + teacher + "]";
	}

}
