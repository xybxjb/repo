package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.Teacher;

public interface TeacherService {
	
	public Teacher getById(Integer id);

	List<Teacher> findTeachers(Integer classroomId);
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
