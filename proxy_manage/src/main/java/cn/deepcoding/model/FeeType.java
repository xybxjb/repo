package cn.deepcoding.model;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;


public class FeeType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7877812658278399258L;
	private Integer id;
	private String name; //缴费类型
	private Date createTime; //缴费时间
	private Boolean addDate;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getAddDate() {
		return addDate;
	}
	public void setAddDate(Boolean addDate) {
		this.addDate = addDate;
	}
	
	
}
