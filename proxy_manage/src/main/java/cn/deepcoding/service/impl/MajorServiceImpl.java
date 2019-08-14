package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.deepcoding.dao.MajorDao;
import cn.deepcoding.model.Major;
import cn.deepcoding.service.MajorService;

@Service
@Transactional
public class MajorServiceImpl implements MajorService{
	
	@Autowired
	private MajorDao majorDao;

	@Override
	public Major getById(Integer id) {
		// TODO Auto-generated method stub
		return majorDao.getById(id);
	}

	@Override
	public List<Major> getAll() {
		// TODO Auto-generated method stub
		return majorDao.getAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		majorDao.delete(id);
	}

	@Override
	public void save(Major major) {
		// TODO Auto-generated method stub
		majorDao.save(major);
	}

	@Override
	public void update(Major major) {
		// TODO Auto-generated method stub
		majorDao.update(major);
	}

}
