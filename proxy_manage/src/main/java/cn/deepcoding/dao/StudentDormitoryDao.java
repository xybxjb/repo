package cn.deepcoding.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.deepcoding.model.Dormitory;
import cn.deepcoding.model.StudentDormitory;

@Repository
public interface StudentDormitoryDao {

	// 通过宿舍号查询所有
	List<StudentDormitory> FindStudentDormitoryBydid(Integer did);

	// 向宿舍添加学生
	void StudentDormitoryAdd(StudentDormitory studentdormitory);

	// 通过学生ID与宿舍ID进行添加离开宿舍的时间
	void UpdatestudentdormitoryBySidAndDid(StudentDormitory sd);

	// 根据ID修改宿舍长信息
	void DormitorylleaderSystemByid(StudentDormitory sd);

	// 根据学生ID修改宿舍长信息
	void DormitorylleaderSystemBysid(StudentDormitory sd);

	// 根据学生住宿ID修改住宿时间
	void UpdatestudentdormitorById(StudentDormitory studentDormitory);
	//通过学生id查看住宿情况
	StudentDormitory getBoarder(Integer id);

	


}
