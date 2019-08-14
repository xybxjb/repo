package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.VisitFee;

public interface VisitFeeDao {
	
	public List<VisitFee> selectTransportation(@Param("visitRecordsId")Integer visitRecordsId);
	
	public void save(@Param("visitFees")List<VisitFee> visitFees);

	public List<VisitFee> listAll();
	public void delete(@Param("visitRecordsId")Integer visitRecordsId);
}
