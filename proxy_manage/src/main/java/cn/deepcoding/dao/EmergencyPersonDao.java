package cn.deepcoding.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.EmergencyPerson;

@Repository
public interface EmergencyPersonDao {

	public void save(EmergencyPerson emergencyPerson);

	// 根据学生ID删除紧急联系人
	public void deleteemergencyPerson(Integer id);
	@Select("select * from  emergency_person where id=#{id} ")
	public EmergencyPerson emergencyPersonPersonById(Integer id);
	
	public void updateemergencyPerson(@Param("id")Integer id,@Param("xdianhua")String xdianhua);
}
