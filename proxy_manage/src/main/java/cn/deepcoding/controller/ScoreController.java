package cn.deepcoding.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.model.Exam;
import cn.deepcoding.model.Score;
import cn.deepcoding.model.Student;
import cn.deepcoding.service.ScoreService;

@Controller

@RequestMapping("/score")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	@RequiresPermissions("score:addExam")
	@RequestMapping("/addExam")
	public String addExam(){
		return "exam/add_exam";
	}
	
	@RequiresPermissions("score:findExam")
	@RequestMapping("/findExam")
	public String findExam(){
		return "exam/find_exam";
	}
	@RequiresPermissions("score:brvBar")
	@RequestMapping("/brvBar")
	public String brvbar(){
		return "exam/brv_bar";
	}
	
	
	//通过考试id获取各科成绩信息
	@RequestMapping("/getById")
	@ResponseBody
	public List<Score> getById(Integer id){	
		
		return scoreService.getById(id);
	}
	
	
	@RequestMapping("/getExamId")
	@ResponseBody
	public List<Score> getExamId(Integer examId){
		System.err.println(examId);
		return scoreService.getExamId(examId);
	}	
	//通过考试Id 限制分数，条件查询
	@RequestMapping("/getExamIdScore")
	@ResponseBody
	public Score getExamIdScore(Integer examId,String inputName){
		return scoreService.getExamIdScore(examId, inputName);
	}	
	//修改成绩
	@RequestMapping("/update")
	@ResponseBody	
	public void update(HttpServletRequest request){	
		scoreService.update(request);
	}
	
	//getScoreByCourseIdInteger
	@RequestMapping("/getScoreByCourseId")
	@ResponseBody
	public Integer getScoreByCourseId(@RequestParam("courseId")Integer courseId,@RequestParam("examId")Integer examId){	
			return scoreService.getScoreByCourseId(courseId,examId);
		}
	
	
	@RequestMapping(value="/inExcel",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String inExcle(@RequestParam(value="file",required = false)MultipartFile file,HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return scoreService.readExcelFile(file,request);
	}	
	
	
	@RequestMapping("/getStudentScore")
	@ResponseBody
	public Integer getStudentScore(Score score){
 
		return scoreService.getStudentScore(score);
	}
	
	
	
	
}
