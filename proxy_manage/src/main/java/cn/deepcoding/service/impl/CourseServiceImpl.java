package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.CourseDao;
import cn.deepcoding.model.Course;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;
import cn.deepcoding.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Override
	public List<Course> getAll() {
		// TODO Auto-generated method stub

		return courseDao.getAll();

	}

	@Override
	public List<Course> getClassCourse(Integer id) {
		return courseDao.getClassCourse(id);
	}

	@Override
	public List<Course> getStudentCourses(Student student) {
		return courseDao.getStudentCourses(student);
	}

	@Override
	public List<Course> getCourses(Integer id) {
		return courseDao.getCourses(id);
	}

	@Override
	public List<Course> getFilterCourse(String name, String beginTime, String endTime, Integer classroomId,
			String examName) {
		return courseDao.getFilterCourse(name, beginTime, endTime, classroomId, examName);
	}

	@Override
	public List<Course> getExamCourses(Integer id) {

		return courseDao.getExamCourses(id);
	}

	@Override
	public void save(Course Course) {
		// TODO Auto-generated method stub
		courseDao.save(Course);
	}

	@Override
	public void update(Course Course) {
		// TODO Auto-generated method stub
		courseDao.update(Course);
	}

	@Override
	public Course getById(Integer id) {
		// TODO Auto-generated method stub
		return courseDao.getById(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		courseDao.delete(id);
	}

	@Override
	public List<Course> getMajorId(Integer majorId) {
		// TODO Auto-generated method stub
		return courseDao.getMajorId(majorId);
	}
//导出详情excel
	@Override
	public List<Course> export1(Integer id) {
		// TODO Auto-generated method stub
		return courseDao.getCourses(id);
	}

}
