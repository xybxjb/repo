package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.VisitRecords;

public interface VisitRecordsService {
	
	//招生老师来访记录查询所有
	public List<VisitRecords> getAll();
	
	//招生老师来访记录添加
	public void save(VisitRecords visitRecords,Double[] actualAmount,Integer[] trans,Double[] ticketAmount,String reimbVoucher,String reimbMileage);
	
	//招生老师来访记录修改
	public void update(VisitRecords visitRecords,Double[] actualAmount,Integer[] trans,Double[] ticketAmount,String reimbVoucher,String reimbMileage);
	
	public VisitRecords getById(Integer id);
	
	List<VisitRecords> getVisitRecords(String name,String purpose,String beginDateTime,String endDateTime,String startFromAddress1);

}
