package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.AdvisoryTeacher;
import cn.deepcoding.model.Classroom;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.page.Page;

@Repository
public interface ClassroomDao {

	//通过班级id获取该对象
	public Classroom getById(Integer id);
	
	//获取所有的班级，包括有结束时间的班级
	public List<Classroom> getAll();
	
	//获取所有状态为存在的班级
	public List<Classroom> getAllBe();
	
	//通过班级名称查看是否有重复
	Integer getNumbers(@Param("name")String name);
	//字典表班级作删除
	void delete(Integer id);
	//字典表班级作更改
	void update(Classroom classroom);
	//字典表班级作保存
	void saveClassroom(Classroom classroom);
	void saveTeacherClassroom(@Param("classroom")Classroom classroom,@Param("teacherId")Integer teacherId);
	//通过班级id获取老师
	List<Teacher> getTeacherByClassroom(Integer classroomId);
	
	//编辑移除老师 
	void updateTeacherByClassId(@Param("classroom")Classroom classroom,@Param("teacherIds")Integer[] teacherIds);
	//编辑添加老师 
	void insertTeacherByClassId(@Param("classroom")Classroom classroom,@Param("teacherId")Integer teacherId);
	
}
