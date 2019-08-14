package cn.deepcoding.model;

import java.util.Date;

public class ProxyTeacherHistory {
	private Integer id;
	private Integer proxyteacherId;//招生老师ID
	private String proxyteacherName;//招生老师姓名
	private Integer studentId;//学生ID
	private String studentName;//学生姓名
	private Double studentTuitionAmount;//学生缴费金额
	private Double commissionPoint;//提成点位
	private Double point;//当前点位
	private Double commissionAmount;//提成金额
	private Date commissionDate;//提成日期
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProxyteacherId() {
		return proxyteacherId;
	}
	public void setProxyteacherId(Integer proxyteacherId) {
		this.proxyteacherId = proxyteacherId;
	}
	public String getProxyteacherName() {
		return proxyteacherName;
	}
	public void setProxyteacherName(String proxyteacherName) {
		this.proxyteacherName = proxyteacherName;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public Double getStudentTuitionAmount() {
		return studentTuitionAmount;
	}
	public void setStudentTuitionAmount(Double studentTuitionAmount) {
		this.studentTuitionAmount = studentTuitionAmount;
	}
	
	public Double getCommissionPoint() {
		return commissionPoint;
	}
	public void setCommissionPoint(Double commissionPoint) {
		this.commissionPoint = commissionPoint;
	}
	
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public Double getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(Double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public Date getCommissionDate() {
		return commissionDate;
	}
	public void setCommissionDate(Date commissionDate) {
		this.commissionDate = commissionDate;
	}
	@Override
	public String toString() {
		return "ProxyTeacherHistory [id=" + id + ", proxyteacherId=" + proxyteacherId + ", proxyteacherName="
				+ proxyteacherName + ", studentId=" + studentId + ", studentName=" + studentName
				+ ", studentTuitionAmount=" + studentTuitionAmount + ", commissionPoint=" + commissionPoint
				+ ", commissionAmount=" + commissionAmount + ", commissionDate=" + commissionDate + "]";
	}
	
	
	
}
