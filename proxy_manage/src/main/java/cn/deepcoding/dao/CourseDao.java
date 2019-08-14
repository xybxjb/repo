package cn.deepcoding.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import cn.deepcoding.model.Course;
import cn.deepcoding.model.Exam;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;

@Repository
public interface CourseDao {
	
	//根据考试课程id获取
	public Course  getById(Integer id);
	//获取所有考试数据 
	public List<Course> getAll();
	//通过学生id获取所学课程
	public List<Course> getCourses(Integer id);
	//通过班级查找该班考试需要多少课程
	public List<Course> getClassCourse(Integer id);
	//通过姓名模糊查询返回这些人一共有几门课程，课程去重复
	public List<Course> getStudentCourses(Student student);
	//通过一条考试成绩id信息获取他学的哪些科目
	public List<Course> getExamCourses(Integer id);
	//多种条件组合查询
	public List<Course> getFilterCourse(@Param("name")String name,@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("classroomId")Integer classroomId,@Param("examName")String examName);
	//获取所有课程，去重复
	public List<Course> getAllCourse();
	
	Integer haveCourse(@Param("studentId")Integer studentId,@Param("inputName")String inputName);
	//字典表
	public void save(Course Course);
	//字典表
	public void update(Course Course);
	//字典表
	public void delete(Integer id);
	
	//根据专业Id获取对应的课程
	List<Course> getMajorId(@Param("majorId")Integer majorId);
	
}	
