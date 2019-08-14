package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.StudentConversation;
import cn.deepcoding.page.Page;

@Repository
public interface StudentConversationDao {
	
	// 查询所有学生谈话记录信息
	public List<StudentConversation> getAll(@Param("studentConversation")StudentConversation studentConversation,@Param("page")Page page);
	public Integer getAllCount(StudentConversation studentConversation);
	// 根据 id 查询
	public StudentConversation getById(Integer id);
	// 修改 信息
	public void updateStudentConversation(StudentConversation studentConversation);
	// 添加谈话记录
	public void AddStudentConversation(StudentConversation studentConversation);
	// 删除谈话记录
	public void deleteStudentConversation(Integer id);
	//根据id查询单个学生的谈话记录
	public List<StudentConversation> getById2(Integer id);
	//根据id查询学生谈话详情(图片)
	public String getPicById(Integer id);
}
