package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.VisitFee;
import cn.deepcoding.model.VisitRecords;

@Repository
public interface VisitRecordsDao {
	
		public List<ProxyTeacher> selectPurpose(Integer proxyTeacherId);
		
		//招生老师来访记录查询所有
		public List<VisitRecords> getAll();
		
		//通过id获取对象
		public VisitRecords getById(Integer id);
		
		public void save(VisitRecords visitRecords);

		public void update(VisitRecords visitRecords);
		
		List<VisitRecords> getVisitRecords(@Param("name")String name,@Param("purpose")String purpose,@Param("beginDateTime")String beginDateTime,@Param("endDateTime")String endDateTime,@Param("startFromAddress1")String startFromAddress1);
}
