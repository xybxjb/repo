package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.AdvisoryTeacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.AdvisoryTeacherSeervice;

@Controller
@RequestMapping("advisoryTeacher")
public class AdvisoryTeacherController {

	@Autowired
	private AdvisoryTeacherSeervice advisoryTeacherService;
	
	//获取单个老师id
	@RequestMapping("/getById")
	@ResponseBody
	public List<AdvisoryTeacher> getById(Integer id){
		return advisoryTeacherService.getById(id);
	}
	
	@RequestMapping("/teacherName")
	@ResponseBody
	public List<AdvisoryTeacher> teacherName(){
		return advisoryTeacherService.teacherName();
	}
	
	//字典表
	@RequiresPermissions("advisoryTeacher:advisoryTeacher")
	@RequestMapping("/advisoryTeacher")
	public String dataDictionary(){
		return "data_dictionary/advisoryTeacher";
	}
	//字典表做删除
	@RequestMapping("/del")
	public String delete(Integer id){
		advisoryTeacherService.delete(id);
		return "redirect:teacherName";
	}
	@RequestMapping("/add")
	public String save(AdvisoryTeacher advisoryTeacher){
		advisoryTeacherService.save(advisoryTeacher);
		return "redirect:teacherName";
	}
	@RequestMapping("/update")
	public String update(AdvisoryTeacher advisoryTeacher){
		advisoryTeacherService.update(advisoryTeacher);
		return "redirect:teacherName";
	}
	@RequestMapping("/getOneId")
	@ResponseBody
	public AdvisoryTeacher getOneId(Integer id){
		return advisoryTeacherService.getOneId(id);
	}
}
