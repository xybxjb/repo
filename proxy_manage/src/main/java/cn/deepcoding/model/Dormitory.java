package cn.deepcoding.model;

import java.util.List;


import cn.deepcoding.constants.SexConst;

//宿舍
public class Dormitory  implements Comparable<Dormitory>{

	private Integer id;// ID
	private Integer number;// 宿舍号
	private Integer total;// 应住人数
	private Integer actual;// 实住人数
	private Integer sex;// 性别
	private String time;// 创建时间
	private String note;// 信息
	private List<StudentDormitory> studentdormitory;// 学生住宿表
	private String adress;//地址
	private Integer hidden;
	

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getActual() {
		return actual;
	}

	public void setActual(Integer actual) {
		this.actual = actual;
	}

	
	

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<StudentDormitory> getStudentdormitory() {
		return studentdormitory;
	}

	public void setStudentdormitory(List<StudentDormitory> studentdormitory) {
		this.studentdormitory = studentdormitory;
	}

	@Override
	public String toString() {
		return "Dormitory [id=" + id + ", number=" + number + ", total=" + total + ", actual=" + actual + ", sex=" + sex
				+ ", time=" + time + ", note=" + note + ", studentdormitory=" + studentdormitory + ", adress=" + adress
				+ ", hidden=" + hidden + "]";
	}

	@Override
	public int compareTo(Dormitory o) {
		// TODO Auto-generated method stub
		return o.actual-this.actual;
	}

	
}
