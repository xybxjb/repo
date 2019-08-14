package cn.deepcoding.model;

import java.sql.Date;

//费用到期时间
public class FeeDueTime {
	private Integer id;
	private String type; // 缴费类型
	private Date entDate; // 到期时间
	private Date lastPaymentTime; // 最后缴费时间
	private Student student;// 学生
	private Integer continuePay; // 是否需要继续缴费0:否1：是
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getEntDate() {
		return entDate;
	}
	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}
	public Date getLastPaymentTime() {
		return lastPaymentTime;
	}
	public void setLastPaymentTime(Date lastPaymentTime) {
		this.lastPaymentTime = lastPaymentTime;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Integer getContinuePay() {
		return continuePay;
	}
	public void setContinuePay(Integer continuePay) {
		this.continuePay = continuePay;
	}
	
 
	 
}
