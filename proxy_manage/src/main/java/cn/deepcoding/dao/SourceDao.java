package cn.deepcoding.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.deepcoding.model.Source;

//招生老师数据来源Dao
@Repository
public interface SourceDao {
	//查找所有数据来源
	List<Source> findSource();
	//根据id查找
	Source findById(Integer id);
}
