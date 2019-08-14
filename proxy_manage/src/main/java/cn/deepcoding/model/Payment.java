package cn.deepcoding.model;

import io.swagger.annotations.ApiModelProperty;

//缴费方式
public class Payment {
	@ApiModelProperty(value="缴费方式id")
	private Integer id;
	@ApiModelProperty(value="缴费方式名称")
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
 
	@Override
	public String toString() {
		return "Payment [id=" + id + ", name=" + name + "]";
	}






 
	
}
