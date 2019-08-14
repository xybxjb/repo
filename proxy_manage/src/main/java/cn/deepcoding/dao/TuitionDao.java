package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Tuition;

public interface TuitionDao {
 

	public List<Tuition> select();

	public List<Tuition> getById(Integer id);
	//字典表
	public List<Tuition> getAll();

	public List<Tuition> getWang(@Param("name")String name,@Param("idcard")String idcard);
 

	public Tuition getTuitionId(@Param("studyTime")String studyTime,@Param("majorId")Integer majorId);
	//字典表
	public void save(Tuition tuition);
	//字典表
	public void delete(Integer id);
	//字典表
	public Tuition getOneId(Integer id);
	//字典表
	public void update(Tuition tuition);
}
