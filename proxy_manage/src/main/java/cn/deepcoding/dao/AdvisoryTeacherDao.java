package cn.deepcoding.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.deepcoding.model.AdvisoryTeacher;

@Repository
public interface AdvisoryTeacherDao {

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