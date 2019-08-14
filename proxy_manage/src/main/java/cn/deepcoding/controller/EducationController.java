package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Education;
import cn.deepcoding.service.EducationService;

@Controller
@RequestMapping("education")
public class EducationController {
	
	@Autowired
	private EducationService educationService;
	
	@RequestMapping("/getById")
	@ResponseBody
	public Education getById(Integer id){
		 
		return educationService.getById(id);
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Education> getAll(){
		return educationService.getAll();
	}
	//字典表
	@RequiresPermissions("education:education")
	@RequestMapping("/education")
	public String dataDictionary(){
		return "data_dictionary/education";
	}
	//字典表做删除
	@RequestMapping("/del")
	public String delete(Integer id){
		educationService.delete(id);
		return "redirect:getAll";
	}
	@RequestMapping("/add")
	public String save(Education education){
		educationService.save(education);
		return "redirect:getAll";
	}
	@RequestMapping("/update")
	public String update(Education education){
		educationService.update(education);
		return "redirect:getAll";
	}
}
