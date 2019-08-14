package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.StudentAuditing;
import cn.deepcoding.page.Page;

@Repository
public interface StudentAuditingDao {
	StudentAuditing get(Integer id);
	
	Integer getTotal(@Param("studentAuditing") StudentAuditing studentAuditing);
	
	List<StudentAuditing> list(@Param("studentAuditing")StudentAuditing studentAuditing,@Param("page")Page page);
	
	List<StudentAuditing> listAll(@Param("studentAuditing")StudentAuditing studentAuditing);
	
	void save(StudentAuditing studentAuditing);
	
	void update(StudentAuditing studentAuditing);
	
	List<StudentAuditing> findStudentAuditingById(@Param("studentId")Integer studentId);

}
