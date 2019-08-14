package cn.deepcoding.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.Teacher;

@Repository
public interface TeacherDao {
	
	public Teacher getById(Integer id);
	
	public List<Teacher> findTeachers(@Param("classroomId")Integer classroomId);
	//字典表
	public List<Teacher> getTeacherByClassroom(Integer id);
	//字典表
	public List<Teacher> getAll();
	//字典表删除
	public void delete(Integer id);
	//字典表
	public void save(Teacher teacher);
	//字典表
	void update(Teacher teacher);
	//获取teacher的状态
	public List<Teacher> getState();
}	
