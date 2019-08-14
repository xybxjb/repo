package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.deepcoding.dao.CourseDao;
import cn.deepcoding.dao.ExamDao;
import cn.deepcoding.dao.ScoreDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.model.Classroom;
import cn.deepcoding.model.Course;
import cn.deepcoding.model.Exam;
import cn.deepcoding.model.Score;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamDao examDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ScoreDao scoreDao;

	@Override
	public void addExam(HttpServletRequest request) throws Exception {
		// 获取id，examname，date，classroomid，teacherid这些必要条件
		Integer studentId = Integer.valueOf(request.getParameter("id"));
		String examName = request.getParameter("examName");
		Integer id = null;
		Exam exam = examDao.getOneExam(studentId, examName);
		if (exam == null) {
			String date = request.getParameter("date");
			Integer classroomId = Integer.valueOf(request.getParameter("classroomId"));
			Integer teacherId = Integer.valueOf(request.getParameter("teacherId"));
			// 创建对象，并赋值，把对象放进exam里，在mapper.xml中使用
			Classroom classroom = new Classroom();
			classroom.setId(classroomId);
			Student student = new Student();
			student.setId(studentId);
			Teacher teacher = new Teacher();
			teacher.setId(teacherId);
			exam = new Exam();
			exam.setClassroom(classroom);
			exam.setExamDate(date);
			exam.setName(examName);
			exam.setTeacher(teacher);
			exam.setStudent(student);
			// 首先创建exam数据并获得id
			examDao.addExam(exam);
		}
		// 用枚举循环出json的name和value并进行过滤
		Enumeration names = request.getParameterNames();
		// 获取当前学生的课程，准备进行和前台准据进行匹配
		List<Course> courses = courseDao.getCourses(studentId);
		while (names.hasMoreElements()) {
			// 获取当前的name姓名相当与json的coursey值
			String course = (String) names.nextElement();
			// 通过过滤name直接找需要录入的数据名称
			if ((!(course.equals("id"))) && (!(course.equals("examName"))) && (!(course.equals("date")))
					&& (!(course.equals("classroomId"))) && (!(course.equals("teacherId")))) {
				// 获取当前课程name的值
				String courseScore = request.getParameter(course);
				// 把成绩进行过滤，不输入或者为null都不增加到数据库
				if ((!courseScore.equals("")) && (!courseScore.equals(null))) {
					// 通过学生id找到的课程进行name匹配
					for (int i = 0; i < courses.size(); i++) {
						if (course.equals(courses.get(i).getInputName())) {
							// 创建对象准备加入数据库
							Score score = new Score();
							score.setCourse(courses.get(i));
							score.setExam(exam);
							score.setScore(Integer.valueOf(courseScore));
							scoreDao.addScore(score);
						}
					}
				}
			}

		}

	}

	//
	@Override
	public PageData getStudentExam(String name, String beginTime, String endTime, Integer classroomId, String examName,
			Page page) {
		List<Exam> exams = new ArrayList<>();
		if (classroomId != null) {
			page.setRows(100);
			exams = examDao.getStudentExam(name, beginTime, endTime, classroomId, examName, page);
		} else {
			exams = examDao.getStudentExam(name, beginTime, endTime, classroomId, examName, page);
		}

		Integer count = examDao.getAllCount();
		PageData pageData = new PageData();
		pageData.setTotal(count);
		List<Course> courses = courseDao.getFilterCourse(name, beginTime, endTime, classroomId, examName);
		for (Exam exam : exams) {
			List<Score> scores = new ArrayList<Score>();
			for (Course course : courses) {
				Score score = scoreDao.getOneScore(exam.getStudent().getId(), course.getName(), exam.getName());
				if (score != null) {
					scores.add(score);
				} else {
					if (courseDao.haveCourse(exam.getStudent().getId(), course.getInputName()) != null) {
						Score score1 = new Score();
						score1.setScore(-1);
						scores.add(score1);
					} else {
						Score score1 = new Score();
						score1.setScore(-2);
						scores.add(score1);
					}
				}
			}
			exam.setScores(scores);
		}
		pageData.setRows(exams);
		return pageData;
	}

	@Override
	public Exam getById(Integer id) {
		return examDao.getById(id);
	}

	// 获取所有考试名称
	@Override
	public List<Exam> getExamName() {

		return examDao.getExamName();

	}

	@Override
	public List<Exam> getAllExamName() {

		return examDao.getAllExamName();
	}

	@Override
	public String getExamTime(String name) {
		System.err.println(name);
		String s = examDao.getExamTime(name);
		System.err.println(s);
		return s;
	}

	@Override
	public PageData getCourseIdScore(Page page, Integer majorId, Integer classId, String studentName, Integer courseId,
			String highScore) {
		List<Exam> exams = examDao.getCourseIdScore(majorId, classId, studentName, courseId, highScore, page);
		PageData pageData = new PageData();
		Integer count = studentDao.getAllCount();
		pageData.setTotal(count);
		if (highScore == null || highScore.trim() == "") {
			List<Student> students = studentDao.getByClassIdMaId(classId, majorId, studentName, page);
			for (Exam exam : exams) {
				for (Student student : students) {
					if (student.getId().equals(exam.getStudent().getId())) {
						students.remove(student);
						break;
					}
				}
			}
			for (Student student : students) {
				Exam exam = new Exam();
				exam.setStudent(student);
				exam.setClassroom(student.getClassroom());
				exams.add(exam);
			}
		}

		List<Course> courses = courseDao.getStudentCourses(null);
		for (Exam exam : exams) {
			List<Score> scores = new ArrayList<Score>();
			for (Course course : courses) {
				Score score = scoreDao.getOneScore(exam.getStudent().getId(), course.getName(), exam.getName());
				if (score != null) {
					scores.add(score);
				} else {
					if (courseDao.haveCourse(exam.getStudent().getId(), course.getInputName()) != null) {
						Score score1 = new Score();
						score1.setScore(-1);
						scores.add(score1);
					} else {
						Score score1 = new Score();
						score1.setScore(-2);
						scores.add(score1);
					}
				}
			}
			exam.setScores(scores);
		}

		// PageHelper.startPage(page.getPage(), page.getRows());
		// PageInfo<Exam> inif = new PageInfo<Exam>(exams);
		// Map<String,Object> data = new HashMap<>();
		// data.put("total", inif.getTotal());
		// data.put("rows", exams);
		System.err.println(exams.size());
		pageData.setRows(exams);
		return pageData;
	}

	@Override
	public List<Exam> export1(Integer id) {
		List<Exam> exam = examDao.getByStuId(id);
		return exam;
	}

}