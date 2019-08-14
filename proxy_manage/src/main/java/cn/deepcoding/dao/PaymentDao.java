package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Payment;

public interface PaymentDao {
	 
	 public   List<Payment> getById(Integer id);
	 public List<Payment> getAll();
	//添加支付方式
	public void add(@Param("payment")Payment payment);
	//删除
	public void delete(Integer id);
	//回显
	public Payment findById(Integer id);
	//修改
	public void update(@Param("payment")Payment payment);
	//模糊搜索
	public List<Payment> getByName(@Param("payment")Payment payment);
}