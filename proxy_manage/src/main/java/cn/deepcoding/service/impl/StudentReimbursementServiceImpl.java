package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.StudentReimbursementDao;
import cn.deepcoding.model.StudentReimbursement;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.StudentReimbursementService;

@Service
@Transactional
public class StudentReimbursementServiceImpl implements StudentReimbursementService {
	@Autowired
	private StudentReimbursementDao studentReimbursementDao;

	@Override
	public void update(StudentReimbursement studentReimbursement) {
		
		studentReimbursementDao.update(studentReimbursement);
	}
	
	
	@Override
	public PageData list(StudentReimbursement studentReimbursement, Page page) {
		Integer total = studentReimbursementDao.getTotal(studentReimbursement);
		List<StudentReimbursement> list = studentReimbursementDao.list(studentReimbursement, page);
		PageData data = new PageData();
		data.setRows(list);
		data.setTotal(total);
		return data;
		
	}
	@Override
	public List<StudentReimbursement> listStudentReimbursement() {
		return studentReimbursementDao.ListStudentReimbursement();
	}

	
	
}
