package cn.deepcoding.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.deepcoding.model.Exam;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ExamService;

@Controller
@RequestMapping("exam")
public class ExamController {
	@Autowired
	private ExamService examService;

	//成绩录入
	@RequestMapping("/addExam")
	@ResponseBody
	 public void addexam(HttpServletRequest request) throws Exception{
		 examService.addExam(request);
	 }
		
	//拿到符合该科目成绩的学生
	@RequestMapping("/getCourseIdScore")
	@ResponseBody
	public PageData getCourseIdScore(Page page,Integer majorId, Integer classId, String studentName, Integer courseId,
			String highScore) {
		return  examService.getCourseIdScore(page,majorId, classId, studentName, courseId, highScore);
		
		
		
	}
	
	@RequestMapping("/brvbar")
	public String index2(){
		return "exam/brvbar";
	}
	
	//通过组合查询返回考试对象
	@RequestMapping("/getStudentExam")
	@ResponseBody
	 public PageData getStudentExam(Page page,@RequestParam(value="name",required=false)String name,@RequestParam(value="beginTime",required=false)String beginTime,@RequestParam(value="endTime",required=false)String endTime,@RequestParam(value="classroomId",required=false)Integer classroomId,@RequestParam(value="examName",required=false)String examName){

		return  examService.getStudentExam(name,beginTime,endTime,classroomId,examName,page);
	 }
	
	//通过考试id获取该成绩
	@RequestMapping("/getById")
	@ResponseBody
	 public Exam getById(@RequestParam("id")Integer id){
		return  examService.getById(id);
	 }
	
	//通过考试名称返回成绩信息
	@RequestMapping("/getExamName")
	@ResponseBody
	 public List<Exam> getExamName(){
		return  examService.getExamName();
	 }
	
	
	//通过考试名称返回成绩信息
		@RequestMapping("/getAllExamName")
		@ResponseBody
		 public List<Exam> getAllExamName(){
			return  examService.getAllExamName();
		 }
	
		
		//通过考试名称返回成绩信息
		@RequestMapping("/getExamTime")
		@ResponseBody
		 public String getExamTime(String name){
			return  examService.getExamTime(name);
		 }
		
		
}
