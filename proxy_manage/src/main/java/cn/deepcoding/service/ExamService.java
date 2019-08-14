package cn.deepcoding.service;


import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Exam;
import cn.deepcoding.model.Score;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
public interface ExamService {

	public void addExam(HttpServletRequest request)throws Exception ;
	public PageData  getStudentExam(String name,String beginTime,String endTime,Integer classroomId,String examName,Page page);
	public Exam getById(Integer id);
	public List<Exam> getExamName();
	//拿到符合该科目成绩的学生
	PageData getCourseIdScore(Page page, Integer majorId,Integer classId,String studentName, Integer courseId, String highScore);	
	//获取所有考试名称字典表
	 List<Exam> getAllExamName();
	 String getExamTime(String name);
	 //详情导出excel
	public List<Exam> export1(Integer id);
}
