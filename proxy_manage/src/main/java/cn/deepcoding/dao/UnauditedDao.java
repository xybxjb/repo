package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.StudentAuditing;
import cn.deepcoding.model.StudentTeacherAuditing;
import cn.deepcoding.model.Unaudited;
import cn.deepcoding.page.Page;

@Repository
public interface UnauditedDao {
	// 获取所有未审核的学生信息
	List<Unaudited> list(@Param("id") Integer id, @Param("studentName") String studentName,
			@Param("className") String className, @Param("startTime") String startTime,
			@Param("endTime") String endtTime,@Param("page")Page page);

	// 获取未审核学生的单条信息
	Unaudited getById(Integer id);

	// 获取已报销和作废学生的数据
	Unaudited get(Integer id);

	// 修改学生报销的审核未通过数据
	void update(Unaudited unaudited);

	List<StudentAuditing> findStudentAuditingById(Integer id);

	// 获取总条数
	Integer getAllCount();

}
