package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.Education;

public interface EducationService {

	public Education getById(Integer id);
	
	public List<Education> getAll();
	//字典表删除
	public void delete(Integer id);
	//字典表
	public void save(Education education);
	//字典表
	void update(Education education);
}
