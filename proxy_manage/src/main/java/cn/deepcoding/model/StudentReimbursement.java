package cn.deepcoding.model;

import java.io.Serializable;

//学生报销
public class StudentReimbursement  implements Serializable{
	private Integer id;
	private Integer studentId;//学生Id
	private String actualObject;//实际报销对象
	private String actualObjectTel;//实际报销对象联系方式
	private double billAmount;//应报金额
	private double amountReported;//实报金额
	private String ticketVoucher;//车票凭证
	private Integer auditState;//记录审核报销状态0、待审核 1、审核未通过2、待报销3、已报销
	private String remarks;//备注

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getActualObject() {
		return actualObject;
	}
	public void setActualObject(String actualObject) {
		this.actualObject = actualObject;
	}
	public String getActualObjectTel() {
		return actualObjectTel;
	}
	public void setActualObjectTel(String actualObjectTel) {
		this.actualObjectTel = actualObjectTel;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	public double getAmountReported() {
		return amountReported;
	}
	public void setAmountReported(double amountReported) {
		this.amountReported = amountReported;
	}
	public String getTicketVoucher() {
		return ticketVoucher;
	}
	public void setTicketVoucher(String ticketVoucher) {
		this.ticketVoucher = ticketVoucher;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	
	
}
