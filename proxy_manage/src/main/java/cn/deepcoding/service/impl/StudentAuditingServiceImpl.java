package cn.deepcoding.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.StudentAuditingDao;
import cn.deepcoding.dao.StudentReimbursementDao;
import cn.deepcoding.model.StudentAuditing;
import cn.deepcoding.model.StudentReimbursement;
import cn.deepcoding.model.User;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.StudentAuditingService;

@Service
@Transactional
public class StudentAuditingServiceImpl implements StudentAuditingService {
	@Autowired
	private StudentAuditingDao studentAuditingDao;
	@Autowired
	private StudentReimbursementDao studentReimbursementDao;
	@Autowired
	HttpServletRequest requst;
	@Override
	public StudentAuditing get(Integer id) {
		return studentAuditingDao.get(id);
	}
	
	@Override
	public PageData list(StudentAuditing studentAuditing, Page page) {
		Integer total = studentAuditingDao.getTotal(studentAuditing);
		List<StudentAuditing> list = studentAuditingDao.list(studentAuditing, page);
		PageData data = new PageData();
		data.setRows(list);
		data.setTotal(total);
		return data;
	}
	@Override
	public StudentAuditing listAll(StudentAuditing studentAuditing) {
		return (StudentAuditing) studentAuditingDao.listAll(studentAuditing);
	}
	
	@Override
	public String save(StudentAuditing studentAuditing){
		User user =(User)requst.getSession().getAttribute("user");
		if(user==null){return "用户请先登录";}
		StudentReimbursement studentReimbursement = new StudentReimbursement();
		studentReimbursement.setStudentId(studentAuditing.getStudentId());
		studentReimbursement.setAuditState(studentAuditing.getAuditStatus());
		studentAuditing.setAuditor(user.getName());
		studentReimbursementDao.update(studentReimbursement);
		studentAuditingDao.save(studentAuditing);
		return "修改成功";
	}
}
