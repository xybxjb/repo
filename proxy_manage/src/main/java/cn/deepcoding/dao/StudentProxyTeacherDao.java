package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.StudentProxyTeacher;

public interface StudentProxyTeacherDao {

	List<StudentProxyTeacher> getAll(@Param("st")StudentProxyTeacher studentProxyTeacher,@Param("start")Integer start,@Param("rows")Integer rows);
	
	void addStuAndProxyTeacher(@Param("studentName")String studentName, @Param("phone")String phone, @Param("teacherName")String teacherName,@Param("teacherPhone")String teacherPhone,@Param("remark")String remark);
}
