package cn.deepcoding.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.ReferrerDao;
import cn.deepcoding.model.Referrer;
import cn.deepcoding.model.StudentProxyTeacher;
import cn.deepcoding.service.ReferrerService;
@Service
@Transactional
public class ReferrerServiceImpl implements ReferrerService {
	@Autowired
	private ReferrerDao referrerDao;
	@Override
	public Map<String, Object> getAll(Referrer referrer, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		Integer start = (page-1)*rows;
		List<Referrer> list = referrerDao.getAll(referrer,start,rows);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", rows);
		map.put("rows", list);
		return map;
	}

}
