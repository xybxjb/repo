package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.EducationDao;
import cn.deepcoding.model.Education;
import cn.deepcoding.service.EducationService;

@Service
@Transactional
public class EducationServiceImpl implements EducationService{

	@Autowired
	private EducationDao educationDao;

	@Override
	public Education getById(Integer id) {
		// TODO Auto-generated method stub
		return educationDao.getById(id);
	}

	@Override
	public List<Education> getAll() {

		return educationDao.getAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		educationDao.delete(id);
	}

	@Override
	public void save(Education education) {
		// TODO Auto-generated method stub
		educationDao.save(education);
	}

	@Override
	public void update(Education education) {
		// TODO Auto-generated method stub
		educationDao.update(education);
	}
	
	

}
