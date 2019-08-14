package cn.deepcoding.model;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

//缴费
public class Fee {
	@ApiModelProperty(hidden=true)
	private Integer id;
	@ApiModelProperty(hidden=true)
	private String type; //缴费类型
	@ApiModelProperty(value="缴费金额")
	private double amount; //缴费金额
	@ApiModelProperty(value="缴费时间")
	private Date paymentTime; //缴费时间
	@ApiModelProperty(value="缴费方式")
	private Payment payment; //缴费方式
	@ApiModelProperty(hidden=true)
	private Student student;//学生姓名
	@ApiModelProperty(hidden=true) 
	private FeeDueTime feeDueTime;//费用状态
	@ApiModelProperty(hidden=true)
	private String initialResideDate;//开始住宿时间
	@ApiModelProperty(hidden=true)
	private String remarks;//备注
	
	private FeeType feeType;
	private Date endDate; //此次费用截止日期
	
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public FeeType getFeeType() {
		return feeType;
	}
	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public FeeDueTime getFeeDueTime() {
		return feeDueTime;
	}
	public void setFeeDueTime(FeeDueTime feeDueTime) {
		this.feeDueTime = feeDueTime;
	}
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
    
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getInitialResideDate() {
		return initialResideDate;
	}
	public void setInitialResideDate(String initialResideDate) {
		this.initialResideDate = initialResideDate;
	}
	@Override
	public String toString() {
		return "Fee [id=" + id + ", type=" + type + ", amount=" + amount + ", paymentTime=" + paymentTime + ", payment="
				+ payment + ", student=" + student + ", feeDueTime=" + feeDueTime + ", initialResideDate="
				+ initialResideDate + ", remarks=" + remarks + "]";
	}
    
	 
	 
	 
	
}
