package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.StudentConversation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface StudentConversationService {
	
	// 查询所有学生谈话记录信息
	public PageData getAll(StudentConversation studentConversation,Page page);
	// 根据 id 查询
	public StudentConversation getById(Integer id);
	// 修改 信息
	public void updateStudentConversation(StudentConversation studentConversation);
	// 添加谈话记录
	public void AddStudentConversation(StudentConversation studentConversation);
	// 删除谈话记录
	public void deleteStudentConversation(Integer id);
	// 获取 老师 和咨询老师的
	public List<StudentConversation> getAllName();
}
