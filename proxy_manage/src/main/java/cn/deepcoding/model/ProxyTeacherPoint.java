package cn.deepcoding.model;

/*
 * 招生老师的点位表
 */
public class ProxyTeacherPoint {
	private Integer id;
	private Double point;//点位
	private String startDate; //开始时间
	private String endDate;	//结束时间
	private Integer proxyTeacherId; //表达多的一方属于哪个一的一方
	private ProxyTeacher proxyTeacher;
	private String endDateMax;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getProxyTeacherId() {
		return proxyTeacherId;
	}
	public void setProxyTeacherId(Integer proxyTeacherId) {
		this.proxyTeacherId = proxyTeacherId;
	}
	public ProxyTeacher getProxyTeacher() {
		return proxyTeacher;
	}
	public void setProxyTeacher(ProxyTeacher proxyTeacher) {
		this.proxyTeacher = proxyTeacher;
	}
	public String getEndDateMax() {
		return endDateMax;
	}
	public void setEndDateMax(String endDateMax) {
		this.endDateMax = endDateMax;
	}

	public ProxyTeacherPoint() {
		// TODO Auto-generated constructor stub
	}


	public ProxyTeacherPoint(Integer id, Double point, String startDate, String endDate, Integer proxyTeacherId,
			ProxyTeacher proxyTeacher, String endDateMax) {
		this.id = id;
		this.point = point;
		this.startDate = startDate;
		this.endDate = endDate;
		this.proxyTeacherId = proxyTeacherId;
		this.proxyTeacher = proxyTeacher;
		this.endDateMax = endDateMax;
	}
	@Override
	public String toString() {
		return "proxyTeacherPoint [id=" + id + ", point=" + point + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", proxyTeacherId=" + proxyTeacherId + ", proxyTeacher=" + proxyTeacher + ", endDateMax=" + endDateMax
				+ "]";
	}
	
	
}
	