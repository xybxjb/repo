package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.AdvisoryTeacherDao;
import cn.deepcoding.model.AdvisoryTeacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.AdvisoryTeacherSeervice;

@Service
@Transactional
public class AdvisoryTeacherSeerviceImpl implements AdvisoryTeacherSeervice{

	@Autowired
	private AdvisoryTeacherDao advisoryTeacherDao;

	public List<AdvisoryTeacher> getById(Integer id) {
		// TODO Auto-generated method stub
		return advisoryTeacherDao.getById(id);
	}

	@Override
	public List<AdvisoryTeacher> teacherName () {
		// TODO Auto-generated method stub
		return advisoryTeacherDao.teacherName();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		advisoryTeacherDao.delete(id);
	}

	@Override
	public void save(AdvisoryTeacher advisoryTeacher) {
		// TODO Auto-generated method stub
		advisoryTeacherDao.save(advisoryTeacher);
	}

	@Override
	public void update(AdvisoryTeacher advisoryTeacher) {
		// TODO Auto-generated method stub
		advisoryTeacherDao.update(advisoryTeacher);
	}

	@Override
	public AdvisoryTeacher getOneId(Integer id) {
		// TODO Auto-generated method stub
		return advisoryTeacherDao.getOneId(id);
	}

	

}
