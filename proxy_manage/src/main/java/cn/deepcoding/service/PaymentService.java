package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.Payment;

public interface PaymentService {
	
	 public   List<Payment> getById(Integer id);
	 public List<Payment> getAll();
	 //添加支付方式
	 public void add(Payment payment);
	 //删除
	 public void delete(Integer id);
	 //回显
	 public Payment findById(Integer id);
	 //修改
	 public void update(Payment payment);
	 //模糊搜索
	 public List<Payment> getByName(Payment payment);
}
