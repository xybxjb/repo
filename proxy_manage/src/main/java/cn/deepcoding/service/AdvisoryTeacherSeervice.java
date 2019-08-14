package cn.deepcoding.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.AdvisoryTeacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface AdvisoryTeacherSeervice {
	
	public List<AdvisoryTeacher> getById(Integer id);

	public List<AdvisoryTeacher> teacherName();
	//字典表删除
	public void delete(Integer id);
	//字典表
	public void save(AdvisoryTeacher advisoryTeacher);
	//字典表
	void update(AdvisoryTeacher advisoryTeacher);
	
	public AdvisoryTeacher getOneId(Integer id);
}
