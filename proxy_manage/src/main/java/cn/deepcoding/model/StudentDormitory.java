package cn.deepcoding.model;

import java.util.List;

//学生与住宿关系表
public class StudentDormitory {
	private Integer id;// ID
	private String starttime;// 住宿时间
	private String endtime;// 退宿时间
	private Dormitory dormitroy;// 宿舍
	private Student student;// 学生
	private Integer	dormitoryleader;//宿舍长


	public Integer getDormitoryleader() {
		return dormitoryleader;
	}

	public void setDormitoryleader(Integer dormitoryleader) {
		this.dormitoryleader = dormitoryleader;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Dormitory getDormitroy() {
		return dormitroy;
	}

	public void setDormitroy(Dormitory dormitroy) {
		this.dormitroy = dormitroy;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "StudentDormitory [id=" + id + ", starttime=" + starttime
				+ ", endtime=" + endtime + ", dormitroy=" + dormitroy + ", student=" + student + ", dormitoryleader="
				+ dormitoryleader + "]";
	}

}
