package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ProxyTeaMonth;
import cn.deepcoding.model.ProxyTeacherHistory;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;

@Repository
public interface ProxyTecherStudentDao {

	public List<ProxyTeacherHistory> find(@Param("ids") List<Integer> juniorIds,@Param("year")String year,@Param("month")String month,@Param("page")Page page);

	public Student get(Integer id);

	public void save(ProxyTeaMonth proxyTeaMonth);
	public Integer getTotal(@Param("ids") List<Integer> juniorIds,@Param("year")String year,@Param("month")String month);		
}
