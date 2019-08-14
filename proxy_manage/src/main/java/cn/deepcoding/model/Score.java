package cn.deepcoding.model;


//考试分数
public class Score {
	private Integer id;
	private Exam exam; //考试
	private Course course; //课程
	private Integer score; //分数
	private Student student;//学生
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Score [id=" + id + ", exam=" + exam + ", course=" + course + ", score=" + score + "]";
	}
	
	
}
