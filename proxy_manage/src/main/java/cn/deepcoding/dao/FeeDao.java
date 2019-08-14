package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Fee;
import cn.deepcoding.page.Page;

public interface FeeDao {
 
	public void save(Fee fee);

	public String countTuition(@Param("stuId")Integer stuId, @Param("type")String type);
	
	public List<Fee> select(Integer id);
		

	List<Fee> selectFee(int studentId);
 
	  //陈雪珍
	  Integer feeSelect(@Param("studentId")Integer studentId);
	  // 根据学生 id 查询部分信息
	  List<Fee> getFeeByStudentId(@Param("id")Integer id,@Param("page")Page page);
	  // 根据学生 id 查询部分信息的个数
	  int getFeeCountByStudentId(Integer id);
	  
	  // app
	  // 根据学生 id 查询部分信息
	  List<Fee> getFeeByStudentId2(Integer id);
	  //总条数
	public Integer count();

	public Fee getInittime(Integer id);

	public Integer hasFeeTypeInfo(Integer feeTypeId);
}