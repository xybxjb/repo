package cn.deepcoding.model;

import java.util.List;

//招生老师来访记录
public class VisitRecords {
	private Integer id;
	private String startFromAddress;//出发地
	private String purpose;//来访目的
	private String dateTime;//来访时间
	private String reimVoucher;// 招生老师报销凭证(车票)
	private double reimAmount;//需报销总钱数
	private String reimTime;//报销时间
	private String bankName;//银行卡姓名
	private String bankCardNumber;//银行卡卡号
	private String openingBank;//开户行
	private ProxyTeacher proxyTeacher; //招生老师
	private String auditState;//记录审核报销状态0、待审核 1、审核未通过2、待报销、3已报销
	private List<VisitFee> visitFee;

	public List<VisitFee> getVisitFee() {
		return visitFee;
	}
	public void setVisitFee(List<VisitFee> visitFee) {
		this.visitFee = visitFee;
	}
	
	public String getReimVoucher() {
		return reimVoucher;
	}
	public void setReimVoucher(String reimVoucher) {
		this.reimVoucher = reimVoucher;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartFromAddress() {
		return startFromAddress;
	}
	public void setStartFromAddress(String startFromAddress) {
		this.startFromAddress = startFromAddress;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public double getReimAmount() {
		return reimAmount;
	}
	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getOpeningBank() {
		return openingBank;
	}
	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getReimTime() {
		return reimTime;
	}
	public void setReimTime(String reimTime) {
		this.reimTime = reimTime;
	}
	public ProxyTeacher getProxyTeacher() {
		return proxyTeacher;
	}
	public void setProxyTeacher(ProxyTeacher proxyTeacher) {
		this.proxyTeacher = proxyTeacher;
	}
	public String getDateTime() {
		return dateTime;
	}
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
}

