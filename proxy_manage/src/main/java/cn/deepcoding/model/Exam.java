package cn.deepcoding.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//考试
@ApiModel(value="试卷对象",description="试卷信息")
public class Exam{
	@ApiModelProperty(value="考试id")
	private Integer id;
	@ApiModelProperty(value="考试名称")
	private String name; //考试名
	@ApiModelProperty(value="考试时间")
	private String examDate; //考试时间
	@ApiModelProperty(hidden=true)
	private Classroom classroom;
	@ApiModelProperty(hidden=true)
	private Teacher teacher;
	@ApiModelProperty(hidden=true)
	private Student student;
	@ApiModelProperty(hidden=true)
	private List<Score> scores;
	@ApiModelProperty(value="考试成绩")
	private Integer score;  // 考试成绩
	
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Exam [id=" + id + ", name=" + name + ", examDate=" + examDate + ", classroom=" + classroom
				+ ", teacher=" + teacher + ", student=" + student + ", scores=" + scores + ", score=" + score + "]";
	}
	
	
	
	
}
