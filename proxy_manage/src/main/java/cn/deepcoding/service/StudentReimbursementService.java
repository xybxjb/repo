package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.StudentReimbursement;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface StudentReimbursementService {
	
	 PageData list(StudentReimbursement studentReimbursement,Page page);
     
	 List<StudentReimbursement>  listStudentReimbursement();
	
	 void update(StudentReimbursement studentReimbursement);
	
	
}
