package cn.deepcoding.service.impl;
import cn.deepcoding.dao.TransportationDao;
import cn.deepcoding.model.Transportation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.TransportationDao;
import cn.deepcoding.model.Transportation;
import cn.deepcoding.service.TransportationService;
@Service
@Transactional
public class TransportationServiceImpl implements TransportationService{

	@Autowired
	private TransportationDao transportationDao;
	
	@Override
	public List<Transportation> getAll() {
		// TODO Auto-generated method stub
		return transportationDao.getAll();
	}

	@Override
	public Transportation getById(Integer id) {
		// TODO Auto-generated method stub
		return transportationDao.getById(id);
	}

	@Override
	public void save(Transportation transportation) {
		// TODO Auto-generated method stub
		transportationDao.save(transportation);
	}

	@Override
	public void update(Transportation transportation) {
		// TODO Auto-generated method stub
		transportationDao.update(transportation);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		transportationDao.delete(id);
	}
 

}
