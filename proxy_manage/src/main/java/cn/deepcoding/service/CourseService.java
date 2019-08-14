package cn.deepcoding.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cn.deepcoding.model.Course;
import cn.deepcoding.model.Student;

public interface CourseService {
	
	public List<Course> getAll();
	//通过班级找到该班级下所有的专业
	public List<Course> getClassCourse(Integer id);
	
	public List<Course> getStudentCourses(Student student);
	
	public List<Course>  getCourses (Integer id);
	
	public List<Course> getFilterCourse(String name,String beginTime,String endTime,Integer classroomId,String examName);
	public List<Course> getExamCourses(Integer id);
	
	//字典表
	public void save(Course Course);
	//字典表
	public void update(Course Course);
	
	public Course  getById(Integer id);
	//字典表
	public void delete(Integer id);
	
	//根据专业Id获取对应的课程
	List<Course> getMajorId(Integer majorId);
	//导出详情
	public List<Course> export1(Integer id);
}
