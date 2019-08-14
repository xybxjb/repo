package cn.deepcoding.dao;

import java.util.List;

import cn.deepcoding.model.Classroom;
import cn.deepcoding.model.Major;
 
public interface MajorDao {
	

	public Major get(Integer id);
	

	//获取所有专业
	public List<Major> getAll();
	
	//通过专业id获取该数据 
	public Major getById(Integer id);
	//字典表删除
	public void delete(Integer id);
	//字典表
	public void save(Major major);
	//字典表
	void update(Major major);
}
