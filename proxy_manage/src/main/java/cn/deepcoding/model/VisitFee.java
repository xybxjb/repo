package cn.deepcoding.model;

//招生老师来访费用
public class VisitFee {
	private Integer id;
	private VisitRecords visitRecords;//来访记录
	private Transportation trans;//出行方式
	private double ticketAmount;//车票金额
	private double actualAmount;//实报金额
	private String reimbMileage;//报销里程
	private String reimbVoucher;//报销凭证
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public VisitRecords getVisitRecords() {
		return visitRecords;
	}
	public void setVisitRecords(VisitRecords visitRecords) {
		this.visitRecords = visitRecords;
	}
	public Transportation getTrans() {
		return trans;
	}
	public void setTrans(Transportation trans) {
		this.trans = trans;
	}
	public double getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(double ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public double getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getReimbMileage() {
		return reimbMileage;
	}
	public void setReimbMileage(String reimbMileage) {
		this.reimbMileage = reimbMileage;
	}
	public String getReimbVoucher() {
		return reimbVoucher;
	}
	public void setReimbVoucher(String reimbVoucher) {
		this.reimbVoucher = reimbVoucher;
	}
//	public String gettName() {
//		return tName;
//	}
//	public void settName(String tName) {
//		this.tName = tName;
//	}
	
	
}
