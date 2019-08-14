package cn.deepcoding.dao;


import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.model.FeeDueTime;

public interface FeeDueTimeDao {
	
 
 
	 //王晓宇
	public FeeDueTime getById(Integer id);

	public FeeDueTime feeDueExist(@Param("stuId")Integer stuId, @Param("type")String type);
	@Transactional
	public void saveTuition(@Param("type")String type,@Param("entDate")Date entDate, @Param("stuId")Integer stuId, @Param("continuePay")Integer continuePay);
	public void save(FeeDueTime feeDueTime);
	public void updateTuition(@Param("type")String type, @Param("num")Integer num, @Param("stuId")Integer stuId, @Param("continuePay")Integer continuePay);
	
	public FeeDueTime select(@Param("id")Integer id,@Param("type")String type);

	public void updateTuition2(@Param("type")String string, @Param("entDate")java.sql.Date entDate, @Param("stuId")Integer id, @Param("continuePay")Integer continuePay);
	
	
	public void updateFeeDueTime(@Param("type")String string, @Param("endDate")java.sql.Date entDate, @Param("stuId")Integer id, @Param("continuePay")Integer continuePay);
} 