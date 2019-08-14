package cn.deepcoding.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.api.ApiException;

import cn.deepcoding.model.Employee;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.AttenceService;
import cn.deepcoding.service.StudentService;
/**
 *考勤Controller
 * @author 杨乐乐
 *
 */
@Controller
@RequestMapping("/attence")
public class AttenceController {
	@Resource
	private AttenceService attenceService;
	@Resource
	private StudentService studentService;
	@RequiresPermissions("attence:classAttenceCount")
	@RequestMapping("/classAttenceCount")
	 public String attence(){
		 return "attence/class_attence_count";
	 }
	
	@RequestMapping("/showClassAttenceCount")
	@ResponseBody
	public PageData showClassAttenceCount(Page page, String workDateFrom, String workDateTo, Integer classname){
		if(workDateFrom==null||workDateFrom==""){
			PageData pageData = new PageData();
			 List<?> nullList= Collections.EMPTY_LIST;
			 pageData.setRows(nullList);
			 return pageData;
		 }
		
		List<Student> students =studentService.getByClassId(page,classname);
		PageData pageData=attenceService.listAttenceCount(workDateFrom, workDateTo,students,classname);
		return pageData;
	}
	
	@RequiresPermissions("attence:studentAttence")
	@RequestMapping(value ="/studentAttence")
	 public String studentAttence(){
		 return "attence/student_attence";
	 }
	
	@RequiresPermissions("attence:bindingAttence")
	@RequestMapping("/bindingAttence")
	public String bindingAttence(){
		return "attence/binding_attence";
	}
	@RequestMapping("/bindingAttenceAutomatic")
	@ResponseBody
	public void bindingAttenceAutomatic() throws ApiException{
		attenceService.bindingAttenceAutomatic();
	}
	@RequestMapping("/ShowBindingAttence")
	@ResponseBody
	public List<Employee> ShowBindingAttence() throws ApiException{
		return attenceService.getEmployee();
	}
	@RequestMapping("/ShowStudentAttence")
	@ResponseBody
	 public PageData ShowStudentAttence(Page page,String workDateFrom, String workDateTo,
       String studentName, String tel){
		 if(workDateFrom==null||workDateFrom==""){
			 PageData pageData = new PageData();
			 List<?> nullList= Collections.EMPTY_LIST;
			 pageData.setRows(nullList);
			 return pageData;
		 }
		 String userId = studentService.getDingId(studentName, tel);
		 PageData pd =attenceService.showStudentAttence(page, workDateFrom, workDateTo, userId);
		 return  pd;
	 } 
	@RequestMapping("/selectTel")
	@ResponseBody
	public List<Student> selectTel(String name){
		return studentService.getTelByName(name);
	}
	@RequestMapping("/ShowBindingStudent")
	@ResponseBody
	public List<Student> ShowBindingStudent(){
		return studentService.getNameTel();
	}
	
	@RequestMapping("/ShowWindowStudentAttence")
	@ResponseBody
	 public PageData ShowWindowStudentAttence(Page page,
              String workDateFrom, String workDateTo, String userId){
		return attenceService.showStudentAttence(page, workDateFrom, workDateTo, userId);
	}
	
	
	@RequiresPermissions("attence:importAttence")
	 @RequestMapping(value ="/importAttence")
	 public String importAttence(){
		// 
		 return "attence/import_attence";
	 }
	 @RequestMapping(value="/importAttenceAutomatic")
	 @ResponseBody
	 public int importAttenceAutomatic(){
		
		 int sum =attenceService.automaticAttendance();
		 return sum ;
	 }
	 
	 @RequestMapping("/addBindingStudentUserId")
     @ResponseBody
     public void addBindingStudentUserId(String userId,String name,String tel){
//		 System.err.println("手动添加id"+userId);
		 studentService.updateDingIdByNameTel(userId, name, tel);
	 }
}
