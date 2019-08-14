package cn.deepcoding.model;

import java.io.Serializable;

//学历
public class Education implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2738961903074085702L;
	private Integer id;
	private String name;
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
	
}
