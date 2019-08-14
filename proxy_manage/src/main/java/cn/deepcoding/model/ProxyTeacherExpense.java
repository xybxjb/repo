package cn.deepcoding.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.deepcoding.constants.AuditState;

public class ProxyTeacherExpense{
	/**
	 * 
	 */
	private Integer id;//来访记录ID
	private String name;
	private String address;
	private String sex;
	private String idCard;
	private String tel;
	private String purpose;//来访目的
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date datetime;//来访时间
	private double reimAmount;//需报销总钱数
	private Transportation transportation;
	private double actualAmount;//实报金额
	private AuditState auditState;
	private String reimbursementMileage;
	private String auditor;//审核人
	private Date auditTime;//审核时间
	private Integer proxyTeacherId;
	private List<VisitFee> visitFees;
	private List<StudentTeacherAuditing> teacherAuditing;
	
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public double getReimAmount() {
		return reimAmount;
	}
	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}
	
	public Transportation getTransportation() {
		return transportation;
	}
	public void setTransportation(Transportation transportation) {
		this.transportation = transportation;
	}
	public double getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getAuditState() {
		return auditState.getText();
	}
	public void setAuditState(AuditState auditState) {
		this.auditState = auditState;
	}
	public String getReimbursementMileage() {
		return reimbursementMileage;
	}
	public void setReimbursementMileage(String reimbursementMileage) {
		this.reimbursementMileage = reimbursementMileage;
	}
	public Integer getProxyTeacherId() {
		return proxyTeacherId;
	}
	public void setProxyTeacherId(Integer proxyTeacherId) {
		this.proxyTeacherId = proxyTeacherId;
	}
	
	public List<VisitFee> getVisitFees() {
		return visitFees;
	}
	public void setVisitFees(List<VisitFee> visitFees) {
		this.visitFees = visitFees;
	}
	public List<StudentTeacherAuditing> getTeacherAuditing() {
		return teacherAuditing;
	}
	public void setTeacherAuditing(List<StudentTeacherAuditing> teacherAuditing) {
		this.teacherAuditing = teacherAuditing;
	}
	
	
}
