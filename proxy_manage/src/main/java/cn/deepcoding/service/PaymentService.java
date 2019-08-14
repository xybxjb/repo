package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.Payment;

public interface PaymentService {
	
	 public   List<Payment> getById(Integer id);
	 public List<Payment> getAll();
	 //���֧����ʽ
	 public void add(Payment payment);
	 //ɾ��
	 public void delete(Integer id);
	 //����
	 public Payment findById(Integer id);
	 //�޸�
	 public void update(Payment payment);
	 //ģ������
	 public List<Payment> getByName(Payment payment);
}
