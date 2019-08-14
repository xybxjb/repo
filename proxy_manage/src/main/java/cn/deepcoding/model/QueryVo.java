package cn.deepcoding.model;

public class QueryVo {
	
	// 宿舍查询的一些条件
	private Integer queryNumber;  // 宿舍号
	private Integer queryType;    // 宿舍类型
	private String queryName;     // 学生姓名
	public Integer getQueryNumber() {
		return queryNumber;
	}
	public void setQueryNumber(Integer queryNumber) {
		this.queryNumber = queryNumber;
	}
	public Integer getQueryType() {
		return queryType;
	}
	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	
	
}
