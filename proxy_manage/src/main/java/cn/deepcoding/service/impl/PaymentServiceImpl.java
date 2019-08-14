package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.PaymentDao;
import cn.deepcoding.model.Payment;
import cn.deepcoding.service.PaymentService;
@Service
@Transactional
public class PaymentServiceImpl implements  PaymentService {

	@Autowired
	private  PaymentDao paymentDao;

	@Override
	public List<Payment> getById(Integer id) {
		 
		return paymentDao.getById(id);
	}

	@Override
	public List<Payment> getAll() {
		// TODO Auto-generated method stub
		return paymentDao.getAll() ;
	} 
	//���֧����ʽ
	@Override
	public void add(Payment payment) {
		// TODO Auto-generated method stub
		paymentDao.add(payment);
	}
	//ɾ��
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		paymentDao.delete(id);
	}
//����
	@Override
	public Payment findById(Integer id) {
		// TODO Auto-generated method stub
		return paymentDao.findById(id);
	}
//�޸�
	@Override
	public void update(Payment payment) {
		// TODO Auto-generated method stub
		paymentDao.update(payment);
	}
//ģ������
@Override
	public List<Payment> getByName(Payment payment) {
		// TODO Auto-generated method stub
		return paymentDao.getByName(payment);
	} 
	
	
}
