package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Teacher;
import cn.deepcoding.service.TeacherService;

@Controller
@RequestMapping("teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("findTeachers")
	@ResponseBody
	public List<Teacher> findTeachers(@RequestParam("classroomId")Integer classroomId){
		
		return teacherService.findTeachers(classroomId);
		
	}
	//字典表
	@RequiresPermissions("teacher:teacher")
	@RequestMapping("/teacher")
	public String dataDictionary(){
		return "data_dictionary/teacher";
	}
	//字典表
	@RequestMapping("/getById")
	@ResponseBody
	public Teacher getById(Integer id){
		
		return teacherService.getById(id);
	}
	//字典表
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Teacher> getAll(){
		
		return teacherService.getAll();
	}
	//字典表做删除
	@RequestMapping("/del")
	public String delete(Integer id){
		teacherService.delete(id);
		return "redirect:getAll";
	}
	@RequestMapping("/add")
	public String save(Teacher teacher){
		teacherService.save(teacher);
		return "redirect:getAll";
	}
	@RequestMapping("/update")
	public String update(Teacher teacher){
		teacherService.update(teacher);
		return "redirect:getAll";
	}
	//字典表
	@RequestMapping("/getState")
	@ResponseBody
	public List<Teacher> getState(){
		
		return teacherService.getState();
	}
}
