package cn.deepcoding.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import cn.deepcoding.model.Education;

@Repository
public interface EducationDao {
	
	public Education getById(Integer id);
	
	public List<Education> getAll();
	
	//字典表删除
	public void delete(Integer id);
	//字典表
	public void save(Education education);
	//字典表
	void update(Education education);
}

