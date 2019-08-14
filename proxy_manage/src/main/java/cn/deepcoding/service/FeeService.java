package cn.deepcoding.service;

 
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Fee;

public interface FeeService {

	//王晓宇
	public void save(Fee fee);
	
	//王晓宇
	public List<Fee> select(Integer id);

	 public Fee selectFee(int studentId);
	//  导出详情excel
	public List<Fee> export1(Integer id);

	 // 根据学生 id 查询部分信息的个数
	  int getFeeCountByStudentId(Integer id);
	public void save(Fee fee, Integer [] feeTypeId, double[] amount, java.sql.Date[] entDate);
	/* public void save(Fee fee, String[] type, double[] amount,Date entDate); */

	public Fee getInittime(Integer id);
	 
	 
	 
}
