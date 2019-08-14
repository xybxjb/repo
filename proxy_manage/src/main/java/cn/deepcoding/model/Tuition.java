package cn.deepcoding.model;
//学费
public class Tuition {
	private Integer id;
	private StudyTime studyTime;//学习时长
	private Major major; //专业
	private double tuition; //学费
 
	private double incidentals;//杂费
	private double quarterage;//住宿费
	private Student student;//学生
	
 
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
 
	public double getIncidentals() {
		return incidentals;
	}
	public void setIncidentals(double incidentals) {
		this.incidentals = incidentals;
	}
	public double getQuarterage() {
		return quarterage;
	}
	public void setQuarterage(double quarterage) {
		this.quarterage = quarterage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public StudyTime getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(StudyTime studyTime) {
		this.studyTime = studyTime;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public double getTuition() {
		return tuition;
	}
	public void setTuition(double tuition) {
		this.tuition = tuition;
	}
	
	
}
