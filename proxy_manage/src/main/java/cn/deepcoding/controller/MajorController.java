package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Major;
import cn.deepcoding.service.MajorService;

@Controller
@RequestMapping("major")
public class MajorController {

	@Autowired
	private MajorService  majorService;
	
	//通过专业id获取该数据
	@RequestMapping("/getById")
	@ResponseBody
	public Major getById(Major major , Integer id){
		
		return majorService.getById(id);
	}

	//获取所有专业
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Major> getAll(){

		return majorService.getAll();
	}
	//字典表
	@RequiresPermissions("major:major")
	@RequestMapping("/major")
	public String dataDictionary(){
		return "data_dictionary/major";
	}
	//字典表做删除
	@RequestMapping("/del")
	public String delete(Integer id){
		majorService.delete(id);
		return "redirect:getAll";
	}
	@RequestMapping("/add")
	public String save(Major major){
		majorService.save(major);
		return "redirect:getAll";
	}
	@RequestMapping("/update")
	public String update(Major major){
		majorService.update(major);
		return "redirect:getAll";
	}
}
