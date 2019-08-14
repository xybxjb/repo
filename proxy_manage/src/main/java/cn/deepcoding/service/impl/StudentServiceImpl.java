package cn.deepcoding.service.impl;

import java.text.SimpleDateFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.aop.target.SimpleBeanTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.deepcoding.dao.FeeDao;
import cn.deepcoding.dao.ScoreDao;
import cn.deepcoding.dao.FeeDueTimeDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.dao.EmergencyPersonDao;
import cn.deepcoding.dao.StudentReimbursementDao;
import cn.deepcoding.model.Course;
import cn.deepcoding.model.EmergencyPerson;
import cn.deepcoding.model.Fee;
import cn.deepcoding.model.Score;
import cn.deepcoding.model.Student;

import cn.deepcoding.model.FeeDueTime;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.dao.CourseDao;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.model.StudentReimbursement;
import cn.deepcoding.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private FeeDao feeDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private EmergencyPersonDao emergencyPersonDao;
	@Autowired
	private StudentReimbursementDao studentReimbursementDao;
	@Autowired
	private FeeDueTimeDao feeDueTimeDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ScoreDao scoreDao;

	
	@Override
	public void save(Student student, String[] cname, String[] ctel, String[] crelation) {
		student.setAddress(student.getAddress().replace(",", "-"));
		studentDao.save(student);
		for (int i = 0; i < cname.length; i++) {
			EmergencyPerson emergencyPerson = new EmergencyPerson();
			emergencyPerson.setName(cname[i]);
			emergencyPerson.setTel(ctel[i]);
			emergencyPerson.setRelation(crelation[i]);
			emergencyPerson.setStudent(student);
			emergencyPersonDao.save(emergencyPerson);
		}
		StudentReimbursement studentReimbursement = new StudentReimbursement();
		studentReimbursement.setStudentId(student.getId());
		studentReimbursement.setActualObject(student.getName());
		studentReimbursement.setActualObjectTel(student.getTel());
		studentReimbursementDao.save(studentReimbursement);
	}

	@Override
	public Student getById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.getById(id);
	}

	@Override
	public Student getByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.getByName(name);
	}

	@Override
	public List<Student> getByClassId(Page page, Integer classId) {

		return studentDao.getByClassIdLimit(page, classId);
	}

	@Override
	public List<Student> getByClassIdMaId(Integer classId, Integer majorId, String studentName) {
		// TODO Auto-generated method stub
		Page page = new Page();
		page.setPage(1);
		page.setRows(1000);
		return studentDao.getByClassIdMaId(classId, majorId, studentName,page);
	}

	@Override
	public void update(Student student) {
		if (student.getAddress() != null && student.getAddress() != "") {
			String str = student.getAddress().replaceAll(",", "-");
			student.setAddress(str);
		}
		studentDao.update(student);

	}

	@Override
	public List<Student> getStudent(Student Student) {

		return studentDao.getStudent(Student);
	}

	@Override
	public List<Student> getClassStudents(Integer id) {

		return studentDao.getClassStudents(id);
	}

	@Override
	public void updateTestClassId(Integer testClassId, Integer[] studentIds) {
		System.err.println(studentIds);
		studentDao.updateTestClassId(testClassId, studentIds);

	}

	// 考勤需要 勿动
	@Override
	public String getDingId(String name, String tel) {
		// TODO Auto-generated method stub
		return studentDao.getByNameAndPhone(name, tel);
	}

	@Override
	public List<Student> getTelByName(String name) {
		return studentDao.getTelByName(name);
	}

	@Override
	public List<Integer> listTestClassroomId() {
		// TODO Auto-generated method stub
		return studentDao.listTestClassroomId();
	}

	@Override
	public List<Student> getByClassId(int id) {
		// TODO Auto-generated method stub
		return studentDao.getByClassId(id);
	}

	@Override
	public Object getByTestClassId(Page page, Integer testClassId) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPage(), page.getRows());
		List<Student> list = studentDao.getByTestClassId(testClassId);
		PageInfo<Student> inif = new PageInfo<Student>(list);
		Map<String, Object> data = new HashMap<>();
		data.put("total", inif.getTotal());
		data.put("rows", list);
		return data;
	}

	@Override
	public Object listAll(Page page, String stuName, String stuIdCard) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPage(), page.getRows());
		List<Student> list = studentDao.listAll(stuName, stuIdCard);
		
		System.err.println(list.get(0).getPersons().get(0).getTel());
		PageInfo<Student> inif = new PageInfo<Student>(list);
		Map<String, Object> data = new HashMap<>();
		data.put("total", inif.getTotal());
		data.put("rows", list);
		return data;
	}

	@Override
	public List<Student> select(String name, String sex, String idcard) {
		// TODO Auto-generated method stub
		return studentDao.select(name, sex, idcard);
	}

	public PageData getSomeStudents(Student student, String examName,Page page) {

		if (examName == null) {
			examName = ""; 
		};
		List<Student> students = studentDao.getSomeStudents(page,student);
		Integer count = studentDao.getAllCount();
		List<Course> courses = courseDao.getStudentCourses(student);
		PageData pageData = new PageData();
		pageData.setTotal(count);
		for (Student ss : students) {
			List<Score> scores = new ArrayList<Score>();
			for (Course course : courses) {
				Score score = scoreDao.getOneScore(ss.getId(), course.getName(), examName);
				System.out.println("成绩********"+score);
				if (score != null) {
					scores.add(score);
				} else {
					Score score1 = new Score();
					if (courseDao.haveCourse(ss.getId(), course.getInputName()) != null) {
						score1.setScore(-1);
						scores.add(score1);
					} else {
						score1.setScore(-2);
						scores.add(score1);
					}

				}
			}
			ss.setScores(scores);
		}
		pageData.setRows(students);
		return pageData;
	}

	public void deleteTestClassId(Integer id) {
		studentDao.deleteTestClassId(id);

	}

	@Override
	public void updateClassId(Integer testClassId, Integer[] studentIds) {
		// TODO Auto-generated method stub
		studentDao.updateClassId(testClassId, studentIds);
	}
	// 获取学生信息

	@Override
	public List<Student> getName(Integer id) {

		return studentDao.getName(id);
	}

	// 王晓宇
