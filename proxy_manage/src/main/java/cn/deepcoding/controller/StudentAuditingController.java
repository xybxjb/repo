package cn.deepcoding.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentAuditing;
import cn.deepcoding.model.StudentReimbursement;
import cn.deepcoding.service.StudentAuditingService;
import cn.deepcoding.service.StudentReimbursementService;
import cn.deepcoding.service.StudentService;
/**
 * 
 * @author 陈大发
 *	学生报销审核Controller
 */
@Controller
@RequestMapping("/studentAuditing")
public class StudentAuditingController {
	@Autowired
	private StudentAuditingService studentAuditingService;
	@Autowired
	StudentReimbursementService studentReimbursementService;
	@Autowired
	StudentService studentService;

	
	//返回学生未审核页面
	@RequiresPermissions("studentAuditing:unaudited")
	@RequestMapping("/unaudited")
	public String unaudited(){
		return "studentAuditing/unaudited";
	}
	
	
	//返回学生待报销页面
	@RequiresPermissions("studentAuditing:reimburse")
	@RequestMapping("/reimburse")
	public String reimburse(){
		
		return "studentAuditing/reimburse";
	}
	//返回学生已报销页面
	@RequiresPermissions("studentAuditing:reimbursed")
	@RequestMapping("/reimbursed")
	public String reimbursed(){
		
		return "studentAuditing/reimbursed";
	}
	//返回学生报销作废页面
	@RequiresPermissions("studentAuditing:die")
	@RequestMapping("/die")
	public String die(){
		
		return "studentAuditing/die";
	}
	
	//返回学生报销作废页面
	@RequiresPermissions("studentAuditing:index")
	@RequestMapping("/modify")
	public String modify(){
		
		return "studentAuditing/modify";
	}
	
	//返回学生报销list数据
	@RequestMapping("/listAll")
	@ResponseBody
	public StudentAuditing listAll(StudentAuditing studentAuditing){
		List<StudentReimbursement> listStudentReimbursement =studentReimbursementService.listStudentReimbursement();
		List<Student> ListStudent = new ArrayList<Student>();
		listStudentReimbursement.forEach(studentReimbursement->{
			ListStudent.add((Student) studentService.getById(studentReimbursement.getStudentId()));
		});  
		
		return studentAuditingService.listAll(studentAuditing);
	}
	
	
	
	//保存学生审核结果方法
	@RequestMapping(value="/save",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String save(StudentAuditing studentAuditing){
		return studentAuditingService.save(studentAuditing);
	}
	
	
	
}
