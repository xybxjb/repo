package cn.deepcoding.model;

import java.util.List;

public class Employee {
   private String name;
   private String userId;
   private List<String> departmentList;
   
	public String getUserId() {
	return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<String> departmentList) {
		this.departmentList = departmentList;
	} 
	   
}
