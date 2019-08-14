package cn.deepcoding.service;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.model.Fee;
import cn.deepcoding.model.Score;

public interface ScoreService {

	List<Score> getExamId(Integer examId);

	public Score getScore(String inputName,Integer studentId,Integer examId);
	public List<Score> getById(Integer id);
	public void update(HttpServletRequest request);
	Score getExamIdScore(Integer examId,String inputName);
	public Integer getScoreByCourseId(Integer courseId,Integer examId);
	public String readExcelFile( MultipartFile file,HttpServletRequest request);
	Score haveScore(Integer studentId,Integer courseId,String examName);
	Integer getStudentScore(Score score);

	List<Score> export1(Integer id);
	


}
