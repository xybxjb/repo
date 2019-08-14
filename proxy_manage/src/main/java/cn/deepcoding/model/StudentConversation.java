package cn.deepcoding.model;

import io.swagger.annotations.ApiModelProperty;

// 学生谈话记录
public class StudentConversation {
	@ApiModelProperty(hidden=true)
	private Integer id;           		// 编号
	@ApiModelProperty(hidden=true)
	private Student student;      		// 学生 id
	@ApiModelProperty(hidden=true)
	private String teacherName;   		// 谈话老师
	@ApiModelProperty(value="谈话原因")
	private String reason;        		// 谈话原因
	@ApiModelProperty(hidden=true)
	private String studentAttitude; 	// 学谈话态度
	@ApiModelProperty(value="谈话时间")
	private String conversationTime;    // 谈话时间
	@ApiModelProperty(hidden=true)
	private Integer expected;           // 是否达到预期效果
	private String pic;                 // 上传谈话记录照片
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
	public String getStudentAttitude() {
		return studentAttitude;
	}
	public void setStudentAttitude(String studentAttitude) {
		this.studentAttitude = studentAttitude;
	}
	public String getConversationTime() {
		return conversationTime;
	}
	public void setConversationTime(String conversationTime) {
		this.conversationTime = conversationTime;
	}
	public Integer getExpected() {
		return expected;
	}
	public void setExpected(Integer expected) {
		this.expected = expected;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "StudentConversation [id=" + id + ", student=" + student + ", teacherName=" + teacherName + ", reason="
				+ reason + ", studentAttitude=" + studentAttitude + ", conversationTime=" + conversationTime
				+ ", expected=" + expected + ", pic=" + pic + "]";
	}
	
	
}
