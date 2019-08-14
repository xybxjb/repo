package cn.deepcoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentReimbursement;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.FeeService;
import cn.deepcoding.service.StudentReimbursementService;
import cn.deepcoding.service.StudentService;
/**
 * 
 * @author 陈大发
 *	学生报销Controller
 */
@Controller
@RequestMapping("studentReimbursement")
public class StudentReimbursementController {
	@Autowired
	private StudentReimbursementService studentReimbursementService;
	@Autowired
	private StudentService studentService;
	
	
		//返回学生报销list数据
		@RequestMapping("/list")
		@ResponseBody
		public PageData list(StudentReimbursement studentReimbursement,Page page){
			System.err.println("HelloWORLD");
			PageData pageDate = studentReimbursementService.list(studentReimbursement, page);
			List<StudentReimbursement> StudentReimbursements =(List<StudentReimbursement>) pageDate.getRows();
			StudentReimbursements.forEach(StudentReimbursement->{
				Student student =  (Student) studentService.getById(StudentReimbursement.getId());
				String studentName = student.getName();
			});
			return null;
		}
		
		//返回学生报销更新审核状态
		@RequestMapping("/update")
		@ResponseBody
		public void update(StudentReimbursement  studentReimbursement){
			studentReimbursementService.update(studentReimbursement);
		}
		
		
		
		
}
