package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.StudyTime;

public interface StudyTimeService {

	public StudyTime getById(Integer id);
	public List<StudyTime> getAll();
	
	//字典表删除
	public void delete(Integer id);
	//字典表
	public void save(StudyTime StudyTime);
	//字典表
	void update(StudyTime StudyTime);

}
