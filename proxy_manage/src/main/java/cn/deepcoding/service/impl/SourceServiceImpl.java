package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.deepcoding.dao.SourceDao;
import cn.deepcoding.model.Source;
import cn.deepcoding.service.SourceService;
@Service
public class SourceServiceImpl implements SourceService {
	@Autowired
private SourceDao sourceDao;
//查找招生老师所有的数据来源
	@Override
	public List<Source> findSource() {
		// TODO Auto-generated method stub
		List<Source> findSource = sourceDao.findSource();
		return findSource;
	}

	

}
