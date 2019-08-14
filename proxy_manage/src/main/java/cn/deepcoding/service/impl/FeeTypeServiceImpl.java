package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.constants.ResponseCode;
import cn.deepcoding.dao.FeeDao;
import cn.deepcoding.dao.FeeTypeDao;
import cn.deepcoding.model.FeeType;
import cn.deepcoding.service.FeeTypeService;
import cn.deepcoding.util.RespCode;

@Service
@Transactional
public class FeeTypeServiceImpl implements FeeTypeService {

	@Autowired
	private FeeTypeDao feeTypeDao;
	@Autowired
	private FeeDao feeDao;
	@Override
	public void save(FeeType feeType) {
		// TODO Auto-generated method stub
		feeTypeDao.save(feeType);
	}

	@Override
	public List<FeeType> find() {
		// TODO Auto-generated method stub
		return feeTypeDao.find();
	}

	@Override
	public ResponseCode delete(Integer id) {
		// TODO Auto-generated method stub
		int size = feeDao.hasFeeTypeInfo(id);
		if(size==0) {
			feeTypeDao.delete(id);
			return ResponseCode.SUCCESS;
		}else {
			return ResponseCode.ERROR;
		}
	}


	


 
}
