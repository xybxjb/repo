package cn.deepcoding.model;

import java.io.Serializable;

import cn.deepcoding.constants.SexConst;

//咨询老师
public class AdvisoryTeacher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8081437911229333342L;
	private Integer id;
	private String name;
	private SexConst sex;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SexConst getSex() {
		return sex;
	}
	public String getSexText() {
		return sex.getText();
	}
	public void setSex(SexConst sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "AdvisoryTeacher [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}
	
	
}
