package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Payment;

public interface PaymentDao {
	 
	 public   List<Payment> getById(Integer id);
	 public List<Payment> getAll();
	//���֧����ʽ
	public void add(@Param("payment")Payment payment);
	//ɾ��
	public void delete(Integer id);
	//����
	public Payment findById(Integer id);
	//�޸�
	public void update(@Param("payment")Payment payment);
	//ģ������
	public List<Payment> getByName(@Param("payment")Payment payment);
}