package cn.deepcoding.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.deepcoding.model.Exam;
import cn.deepcoding.page.Page;

@Repository
public interface ExamDao {

	// 通过考试id获取该成绩
	public Exam getById(@Param("id") Integer id);

	// 获取所有考试信息
	public List<Exam> getAll();

	// 添加考试信息
	public void addExam(Exam exam);

	// 通过学生姓名模糊查询查出数据
	public List<Exam> getStudentExam(@Param("name") String name, @Param("beginTime") String startDate,
			@Param("endTime") String endDate, @Param("classroomId") Integer classroomId,
			@Param("examName") String examName, @Param("page") Page page);

	// 获取所有考试名称
	public List<Exam> getExamName();

	// 拿到符合该科目成绩的学生
	List<Exam> getCourseIdScore(@Param("majorId") Integer majorId, @Param("classId") Integer classId,
			@Param("studentName") String studentName, @Param("courseId") Integer courseId,
			@Param("highScore") String highScore, @Param("page") Page page);

	// 此方法为导入excel时使用
	void addExcelExam(Exam exam);

	// 获取所有考试名称字典表
	List<Exam> getAllExamName();

	Exam getOneExam(@Param("id") Integer id, @Param("name") String name);

	// 查看是否有此考试名称
	Integer hasExamName(@Param("name") String name);

	String getExamTime(String name);

	// 分页查询
	public Integer getAllCount();
	
	// app
	// 根据 学生id 查询考试名称
	List<Exam> getExamByStudentId(Integer id);
	//根据id查询
	public List<Exam> getByStuId(Integer id);

}
