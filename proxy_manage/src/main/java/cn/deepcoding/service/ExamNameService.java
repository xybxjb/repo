package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.ExamName;

public interface ExamNameService {


	List<ExamName> getAll();
	
	public void save(ExamName examName);
	public void update(ExamName examName);
	public void delete(Integer id);
}
