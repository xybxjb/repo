package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.ProxyCommissionDetailsDao;
import cn.deepcoding.dao.ProxyTecherStudentDao;
import cn.deepcoding.model.ProxyTeaMonth;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherHistory;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyTecherStudentService;
@Transactional
@Service
public class ProxyTecherStudentServiceImpl implements ProxyTecherStudentService {

	@Autowired
	 private ProxyTecherStudentDao  proxyTecherStudentDao;
	@Autowired
	 private ProxyCommissionDetailsDao proxyCommissionDetailsDao;
	
	
	
	@Override
	public PageData find(Integer id,String year,String month,Page page) {
		// TODO Auto-generated method stub
			List<Integer> juniorIds=new ArrayList<Integer>();
			juniorIds=chaildTeacher(juniorIds,id);
			PageData pd=new PageData();
			if(juniorIds==null){
				List<Integer> ids=new ArrayList<>();
				ids.add(id);
				List<ProxyTeacherHistory> s	=proxyTecherStudentDao.find(ids,year,month,page);
				if(s.size()==0){
					return null;
				}
				Integer rows=proxyTecherStudentDao.getTotal(ids, year, month);
				pd.setRows(s);
				pd.setTotal(rows);
				return pd;
			}
			juniorIds.add(id);
			List<ProxyTeacherHistory> s	=proxyTecherStudentDao.find(juniorIds,year,month,page);
			Integer rows=proxyTecherStudentDao.getTotal(juniorIds, year, month);
			pd.setRows(s);
			pd.setTotal(rows);
			return pd;
	}
	private List<Integer> chaildTeacher(List<Integer> juniorIds, Integer id) {
		List<ProxyTeacher> list = new ArrayList<>();
		if(id!=null){
			list=proxyCommissionDetailsDao.getParentProxyTeacherId(id);
			if(list!=null&&list.size()>0){
				for(ProxyTeacher proxyTeacher : list){
					juniorIds.add(proxyTeacher.getId());
					chaildTeacher(juniorIds,proxyTeacher.getId());
				}
			}else{
				return null;
			}
		}
		return juniorIds;
	}
	@Override
	public Student get(Integer id) {
		// TODO Auto-generated method stub
		return proxyTecherStudentDao.get(id);
	}
	@Override
	public void save(ProxyTeaMonth proxyTeaMonth) {
		// TODO Auto-generated method stub
		proxyTecherStudentDao.save(proxyTeaMonth);
	}

}
