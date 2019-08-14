package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.Tuition;

public interface TuitionService {
	 
	public List<Tuition> select();

	public  List<Tuition> getById(Integer id);
	//字典表
	public List<Tuition> getAll();
 
	public Tuition getTuitionId(String studyTime,Integer majorId);
	//字典表
	public void save(Tuition tuition);
	//字典表
	public void delete(Integer id);
	//字典表
	public Tuition getOneId(Integer id);
	//字典表
	public void update(Tuition tuition);
}
