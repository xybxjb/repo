package cn.deepcoding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Dormitory;
import cn.deepcoding.model.Graduated;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;

public interface StudentDao {

	public void save(Student student);
	
	/* 通过班级ID获取学生并进行分页 */
	List<Student> getByClassIdLimit(@Param("page") Page page, @Param("classId") Integer classId);

	// 通过班级id,专业,名字获取学生
	List<Student> getByClassIdMaId(@Param("classId") Integer classId, @Param("majorId") Integer majorId,
			@Param("studentName") String studentName, @Param("page")Page page);

	// 通过学生id获取一个学生对象

	public Student getStudentById(Integer id);

	public Student getById(int id);

	// 姓名模糊查询 并按专业进行排序,返回一个对象
	public Student getByName(String name);

	// 通过班级id获取该班学生
	public List<Student> getByClassId(int id);

	public void update(Student student);

	public List<Student> list(Student student);

	// 姓名模糊查询 并按专业进行排序,返回一个集合
	public List<Student> listAll(@Param("stuName") String stuName, @Param("stuIdCard") String stuIdCard);

	// 獲取所有學生信息
	public List<Student> getStudent(Student student);

	// 通过班级id获取该班学生 并按专业进行排序
	public List<Student> getClassStudents(Integer id);

	// 考勤需要，勿改动
	String getByNameAndPhone(@Param("name") String name, @Param("tel") String tel);

	int getStudengCountByClassId(int classId);

	// 根据姓名获取手机号 考勤需要，勿动
	List<Student> getTelByName(String name);

	// 通过姓名查看是否有重复
	public List<Student> getNumber(@Param("name") String name);

	// 设置模拟班级id
	public void updateTestClassId(@Param("testClassId") Integer testClassId, @Param("studentIds") Integer[] studentIds);

	// 获取学生信息
	public List<Student> getName(Integer id);

	// 获取学生DingID集合
	List<String> listDingId();

	// 通过学生姓名以及电话添加学生Dingid信息
	void updateDingIdByNameTel(@Param("userId") String userId, @Param("name") String name, @Param("tel") String tel);

	// 添加Dingid
	int updateDingId(@Param("userId") String userId, @Param("name") String name);

	// 查询重复学生姓名
	int countByName(String name);

	// 查询没有DingId的学生
	List<Student> getNameTel();

	// 获取所有的模拟班级Id
	List<Integer> listTestClassroomId();

	// 通过模拟班级Id获取学生
	List<Student> getByTestClassId(Integer testClassId);

	public List<Student> select(@Param("name") String name, @Param("sex") String sex, @Param("idcard") String idcard);

	/**
	 * 考试查询
	 */
	List<Student> getSomeStudents(@Param("page") Page page, @Param("student") Student student);

	// 查询所有页数
	Integer getAllCount();

	// 移除模拟班级Id
	void deleteTestClassId(Integer id);

	// 将模拟班级设置为实际班级
	void updateClassId(@Param("testClassId") Integer testClassId, @Param("studentIds") Integer[] studentIds);

	// 王晓宇
	public List<Student> getAll(@Param("stuName") String stuName, @Param("stuIdCard") String idCard);

	// 王晓宇
	public Student getOneStudent(@Param("id") Integer id);

	// 根据宿舍 id 查询
	public List<Student> selectByDormitoryId(Integer id);

	// 根据招生老师 id 获取学生信息
	List<Map<String, Object>> getStudentByProxyTeacherId(@Param("proxyTeacherId") Integer proxyTeacherId,
			@Param("page") Page page);

	// 根据招生老师 id 获取学生信息个数
	int getStudentCountByProxyTeacherId(Integer proxyTeacherId);

	// 查询学生部分信息
	Map<String, Object> getStudentById2(Integer id);
	/*
	 *   app
	 */
	// 根据招生老师 id 查询学生信息
	List<Student> getStudentByProxyTeacherId2(@Param("proxyTeacherId")Integer proxyTeacherId,@Param("page") Page page);
	// 根据学生 id 查询学生信息
	Student getStudentById3(Integer id);
	// 根据学生 id 查询 钉钉唯一标识符
	Student getAttenceByStudentId(Integer id);
	// 查询所有学生部分信息
	List<Student> getAttenceByStudent();
	/**
	 * 2019-03-19
	 * 增加删除学生信息功能
	 * */

	 void deleteStudent(Integer id);
	 //查找所有
	public List<Student> findAll();
	//通过学生姓名查找联系方式
	public List<Student> findStuByName(@Param("student")Student student);

	public void updateFindJob(@Param("graduated")Graduated graduated);

	//根据招生老师 id 查询学生信息不分页
	List<Student> getStudentCheckingInByUserId(@Param("proxyTeacherId")Integer proxyTeacherId);

	// 学生姓名模糊查询 
	public List<Student> likeStuName(@Param("stuName") String stuName,@Param("proxyTeacherId")Integer proxyTeacherId);
	
	public List<Student> getGraduated(@Param("name")String name,@Param("address")String address);


}
