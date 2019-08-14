package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.AdvisoryTeacherDao;
import cn.deepcoding.dao.StudentConversationDao;
import cn.deepcoding.dao.TeacherDao;
import cn.deepcoding.model.AdvisoryTeacher;
import cn.deepcoding.model.StudentConversation;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.StudentConversationService;
import cn.deepcoding.util.ImagUtils;
@Service
@Transactional
public class StudentConversationServiceImpl implements StudentConversationService {
	
	@Autowired
	private StudentConversationDao studentConversationDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private AdvisoryTeacherDao advisoryTeacherDao;
	// 查询所有学生谈话记录
	@Override
	public PageData getAll(StudentConversation studentConversation,Page page) {
		List<StudentConversation> list = studentConversationDao.getAll(studentConversation,page);
		Integer allCount = studentConversationDao.getAllCount(studentConversation);
		PageData pageData = new PageData();
		pageData.setTotal(allCount);
		pageData.setRows(list);
		return pageData;
	}
	// 添加谈话记录
	@Override
	public void AddStudentConversation(StudentConversation studentConversation) {
		studentConversationDao.AddStudentConversation(studentConversation);
		
	}
	// 根据 id查询
	@Override
	public StudentConversation getById(Integer id) {
		StudentConversation studentConversation = studentConversationDao.getById(id);
		// imUurl路径
		String imgUrl = ImagUtils.imgUrl();
		studentConversation.setPic(imgUrl+studentConversation.getPic());
		return studentConversation;
	}
	// 修改 信息
	@Override
	public void updateStudentConversation(StudentConversation studentConversation) {
		studentConversationDao.updateStudentConversation(studentConversation);
		
	}
	// 删除谈话记录
	@Override
	public void deleteStudentConversation(Integer id) {
		studentConversationDao.deleteStudentConversation(id);
		
	}
	// 获取 老师 和 咨询老师的姓名
	@Override
	public List<StudentConversation> getAllName() {
		List<StudentConversation> lp = new ArrayList<StudentConversation>();
		
		List<Teacher> list = teacherDao.getAll();
		for (Teacher teacher : list) {
			StudentConversation p = new StudentConversation();
			p.setTeacherName(teacher.getName());
			lp.add(p);
			/*System.err.println(teacher.getName());*/
		}
		 List<AdvisoryTeacher> list2 = advisoryTeacherDao.teacherName();
		 for (AdvisoryTeacher advisoryTeacher : list2) {
			 StudentConversation p2 = new StudentConversation();
			p2.setTeacherName(advisoryTeacher.getName());
			lp.add(p2);
			/*System.err.println(advisoryTeacher.getName());*/
		}
		return lp;
	}

}
