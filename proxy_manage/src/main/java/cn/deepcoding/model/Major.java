package cn.deepcoding.model;

import io.swagger.annotations.ApiModelProperty;

//专业
public class Major{
	@ApiModelProperty(value="专业编号")
	private Integer id;
	@ApiModelProperty(value="专业名称")
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
