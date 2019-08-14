package cn.deepcoding.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.deepcoding.model.Score;
@Repository
public interface ScoreDao {
	//添加考试信息
	public void addScore(Score score);

	public List<Score> getExamId(@Param("examId")Integer examId);
	//通过考试Id 限制条件,分数查询
	Score getExamIdScore(@Param("examId")Integer examId,@Param("inputName")String inputName);

	//通过专业名称，学生id，考试id返回一条考试信息
	public Score getScore(@Param("inputName")String inputName,@Param("studentId") Integer studentId, @Param("examId")Integer examId);
	//通过考试成绩id获取各科成绩信息
	public List<Score> getById(Integer id);
	//修改考试信息
	public void update(@Param("examId")Integer examId,@Param("courseId")Integer courseId,@Param("fen")Integer fen);

	public Integer getScoreByCourseId(@Param("courseId")Integer courseId,@Param("examId")Integer examId);
	//通过学生姓名，课程名称，锁定课程id，  用于EXcel表导入
	public void addExcelScore(@Param("courseName")String courseName,@Param("studentName") String studentName,@Param("score") Integer score, @Param("examId")Integer examId);
	//导入excel成绩前添加前查看此课程id是否存在，防止异常
	public Integer chaYan(@Param("courseName")String courseName,@Param("studentName") String studentName, @Param("examId")Integer examId);
	//通过学生id，课程id，考试记录id查看是否有记录  高
	Score haveScore(@Param("studentId")Integer studentId,@Param("courseId")Integer courseId, @Param("examName")String examName);
	//通过score对象修改成绩  高
	void updateScore(Score score);
	//查看是否有成绩
	Integer getStudentScore(Score score);
	
	Score getOneScore(@Param("studentId")Integer studentId,@Param("courseName")String courseName, @Param("examName")String examName);
	
	// app
	// 根据 试卷id 查询成绩
	Integer getScoreByExamId(Integer examId);

	public List<Score> getExamId1(Integer id);

	public List<Score> getScoresByExamId(@Param("id")Integer id2);

}
