package cn.deepcoding.dao;

import java.util.List;

import cn.deepcoding.model.FeeType;

public interface FeeTypeDao {
 
	public void save(FeeType feeType);
	public List<FeeType> find();
	public FeeType get(Integer id);
	
	public void delete(Integer id);
}