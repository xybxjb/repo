package cn.deepcoding.service;


import java.util.List;

import com.taobao.api.ApiException;

import cn.deepcoding.model.*;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
/**
 * 考勤Service
 * @author 杨乐乐
 *
 */
public interface AttenceService {
	//考勤数据自动导入数据库
	int automaticAttendance();
	//根据用户userID查询考勤表
	 PageData showStudentAttence(Page page,
             String workDateFrom, String workDateTo,
             String userId);
	 
	//根据UserId查询考勤统计表
	 PageData listAttenceCount(String workDateFrom, String workDateTo,List<Student> students,int classId);
	 //自动绑定学生考勤信息
	 void bindingAttenceAutomatic() throws ApiException;
	 //查询员工部门信息
	 List<Employee> getEmployee() throws ApiException;
	 
} 