//	public Object getAll(Page page, String stuName, String stuIdCard) {
//		PageHelper.startPage(page.getPage(), page.getRows());
//		List<Student> studentList = studentDao.getAll(stuName, stuIdCard);
//
//		for (Student student : studentList) {
//			String countTuition = feeDao.countTuition(student.getId(), "学费");
//			if (countTuition == null) {
//				student.setTuitionContinue("未缴清");
//			} else if (student.getTuition() > Double.parseDouble(countTuition)) {
//				student.setTuitionContinue("未缴清");
//			} else {
//				student.setTuitionContinue("已缴清");
//			}
//			String countIncidentals = feeDao.countTuition(student.getId(), "杂费");
//			if (countIncidentals == null) {
//				student.setIncidentalsContinue("未缴清");
//			} else if (student.getIncidentals() > Double.parseDouble(countIncidentals)) {
//				student.setIncidentalsContinue("未缴清");
//			} else {
//				student.setIncidentalsContinue("已缴清");
//			}
//			String countquarterage = feeDao.countTuition(student.getId(), "住宿费");
//			FeeDueTime feeDueTime = feeDueTimeDao.select(student.getId(), "住宿费");
//			if (countquarterage == null) {
//				student.setQuarterageContinue(null);
//			} else {
//
//				if (feeDueTime.getEntDate() == null) {
//					student.setQuarterageContinue(null);
//				} else {
//					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//					String dateString = formatter.format(feeDueTime.getEntDate());
//					student.setQuarterageContinue(dateString);
//				}
//
//			}
//		}
//		PageInfo<Student> inif = new PageInfo<Student>(studentList);
//		Map<String, Object> data = new HashMap<>();
//		data.put("total", inif.getTotal());
//		data.put("rows", studentList);
//		return data;
//	}

	public Object getAll(Page page, String stuName, String stuIdCard) {
		PageHelper.startPage(page.getPage(), page.getRows());
		List<Student> studentList = studentDao.getAll(stuName, stuIdCard);
		
		for (Student student : studentList) {
			String countTuition = feeDao.countTuition(student.getId(), "学费");
			if (countTuition == null) {
				student.setTuitionContinue("数据库无数据");
			} else {
				student.setTuitionContinue(feeDueTimeDao.select(student.getId(), "学费").getLastPaymentTime().toString());
			}
			String countIncidentals = feeDao.countTuition(student.getId(), "杂费");
			if (countIncidentals == null) {
				student.setIncidentalsContinue("数据库无数据");
			} else {
				student.setIncidentalsContinue(feeDueTimeDao.select(student.getId(), "杂费").getLastPaymentTime().toString());
			}
			String countquarterage = feeDao.countTuition(student.getId(), "住宿费");
			FeeDueTime feeDueTime = feeDueTimeDao.select(student.getId(), "住宿费");
			if (countquarterage == null) {
				student.setQuarterageContinue(null);
			} else {
				
				if (feeDueTime.getEntDate() == null) {
					student.setQuarterageContinue(null);
				} else {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String dateString = formatter.format(feeDueTime.getEntDate());
					student.setQuarterageContinue(dateString);
				}
				
			}
		}
		PageInfo<Student> inif = new PageInfo<Student>(studentList);
		Map<String, Object> data = new HashMap<>();
		data.put("total", inif.getTotal());
		data.put("rows", studentList);
		return data;
	}
	
	@Override
	public List<Student> getNameTel() {
		// TODO Auto-generated method stub
		return studentDao.getNameTel();
	}

	@Override
	public void updateDingIdByNameTel(String userId, String name, String tel) {
		// TODO Auto-generated method stub
		studentDao.updateDingIdByNameTel(userId, name, tel);
	}

//根据身份证判断数据库是否有该学生的信息
	@Override
	public Boolean idcardisNo(String idCard) {
		// TODO Auto-generated method stub
		 if(studentDao.getAll(null, idCard).size()>0){
			 return true;
		 }
		 return false;
	}
//删除学生
	@Override
	public String deleteStudent(Student student) {
		// TODO Auto-generated method stub
		//先根据学生ID查看是否有缴费信息
	List<Fee> feeByStudentId2 = feeDao.getFeeByStudentId2(student.getId());
	
	if(feeByStudentId2.size()>0){
		return "error";
	}
	//删除学生表   学生紧急联系人   学生报销表
	//删除学生表
	studentDao.deleteStudent(student.getId());
	//删除学生紧急联系人信息
	emergencyPersonDao.deleteemergencyPerson(student.getId());
	//删除学生报销表
	studentReimbursementDao.deleteStudentreimbursement(student.getId());
		return "success";
	}
	
	// excel导出
	@Override
	public List<Student> export() {
		List<Student> list =studentDao.findAll();// 查找所有
		return list;
	}
	// 详情excel导出
		@Override
		public Student export1(Integer id) {
			Student list =studentDao.getById(id);// 根据id查找
			return  list;
		}
		//查询所有 //下拉框
		@Override
		public List<Student> findAll() {
			// TODO Auto-generated method stub
			
			return studentDao.findAll();
		}
//通过学生姓名查找联系方式
		@Override
		public List<Student> findStuByName(Student student) {
			// TODO Auto-generated method stub
			
			return studentDao.findStuByName(student);
		}
	
}
