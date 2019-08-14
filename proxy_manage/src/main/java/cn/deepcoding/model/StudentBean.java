package cn.deepcoding.model;

public class StudentBean extends Student{

	private Integer student_id;
	private Integer amount;
	
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "StudentBean [student_id=" + student_id + ", amount=" + amount + "]";
	}
	
	
	
}
