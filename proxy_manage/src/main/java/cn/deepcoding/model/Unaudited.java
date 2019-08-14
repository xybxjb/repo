package cn.deepcoding.model;

import java.util.List;

/**
 * 
 * @author 陈大发
 * 学生审核的model类
 *
 */
public class Unaudited {
	
	private Student student;        //学生
	private Integer auditState;		//学生审核状态
	private Integer amount;			//实缴学费
	private Integer billAmount;		//应报金额
	private Integer amountReported;	//实报金额
	private String remarks;			//备注
	private String actualObject;	//实际报销对象姓名
	private ProxyTeacher proxyTeacher;
	private String actualObjectTel; //实际报销对象电话
	private String ticketVoucher;	//车票凭证
	private List<StudentAuditing> studentAuditings;
	
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}


	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Integer billAmount) {
		this.billAmount = billAmount;
	}
	public Integer getAmountReported() {
		return amountReported;
	}
	public void setAmountReported(Integer amountReported) {
		this.amountReported = amountReported;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getActualObject() {
		return actualObject;
	}
	public void setActualObject(String actualObject) {
		this.actualObject = actualObject;
	}
	public ProxyTeacher getProxyTeacher() {
		return proxyTeacher;
	}
	public void setProxyTeacher(ProxyTeacher proxyTeacher) {
		this.proxyTeacher = proxyTeacher;
	}
	public String getActualObjectTel() {
		return actualObjectTel;
	}
	public void setActualObjectTel(String actualObjectTel) {
		this.actualObjectTel = actualObjectTel;
	}
	public String getTicketVoucher() {
		return ticketVoucher;
	}
	public void setTicketVoucher(String ticketVoucher) {
		this.ticketVoucher = ticketVoucher;
	}
	public List<StudentAuditing> getStudentAuditings() {
		return studentAuditings;
	}
	public void setStudentAuditings(List<StudentAuditing> studentAuditings) {
		this.studentAuditings = studentAuditings;
	}
	@Override
	public String toString() {
		return "Unaudited [student=" + student + ", auditState=" + auditState + ", amount=" + amount + ", billAmount="
				+ billAmount + ", amountReported=" + amountReported + ", remarks=" + remarks + ", actualObject="
				+ actualObject + ", proxyTeacher=" + proxyTeacher + ", actualObjectTel=" + actualObjectTel
				+ ", ticketVoucher=" + ticketVoucher + ", studentAuditings=" + studentAuditings + "]";
	}
	

	

	
}
