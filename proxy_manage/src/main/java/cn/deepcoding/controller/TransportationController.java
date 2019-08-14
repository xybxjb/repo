package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Transportation;
import cn.deepcoding.service.TransportationService;

@Controller
@RequestMapping("transportation")
public class TransportationController {
		
	@Autowired
	private TransportationService transportationService;
	
	@RequestMapping("getAll")
	@ResponseBody
	public List<Transportation> getAll(){
		
		return transportationService.getAll();
	}
	
	//字典表
	
	@RequiresPermissions("transportation:transportation")
	@RequestMapping("/transportation")
	public String transportation(){
		return "data_dictionary/transportation";
	}
	//字典表
	@RequestMapping("/add")
	public String save(Transportation transportation){
		transportationService.save(transportation);
		return "redirect:getAll";
	}
	//字典表
	@RequestMapping("/update")
	public String update(Transportation transportation){
		transportationService.update(transportation);
		return "redirect:getAll";
	}
	@RequestMapping("/getById")
	@ResponseBody
	public Transportation getById(Integer id){
		return transportationService.getById(id);
	}
	@RequestMapping("/del")
	public String delete(Integer id){
		transportationService.delete(id);
		return "redirect:getAll";
	}
}
