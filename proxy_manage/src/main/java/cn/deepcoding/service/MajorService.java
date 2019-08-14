package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.Major;

public interface MajorService {
	
	public List<Major> getAll();
	
	public Major getById(Integer id);
	
	//字典表删除
	public void delete(Integer id);
	//字典表
	public void save(Major major);
	//字典表
	void update(Major major);
}
