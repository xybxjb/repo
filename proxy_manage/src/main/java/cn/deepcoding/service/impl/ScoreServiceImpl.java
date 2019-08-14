package cn.deepcoding.service.impl;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.dao.ClassroomDao;
import cn.deepcoding.dao.CourseDao;
import cn.deepcoding.dao.ExamDao;
import cn.deepcoding.dao.ScoreDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.model.Classroom;
import cn.deepcoding.model.Course;
import cn.deepcoding.model.Exam;
import cn.deepcoding.model.ReadExcel;
import cn.deepcoding.model.Score;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.service.ScoreService;
@Service
@Transactional

public class ScoreServiceImpl implements ScoreService {

		@Autowired
		private ScoreDao scoreDao;
		@Autowired
		private CourseDao courseDao;
		@Autowired
		private ExamDao examDao;
		@Autowired
		private StudentDao studentDao;
		@Autowired
		private ClassroomDao classroomDao;
	
	public Score getScore(String inputName, Integer studentId, Integer examId) {
		
		return scoreDao.getScore(inputName, studentId, examId);
	}
	@Override

	public List<Score> getExamId(Integer examId) {
		// TODO Auto-generated method stub
		return scoreDao.getExamId(examId);
	}
	public List<Score> getById(Integer id) {

		return scoreDao.getById(id);

	}
	public void update(HttpServletRequest request){	
		
		Integer examId= Integer.valueOf(request.getParameter("examId"));
		List<Course> coueses =courseDao.getExamCourses(examId);
		Enumeration coursesName =request.getParameterNames();
		while(coursesName.hasMoreElements()){
			String courseName =(String)coursesName.nextElement();
			String fenshu =request.getParameter(courseName);
			if(fenshu.equals("")){continue;}
			if(!(courseName.equals("examId"))){
				for(int i=0;i<coueses.size();i++){
					if(courseName.equals(coueses.get(i).getInputName())){
						Integer fen = scoreDao.getScoreByCourseId(coueses.get(i).getId(),examId);
							
						if(fen!=null)scoreDao.update(examId, coueses.get(i).getId(),Integer.valueOf(fenshu));
						else{
							Score score = new Score(); 
							Course course =new Course(); course.setId(coueses.get(i).getId());score.setCourse(course);
							Exam exam =new Exam();  exam.setId(examId);	score.setExam(exam);	score.setScore(Integer.valueOf(fenshu));	
							scoreDao.addScore(score);					
						}		
					}
					
				}
			}
			
			
		}
		
	}
	@Override
	public Score getExamIdScore(Integer examId, String inputName) {
		// TODO Auto-generated method stub
		return scoreDao.getExamIdScore(examId, inputName);
	}

	@Override
	public Integer getScoreByCourseId(Integer courseId, Integer examId) {
		
		return scoreDao.getScoreByCourseId(courseId,examId);
	}
	@Override
	public String readExcelFile(MultipartFile file, HttpServletRequest request) {
				String result = "";
				// 创建处理EXCEL的类  
				ReadExcel readExcel = new ReadExcel();
				// 解析excel，获取上传的事件单 
				List list  = readExcel.getExcelInfo(file);
				List list1 =(List)list.get(0); 
				String examName = (String)list1.get(0); 
				if(examName.equals("考试名称为空")){
					return "考试名称为空";
				}
				if(examDao.hasExamName(examName)==null){
					return "无此考试名称，请先录入！";
				}
				List list2 =(List)list.get(1); 	
				String examDay = (String)list2.get(0); 
				if(examDay.equals("日期不能为空")){
					return "日期不能为空";
				}
				List<Course> courses = courseDao.getAllCourse();
				if (list != null && !list.isEmpty()) {
					int suc=0; 
					int err=0; 
					int update1=0;
					List <String>init =(List)list.get(2);	
					for(int z=3;z<list.size();z++){   
						int number=0; 
						int update=0;
						List<String> one =(List<String>)list.get(z);
						String name =one.get(0);
						List<Student> students =studentDao.getNumber(name);
						if(students.size()==0) 
							return name+"：此姓名不存在，导入失败";
						if(students.size()>1) 
							return name+"：此姓名存在多人，导入失败";	
						String className =one.get(1);
						Integer classNumbers =classroomDao.getNumbers(className);
						if(classNumbers==null)
							return className+"：此班级不存在，导入失败";
						else if(classNumbers>1)	
							return className+"：此班级名称重复，导入失败";	
						Exam exam =null;
						 exam =examDao.getOneExam(students.get(0).getId(), examName);
						if(exam==null){
							Classroom classroom =new Classroom();;
							classroom.setName(className);  
							exam= new Exam();    
							exam.setClassroom(classroom);
							exam.setStudent(students.get(0));
							exam.setExamDate(examDay);  
							exam.setName(examName); 
							exam.setTeacher(students.get(0).getTeacher());
							examDao.addExcelExam(exam);	
						}	
						for(int r =2;r<one.size();r++){ 
							if(one.get(r)==null||one.get(r).equals("")){continue;}
							s1:for(int i=0;i<courses.size();i++){
								if(init.get(r).equals(courses.get(i).getName())){
									Integer id = scoreDao.chaYan(courses.get(i).getName(), name,exam.getId());
									if(id!=null){
										Score score =scoreDao.haveScore(students.get(0).getId(), id, examName);
										if(score==null){ 
											number++;
											scoreDao.addExcelScore(courses.get(i).getName(), name, Integer.valueOf(one.get(r)),exam.getId());	
										}else{ 
											number++;
											update++;
											score.setScore(Integer.valueOf(one.get(r)));
											scoreDao.updateScore(score);
										}
									}
								}			 
							}	
						}
						if(number>0)suc++;
						else {err++;}; 
						if(update>0)update1++;
					}
					result ="一共"+(list.size()-3)+"条成绩信息，成功上传"+suc+"名同学信息,覆盖信息"+update1+"条，无效信息"+err+"条";
				} else {
					result = "文件解析为空！"; 
				}
				return result;
	}
	@Override
	public Score haveScore(Integer studentId, Integer courseId, String examName) {
		
		return scoreDao.haveScore(studentId, courseId, examName);
	}
	@Override
	public Integer getStudentScore(Score score) {

			return scoreDao.getStudentScore(score);
	}
	//详情导出excel
	@Override
	public List<Score> export1(Integer id) {
		// TODO Auto-generated method stub
		List<Score> scores = new ArrayList<>();
		List<Exam> exams = examDao.getByStuId(id);
		for (Exam exam : exams) {
			scores = scoreDao.getExamId(exam.getId());
		}
		return scores;
	}


}
