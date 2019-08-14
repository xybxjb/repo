package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Major;
import cn.deepcoding.model.StudyTime;
import cn.deepcoding.service.StudyTimeService;

@Controller
@RequestMapping("studyTime")
public class StudyTimeController {
	
	@Autowired
	private StudyTimeService studyTimeService;
	
	@RequestMapping("/getById")
	@ResponseBody
	public StudyTime getById(Integer id){
		StudyTime studyTimes = studyTimeService.getById(id);
		return studyTimes;
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<StudyTime> getAll(){
		 
		return studyTimeService.getAll();
	}
	//字典表
	@RequiresPermissions("studyTime:studyTime")
	@RequestMapping("/studyTime")
	public String dataDictionary(){
		return "data_dictionary/studyTime";
	}
	//字典表做删除
	@RequestMapping("/del")
	public String delete(Integer id){
		studyTimeService.delete(id);
		return "redirect:getAll";
	}
	@RequestMapping("/add")
	public String save(StudyTime studyTime){
		studyTimeService.save(studyTime);
		return "redirect:getAll";
	}
	@RequestMapping("/update")
	public String update(StudyTime studyTime){
		studyTimeService.update(studyTime);
		return "redirect:getAll";
	}
}
