package cn.deepcoding.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.StudentProxyTeacher;
import cn.deepcoding.service.StudentProxyTeacherService;

@Controller
@RequestMapping("/studentProxyTeacher")
public class StudentProxyTeacherController {
	@Autowired
	private StudentProxyTeacherService studentProxyTeacherService;
	
	@RequiresPermissions("studentProxyTeacher:index")
	@RequestMapping("/index")
	public String index(){
		return "student/studentProxyTeacher";
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public Map<String,Object> getAll(StudentProxyTeacher studentProxyTeacher,Integer page,Integer rows){
		Map<String,Object> list = studentProxyTeacherService.getAll(studentProxyTeacher,page,rows);
		return list;
	}
}
