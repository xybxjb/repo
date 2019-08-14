package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.AdvisoryTeacherDao;
import cn.deepcoding.dao.ParentConversationDao;
import cn.deepcoding.dao.TeacherDao;
import cn.deepcoding.model.AdvisoryTeacher;
import cn.deepcoding.model.ParentConversation;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ParentConversationService;
import cn.deepcoding.util.ImagUtils;
@Service
@Transactional
public class ParentConversationServiceImpl implements ParentConversationService {
	
	@Autowired
	private ParentConversationDao parentConversationDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private AdvisoryTeacherDao advisoryTeacherDao;
	// 查询所有
	@Override
	public PageData getAll(ParentConversation parentConversation,Page page) {
		List<ParentConversation> list = parentConversationDao.getAll(parentConversation,page);
		Integer allCount = parentConversationDao.getAllCount(parentConversation);
		PageData pageData = new PageData();
		pageData.setTotal(allCount);
		pageData.setRows(list);
		return pageData;
	}
	// 根据id查询
	@Override
	public ParentConversation getById(Integer id) {
		ParentConversation parentConversation = parentConversationDao.getById(id);
		// imUurl路径
		String imgUrl = ImagUtils.imgUrl();
		parentConversation.setPic(imgUrl+parentConversation.getPic());
		return parentConversation;
	}
	// 修改
	@Override
	public void updateParentConversation(ParentConversation parentConversation) {
		parentConversationDao.updateParentConversation(parentConversation);

	}
	// 添加
	@Override
	public void AddParentConversation(ParentConversation parentConversation) {
		parentConversationDao.AddParentConversation(parentConversation);
	}
	// 删除
	@Override
	public void deleteParentConversation(Integer id) {
		parentConversationDao.deleteParentConversation(id);

	}
	// 获取 老师 和 咨询老师的姓名
	@Override
	public List<ParentConversation> getAllName() {
		List<ParentConversation> lp = new ArrayList<ParentConversation>();
		
		List<Teacher> list = teacherDao.getAll();
		for (Teacher teacher : list) {
			ParentConversation p = new ParentConversation();
			p.setTeacherName(teacher.getName());
			lp.add(p);
			/*System.err.println(teacher.getName());*/
		}
		 List<AdvisoryTeacher> list2 = advisoryTeacherDao.teacherName();
		 for (AdvisoryTeacher advisoryTeacher : list2) {
			ParentConversation p2 = new ParentConversation();
			p2.setTeacherName(advisoryTeacher.getName());
			lp.add(p2);
			/*System.err.println(advisoryTeacher.getName());*/
		}
		return lp;
	}
	
	
}
