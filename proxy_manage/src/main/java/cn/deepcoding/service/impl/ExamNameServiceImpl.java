package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.ExamNameDao;
import cn.deepcoding.model.ExamName;
import cn.deepcoding.service.ExamNameService;

@Service
@Transactional
public class ExamNameServiceImpl implements ExamNameService {

	@Autowired
	private ExamNameDao examNameDao;


	@Override
	public List<ExamName> getAll() {
		// TODO Auto-generated method stub
		return examNameDao.getAll();
	}


	@Override
	public void save(ExamName examName) {
		// TODO Auto-generated method stub
		examNameDao.save(examName);
	}


	@Override
	public void update(ExamName examName) {
		// TODO Auto-generated method stub
		examNameDao.update(examName);
	}


	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		examNameDao.delete(id);
	}

}