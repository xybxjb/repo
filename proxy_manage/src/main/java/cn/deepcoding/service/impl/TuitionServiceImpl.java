package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.FeeDao;
import cn.deepcoding.dao.TuitionDao;
import cn.deepcoding.model.Tuition;
import cn.deepcoding.service.TuitionService;
@Service
@Transactional
public class TuitionServiceImpl implements TuitionService {
	@Autowired
	private TuitionDao  tuitionDao ;
	
	@Autowired
	private FeeDao  feeDao ;
	
	@Override
	public List<Tuition> select() {
		return  tuitionDao.select();
	}

	@Override
	public List<Tuition> getById(Integer id) {
		 
		return  tuitionDao.getById(id);
	}

	@Override
	public List<Tuition> getAll() {
		
		return tuitionDao.getAll();
	}
 
	@Override
	public Tuition getTuitionId(String studyTime,Integer majorId) {
		 
		return tuitionDao.getTuitionId(studyTime,majorId);
	}

	@Override
	public void save(Tuition tuition) {
		// TODO Auto-generated method stub
		tuitionDao.save(tuition);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tuitionDao.delete(id);
	}

	@Override
	public Tuition getOneId(Integer id) {
		// TODO Auto-generated method stub
		return tuitionDao.getOneId(id);
	}

	@Override
	public void update(Tuition tuition) {
		// TODO Auto-generated method stub
		tuitionDao.update(tuition);
	}
 
}
