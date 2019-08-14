package cn.deepcoding.service;

import cn.deepcoding.model.EmergencyPerson;

public interface EmergencyPersonService {

	public void save(EmergencyPerson emergencyPerson);
	
	public void updateemergencyPerson(Integer id,String xdianhua);
}
