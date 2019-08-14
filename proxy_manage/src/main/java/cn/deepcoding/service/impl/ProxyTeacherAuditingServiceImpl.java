package cn.deepcoding.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.ProxyTeacherAuditingDao;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherExpense;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyTeacherAuditingService;

@Transactional
@Service
public class ProxyTeacherAuditingServiceImpl implements ProxyTeacherAuditingService{

	@Autowired
	ProxyTeacherAuditingDao proxyTeacherAuditingDao;
 	
	

	@Override
	public ProxyTeacherExpense getById(Integer id) {
		// TODO Auto-generated method stub
		return proxyTeacherAuditingDao.getById(id);
	}

	@Override
	public void saveOne(Integer id ,String auditState,String auditTime) {
		Date datetime = null;
		try {
			datetime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(auditTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proxyTeacherAuditingDao.saveOne(id,auditState,datetime);
		
	}

	@Override
	public void saveTwo(Integer id, String auditState, String auditor, String auditTime) {
			Date dateTime = null;
			try {
				dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(auditTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		proxyTeacherAuditingDao.saveTwo(id,auditState,auditor,dateTime);
		
	}

	@Override
	public PageData waitToSubmit(String name, String tel, String idCard, Date starttime, Date endtime,Page page) {
		Integer row= proxyTeacherAuditingDao.getTotalWaitToSubmit(name,tel,idCard,starttime,endtime);
		List<ProxyTeacherExpense> lists=proxyTeacherAuditingDao.waitToSubmit(name,tel,idCard,starttime,endtime,page);
		PageData pd=new PageData();
		pd.setRows(lists);
		pd.setTotal(row);
		return pd;
	}
	
	@Override
	public ProxyTeacherExpense getByIdWait(Integer id) {
		return proxyTeacherAuditingDao.getByIdWait(id);
	}

	@Override
	public void update(Integer id, String auditor, String auditTime, String auditState) {
		proxyTeacherAuditingDao.update(id,auditor,auditTime,auditState);
		
	}

	


	

	

	@Override
	public PageData haveToSubmit(String name, String tel, String idCard, Date starttime, Date endtime, Page page) {
		List<ProxyTeacherExpense> lists=proxyTeacherAuditingDao.haveToSubmit(name,tel,idCard,starttime,endtime,page);
		Integer  row=proxyTeacherAuditingDao.haveTotal(name,tel,idCard,starttime,endtime);
		PageData pd=new PageData();
		pd.setRows(lists);
		pd.setTotal(row);
		return pd;
	}

	@Override
	public PageData ListAll(String name, String tel, String idCard, Date starttime, Date endtime, Page page) {
		Integer getTotal=proxyTeacherAuditingDao.getTotal(name,tel,idCard,starttime,endtime);
		List<ProxyTeacherExpense> lists=proxyTeacherAuditingDao.ListAll(name,tel,idCard,starttime,endtime,page);
		PageData pd=new PageData();
		pd.setRows(lists);
		pd.setTotal(getTotal);
		return pd;
	}

	
	public ProxyTeacher findTeacherId(Integer id){
		Integer proxyId=proxyTeacherAuditingDao.findproxyTeacher(id);
		return proxyTeacherAuditingDao.findTeacherId(proxyId);
	}

	@Override
	public ProxyTeacherExpense getBySubmitId(Integer id) {
		return proxyTeacherAuditingDao.haveToSubmitById(id);
	}
}
