package cn.deepcoding.model;

/**
 * 谈话导入汇总
 * 
 * @author Tz
 *
 */
public class ConversationIntroduction {
	// Id
	private Integer id;
	// 姓名
	private String name;
	// 班级
	private String classroom;
	// 日期
	private String converDate;
	// 时间点
	private String pointTime;
	// 问题原因
	private String problemReason;
	// 备注
	private String remarks;

	private String begindate = null;
	private String enddate = null;

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

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public String getClassroom() {
		return classroom;
	}



	public String getConverDate() {
		return converDate;
	}

	public void setConverDate(String converDate) {
		this.converDate = converDate;
	}

	public String getPointTime() {
		return pointTime;
	}

	public void setPointTime(String pointTime) {
		this.pointTime = pointTime;
	}

	public String getProblemReason() {
		return problemReason;
	}

	public void setProblemReason(String problemReason) {
		this.problemReason = problemReason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBegindate() {
		return begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public ConversationIntroduction() {
		super();
	}

	public ConversationIntroduction(Integer id, String name, String classroom, String converDate, String pointTime,
			String problemReason, String remarks) {
		this.id = id;
		this.name = name;
		this.classroom = classroom;
		this.converDate = converDate;
		this.pointTime = pointTime;
		this.problemReason = problemReason;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "ConversationIntroduction [id=" + id + ", name=" + name + ", classroom=" + classroom + ", converDate="
				+ converDate + ", pointTime=" + pointTime + ", problemReason=" + problemReason + ", remarks=" + remarks
				+ ", begindate=" + begindate + ", enddate=" + enddate + "]";
	}

}
