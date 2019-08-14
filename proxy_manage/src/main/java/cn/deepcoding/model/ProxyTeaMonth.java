package cn.deepcoding.model;

import java.util.Date;

import cn.deepcoding.constants.WageState;

//招生老师月结算
public class ProxyTeaMonth {
	private Integer id;
	private Integer proxyTeacherId;
	private String proxyTeacherName;
	private double commissionPoint;//提成点位
	private double commissionAmount;//提成金额
	private Date commissionDate;//提成日期
	private String remarks;//备注
	private String state; //状态
	private WageState wageState;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getProxyTeacherId() {
		return proxyTeacherId;
	}
	public void setProxyTeacherId(Integer proxyTeacherId) {
		this.proxyTeacherId = proxyTeacherId;
	}
	public String getProxyTeacherName() {
		return proxyTeacherName;
	}
	public void setProxyTeacherName(String proxyTeacherName) {
		this.proxyTeacherName = proxyTeacherName;
	}
	public double getCommissionPoint() {
		return commissionPoint;
	}
	public void setCommissionPoint(double commissionPoint) {
		this.commissionPoint = commissionPoint;
	}
	public double getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public Date getCommissionDate() {
		return commissionDate;
	}
	public void setCommissionDate(Date commissionDate) {
		this.commissionDate = commissionDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getWageState() {
		return wageState.getText();
	}
	public void setWageState(WageState wageState) {
		this.wageState = wageState;
	}	
}
