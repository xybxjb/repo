package cn.deepcoding.controller;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.ExamName;
import cn.deepcoding.service.ExamNameService;

@Controller
@RequestMapping("examName")
public class ExamNameController {
	@Autowired
	private ExamNameService examNameService;
	@RequiresPermissions("examName:examName")
	@RequestMapping("/examName")
	public String examName(){
		return "data_dictionary/examName";
	}
	@RequestMapping("/getAll")
	@ResponseBody 
	public List<ExamName> getAll(){
		return examNameService.getAll();
	}
	@RequestMapping("/add")
	public String save(ExamName examName){
		examNameService.save(examName);
		return "redirect:getAll";
	}
	@RequestMapping("/update")
	public String update(ExamName examName){
		examNameService.update(examName);
		return "redirect:getAll";
	}
	@RequestMapping("/del")
	public String delete(Integer id){
		examNameService.delete(id);
		return "redirect:getAll";
	}
}
