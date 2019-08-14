package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.StudentReimbursement;
import cn.deepcoding.page.Page;

@Repository
public interface StudentReimbursementDao {
	Integer getTotal(@Param("studentReimbursement") StudentReimbursement studentReimbursement);

	List<StudentReimbursement> list(@Param("studentReimbursement") StudentReimbursement studentReimbursement,
			@Param("page") Page page);

	Integer getById(int studentId);

	List<StudentReimbursement> ListStudentReimbursement();

	// 修改学生审核的状态
	void update(StudentReimbursement studentReimbursement);

	public void save(StudentReimbursement studentReimbursement);

	// 跟据学生iD删除学生报销表
	void deleteStudentreimbursement(Integer id);
}
