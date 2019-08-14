package cn.deepcoding.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Course;
import cn.deepcoding.model.Student;
import cn.deepcoding.service.CourseService;

@Controller
@RequestMapping("course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	//通过姓名模糊查询返回这些人一共有几门课程，课程去重复
	@RequestMapping("/getStudentCourses")
	@ResponseBody
	public List<Course>  getStudentCourses (Student student){
		System.err.println(student);
		return courseService.getStudentCourses(student);	
	}
	
	
	//通过班级id返回这个班级一共有几门课程，课程去重复
	@RequestMapping("/getClassCourse")
	@ResponseBody
	public List<Course>  getClassCourse (Integer id){
		return courseService.getClassCourse(id);	
	}
	
	
	//通过学生id返回该学生有几门课程
	@RequestMapping("/getCourses")
	@ResponseBody
	public List<Course>  getCourses (Integer id){
		return  courseService.getCourses(id);
	}
	
	
	//多种条件组合查询
	@RequestMapping("/getFilterCourse")
	@ResponseBody
	public List<Course> getFilterCourse (@RequestParam(value="name",required=false)String name,@RequestParam(value="beginTime",required=false)String beginTime,@RequestParam(value="endTime",required=false)String endTime,@RequestParam(value="classroomId",required=false)Integer classroomId,@RequestParam(value="examName",required=false)String examName) {
		return courseService.getFilterCourse(name,beginTime,endTime,classroomId,examName); 
	}
	
	
	//多种条件组合查询
	@RequestMapping("/getExamCourses")
	@ResponseBody
	public List<Course> getExamCourses (@Param("id")Integer id) {
		return courseService.getExamCourses(id); 
	}
	//字典表
	@RequiresPermissions("course:course")
	@RequestMapping("/course")
	public String dataDictionary(){
		return "data_dictionary/course";
	}
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Course> getAll(){
		return courseService.getAll();
	}
	//字典表
	@RequestMapping("/add")
	public String save(Course course){
		courseService.save(course);
		return "redirect:getAll";
	}
	//字典表
	@RequestMapping("/getById")
	@ResponseBody
	public Course getById(Integer id){
		return courseService.getById(id);
	}
	//字典表
	@RequestMapping("/update")
	public String update(Course course){
		courseService.update(course);
		return "redirect:getAll";
	}
	@RequestMapping("/del")
	public String delete(Integer id){
		courseService.delete(id);
		return "redirect:getAll";
	}
	
	@RequestMapping("/getMajorId")
	@ResponseBody
	public List<Course> getMajorId(Integer majorId) {
		
		return courseService.getMajorId(majorId);
	}
}
