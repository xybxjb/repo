package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.StudyTimeDao;
import cn.deepcoding.model.StudyTime;
import cn.deepcoding.service.StudyTimeService;

@Service
@Transactional
public class StudyTimeServiceImpl implements StudyTimeService{

	@Autowired
	private StudyTimeDao studyTimeDao;

	@Override
	public StudyTime getById(Integer id) {

		return studyTimeDao.getById(id);
	}

	@Override
	public List<StudyTime> getAll() {
		// TODO Auto-generated method stub
		return studyTimeDao.getAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		studyTimeDao.delete(id);
	}

	@Override
	public void save(StudyTime StudyTime) {
		// TODO Auto-generated method stub
		studyTimeDao.save(StudyTime);
	}

	@Override
	public void update(StudyTime StudyTime) {
		// TODO Auto-generated method stub
		studyTimeDao.update(StudyTime);
	}
	
	

}
