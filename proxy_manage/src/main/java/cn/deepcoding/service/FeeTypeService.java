package cn.deepcoding.service;

 
import java.util.List;

import cn.deepcoding.constants.ResponseCode;
import cn.deepcoding.model.FeeType;

public interface FeeTypeService {

	public void save(FeeType feeType);
	public List<FeeType> find();
	
	public ResponseCode delete(Integer id); 
	 
}
