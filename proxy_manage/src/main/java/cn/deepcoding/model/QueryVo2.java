package cn.deepcoding.model;

import io.swagger.annotations.ApiModelProperty;

public class QueryVo2 {
	@ApiModelProperty(value="最近月份")
	private String stringWork;
	@ApiModelProperty(value="考勤信息")
	private AttenceCount attenceCount;
	public String getStringWork() {
		return stringWork;
	}
	public void setStringWork(String stringWork) {
		this.stringWork = stringWork;
	}
	public AttenceCount getAttenceCount() {
		return attenceCount;
	}
	public void setAttenceCount(AttenceCount attenceCount) {
		this.attenceCount = attenceCount;
	}
	

	
}
