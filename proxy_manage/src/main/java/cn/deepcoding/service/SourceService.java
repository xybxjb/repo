package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.Source;

//招生老师数据来源Service
public interface SourceService {
	//查找所有数据来源
		List<Source> findSource();
}
