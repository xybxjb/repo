package cn.deepcoding.dao;


import java.util.List;
import org.springframework.stereotype.Repository;
import cn.deepcoding.model.ExamName;


 @Repository
public interface ExamNameDao {
	
	
	public List<ExamName> getAll();

	public void save(ExamName examName);
	
	public void update(ExamName examName);
	
	public void delete(Integer id);
}

