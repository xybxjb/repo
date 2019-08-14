package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.ProxyTeacherDao;
import cn.deepcoding.dao.VisitFeeDao;
import cn.deepcoding.dao.VisitRecordsDao;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.Transportation;
import cn.deepcoding.model.VisitFee;
import cn.deepcoding.model.VisitRecords;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.VisitRecordsService;
import cn.deepcoding.util.ImagUtils;

@Service
@Transactional
public class VisitRecordsServiceImpl implements VisitRecordsService{
	@Autowired
	private VisitRecordsDao visitRecordsDao;
	@Autowired
	private VisitFeeDao visitFeeDao;
	@Autowired
	private ProxyTeacherDao proxyTeacherDao;
	@Override
	public List<VisitRecords> getAll() {
		// TODO Auto-generated method stub
		return visitRecordsDao.getAll();
	}
	

	@Override
	public VisitRecords getById(Integer id) {
		// TODO Auto-generated method stub
		VisitRecords byId = visitRecordsDao.getById(id);
		// imUurl路径
		String imgUrl = ImagUtils.imgUrl();
		byId.setReimVoucher(imgUrl+byId.getReimVoucher());
		return byId;
	}


	@Override
	public void save(VisitRecords visitRecords,Double[] actualAmount,Integer[] trans,Double[] ticketAmount,String reimbVoucher,String reimbMileage) {
		// TODO Auto-generated method stub
		visitRecordsDao.save(visitRecords);
		List <VisitFee> visitFees= new ArrayList<VisitFee>();
		for(int i=0;i<trans.length;i++){
			VisitFee visitFee = new VisitFee();
			visitFee.setVisitRecords(visitRecords);
			Transportation tran = new Transportation();
			tran.setId(trans[i]);
			visitFee.setTrans(tran);
			visitFee.setTicketAmount(ticketAmount[i]);
			visitFee.setActualAmount(actualAmount[i]);
			visitFee.setReimbMileage(reimbMileage);
			visitFee.setReimbVoucher(reimbVoucher);
			visitFees.add(visitFee);	
			}
			visitFeeDao.save(visitFees);
	}


	@Override
	public void update(VisitRecords visitRecords, Double[] actualAmount, Integer[] trans, Double[] ticketAmount,
			String reimbVoucher, String reimbMileage) {
		visitRecordsDao.update(visitRecords);
		visitFeeDao.delete(visitRecords.getId());
		List <VisitFee> visitFees= new ArrayList<VisitFee>();
		for(int i=0;i<trans.length;i++){
			VisitFee visitFee = new VisitFee();
			visitFee.setVisitRecords(visitRecords);
			Transportation tran = new Transportation();
			tran.setId(trans[i]);  visitFee.setTrans(tran);
			visitFee.setTicketAmount(ticketAmount[i]);
			visitFee.setActualAmount(actualAmount[i]);
			visitFee.setReimbMileage(reimbMileage);
			visitFee.setReimbVoucher(reimbVoucher);
			visitFees.add(visitFee);	
			}
			visitFeeDao.save(visitFees);
	}


	@Override
	public List<VisitRecords> getVisitRecords(String name, String purpose, String beginDateTime, String endDateTime,
			String startFromAddress1) {
		
		return visitRecordsDao.getVisitRecords(name, purpose, beginDateTime, endDateTime, startFromAddress1);
	}


}
