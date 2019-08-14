package cn.deepcoding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.EmergencyPersonDao;
import cn.deepcoding.model.EmergencyPerson;
import cn.deepcoding.service.EmergencyPersonService;

@Service
@Transactional
public class EmergencyPersonServiceImpl implements EmergencyPersonService{

	@Autowired
	private EmergencyPersonDao emergencyPersonDao;
	
	@Override
	public void save(EmergencyPerson emergencyPerson) {
		// TODO Auto-generated method stub
		emergencyPersonDao.save(emergencyPerson);
	}

	@Override
	public void updateemergencyPerson(Integer id, String xdianhua) {
		// TODO Auto-generated method stub
		emergencyPersonDao.updateemergencyPerson(id, xdianhua);
	}

}
