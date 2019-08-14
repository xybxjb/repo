package cn.deepcoding.model;

import io.swagger.annotations.ApiModelProperty;

public class AppUsers {
	@ApiModelProperty(value="app 编号")
	private Integer appId;      // app 编号
	@ApiModelProperty(value="app 账号")
	private String appName;     // app 账号
	@ApiModelProperty(value="app 密码")
	private String appPassword; // app 密码
	@ApiModelProperty(value="app 手机号")
	private String appTel;      // app 手机号
	@ApiModelProperty(value="app 手机验证码")
	private String securityode; // 验证码
	@ApiModelProperty(value="app 招生老师 id")
	private Integer proxyTeacherId;  // 招生老师 id
	
	
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppPassword() {
		return appPassword;
	}
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	public String getAppTel() {
		return appTel;
	}
	public void setAppTel(String appTel) {
		this.appTel = appTel;
	}
	public String getSecurityode() {
		return securityode;
	}
	public void setSecurityode(String securityode) {
		this.securityode = securityode;
	}
	public Integer getProxyTeacherId() {
		return proxyTeacherId;
	}
	public void setProxyTeacherId(Integer proxyTeacherId) {
		this.proxyTeacherId = proxyTeacherId;
	}
	@Override
	public String toString() {
		return "AppUsers [appId=" + appId + ", appName=" + appName + ", appPassword=" + appPassword + ", appTel="
				+ appTel + ", securityode=" + securityode + ", proxyTeacherId=" + proxyTeacherId + "]";
	}
	
	
}
