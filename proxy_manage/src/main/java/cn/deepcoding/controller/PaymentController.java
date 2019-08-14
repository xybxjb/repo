package cn.deepcoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Payment;
import cn.deepcoding.service.PaymentService;

@Controller
@RequestMapping("payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping("/getById")
	@ResponseBody
	public List<Payment> getById(Integer id){
		return paymentService.getById(id);
	}
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Payment> getAll(){
		return paymentService.getAll();
	}
	@RequestMapping("/payment")
	public String test3() {
		return "fee/payment";
	}
	
	//添加支付方式
	@RequestMapping("/add")
	@ResponseBody
	public void add(Payment payment){
		 paymentService.add(payment);
	}
	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(Integer id){
		 paymentService.delete(id);
	}
	//回显
	@RequestMapping("/findById")
	@ResponseBody
	public Payment findById(Integer id){
		return paymentService.findById(id);
	}
	//修改
		@RequestMapping("/update")
		@ResponseBody
		public void update(Payment payment){
			 paymentService.update(payment);
		}
	//模糊搜索
	@RequestMapping("/getByName")
	@ResponseBody
	public List<Payment> getByName(Payment payment){
		return paymentService.getByName(payment);
	}
}
