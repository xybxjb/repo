package cn.deepcoding.controller;


import java.sql.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Fee;
import cn.deepcoding.model.FeeType;
import cn.deepcoding.service.FeeService;
import cn.deepcoding.service.StudentService;

@Controller
@RequestMapping("/fee")
public class FeeController {

	@Autowired
	private FeeService feeService;
	@Autowired
	private StudentService studentService;
	
	@RequiresPermissions("fee:index")
	@RequestMapping("/test2")
	public String test() {
		return "fee/fee";

	}
 


	@RequestMapping("/save")
	@ResponseBody
	public void save2(Fee fee,Integer [] feeTypeId,double [] amount,Date[] entDate){
		/*
		System.out.println("paymentId========"+fee.getPayment().getId());
		System.out.println("studentId========"+fee.getStudent().getId());
		if(fee.getType().equals("学费")){
			proxyCommissionService.save(fee);
		}
 
		feeService.save(fee);*/
		System.out.println(feeTypeId);
		feeService.save(fee,feeTypeId,amount,entDate);
	}
	@RequestMapping("/select")
	@ResponseBody
	public List<Fee> select(Integer id){
	 
		return feeService.select(id);
	}
	@RequestMapping("/getInittime")
	@ResponseBody
	public Fee getInittime(Integer id){
		Fee inittime = feeService.getInittime(id);
		System.err.println(inittime+"12345667890-========================================================================================");
		return inittime;
	}
 
	
 
}
