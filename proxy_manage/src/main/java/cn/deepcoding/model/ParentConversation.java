package cn.deepcoding.model;

public class ParentConversation {
	
	private Integer id;
	private Student student;
	private String studentParent;
	private String teacherName;
	private String reason;
	private String conversationTime;
	private String pic;
//	private Integer stuId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getStudentParent() {
		return studentParent;
	}
	public void setStudentParent(String studentParent) {
		this.studentParent = studentParent;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getConversationTime() {
		return conversationTime;
	}
	public void setConversationTime(String conversationTime) {
		this.conversationTime = conversationTime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
//	public Integer getStuId() {
//		return stuId;
//	}
//	public void setStuId(Integer stuId) {
//		this.stuId = stuId;
//	}
	@Override
	public String toString() {
		return "ParentConversation [id=" + id + ", student=" + student + ", studentParent=" + studentParent
				+ ", teacherName=" + teacherName + ", reason=" + reason + ", conversationTime=" + conversationTime
				+ ", pic=" + pic + "]";
	}
	
}
