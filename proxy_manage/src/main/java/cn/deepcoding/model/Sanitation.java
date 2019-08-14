package cn.deepcoding.model;

public class Sanitation {
	
	private Integer id;   		   // 编号
	private Dormitory dormitory;   // 宿舍 id
	private String checkDate;      // 检查时间
	private Integer grade;         // 分数
	private String checkPerson;    // 检查人员
	private String pic;            // 宿舍照片
	private Integer dId;           // 条件查询宿舍
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dormitory getDormitory() {
		return dormitory;
	}
	public void setDormitory(Dormitory dormitory) {
		this.dormitory = dormitory;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getdId() {
		return dId;
	}
	public void setdId(Integer dId) {
		this.dId = dId;
	}
	@Override
	public String toString() {
		return "sanitation [id=" + id + ", dormitory=" + dormitory + ", checkDate=" + checkDate + ", grade=" + grade
				+ ", checkPerson=" + checkPerson + ", pic=" + pic + ", dId=" + dId + "]";
	}
	
}
