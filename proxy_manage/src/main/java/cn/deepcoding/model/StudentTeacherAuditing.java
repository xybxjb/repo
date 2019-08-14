package cn.deepcoding.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.deepcoding.constants.AuditState;

//报销审核
public class StudentTeacherAuditing {
	private Integer id;
	private String auditor;//审核人
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date auditTime;//审核时间
	private double actuSetAmount;//实际结算金额
	private AuditState auditStatus;//审核结果
	private String remarks;//备注
	private VisitRecords visitRecords;//招生老师来访记录
	private Student student;
	private String flag;//审核对象1、招生老师 2、学生
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public double getActuSetAmount() {
		return actuSetAmount;
	}
	public void setActuSetAmount(double actuSetAmount) {
		this.actuSetAmount = actuSetAmount;
	}
	public String getAuditStatus() {
		return auditStatus.getText();
	}
	public void setAuditStatus(AuditState auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public VisitRecords getVisitRecords() {
		return visitRecords;
	}
	public void setVisitRecords(VisitRecords visitRecords) {
		this.visitRecords = visitRecords;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
