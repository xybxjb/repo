package cn.deepcoding.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Classroom;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface ClassroomService {
	
	public Classroom getById(Integer id);
	
	//获取所有的班级，包括有结束时间的班级
	public List<Classroom> getAll();
	
	//获取所有状态为存在的班级
	public List<Classroom>  getAllBe();
	//字典表班级作删除
	void delete(Integer id);
	//字典表班级作更改
	void update(Classroom classroom);
	//字典表班级作保存
	void save(Classroom classroom,Integer[] teacherId);
	//编辑移除老师 
	void updateTeacherByClassId(Classroom classroom,Integer[] teacherIds);
	
		
}
