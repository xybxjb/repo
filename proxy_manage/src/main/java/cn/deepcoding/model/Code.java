package cn.deepcoding.model;

import io.swagger.annotations.ApiModelProperty;

public class Code {
	@ApiModelProperty(value="状态码")
	private String status;   // 状态码
	@ApiModelProperty(value="状态")
	private String code;     // 状态
	@ApiModelProperty(value="状态信息")
	private String message;  // 状态信息
	@ApiModelProperty(value="用户对象")
	private AppUsers appUsers; // 用户对象
	@ApiModelProperty(value="token值")
	private Object data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AppUsers getAppUsers() {
		return appUsers;
	}
	public void setAppUsers(AppUsers appUsers) {
		this.appUsers = appUsers;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
