package cn.deepcoding.model;

import java.util.Date;

//学生报销审核
public class StudentAuditing {
	private Integer id;
	private String auditor;				//审核人
	private String auditTime;				//审核时间
	private Integer auditStatus;		//审核结果
	private String remarks;				//备注
	private Integer studentId;			//学生ID
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


	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	@Override
	public String toString() {
		return "StudentAuditing [id=" + id + ", auditor=" + auditor + ", auditTime=" + auditTime + ", auditStatus="
				+ auditStatus + ", remarks=" + remarks + ", studentId=" + studentId + "]";
	}




}
