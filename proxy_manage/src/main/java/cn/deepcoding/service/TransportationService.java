package cn.deepcoding.service;

 
import cn.deepcoding.model.Transportation;

 
 
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.deepcoding.model.Transportation;

@Repository
public interface TransportationService {
	 
	 

	 	List<Transportation> getAll();
 	//字典表
	void save(Transportation transportation);
	//字典表
	Transportation getById(Integer id);
	//字典表
	void update(Transportation transportation);
	//字典表
	void delete(Integer id);
}
 
