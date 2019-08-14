package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Tuition;
import cn.deepcoding.service.TuitionService;

@Controller
@RequestMapping("/tuition")
public class TuitionController {
	
	@Autowired
	private TuitionService tuitionService;
	
	@RequestMapping("/getById")
	@ResponseBody
	public List<Tuition> getById(Tuition Tuition , Integer id){
		return tuitionService.getById(id);
	}

	@RequestMapping("/getAll")
	@ResponseBody
	public List<Tuition> getAll(){
	
		return tuitionService.getAll();
	}
 
	//通过专业与时长获取学费
	@RequestMapping("/getTuitionId")
	@ResponseBody
		public Tuition getTuitionId (@RequestParam("studyTime")String studyTime,@RequestParam("majorId")Integer majorId){
			return tuitionService.getTuitionId(studyTime,majorId);	
		}
	//字典表进入
	@RequiresPermissions("tuition:tuition")
	@RequestMapping("/tuition")
	public String tuition(){
		return "data_dictionary/tuition";
	}
	//字典表保存
	@RequestMapping("/add")
	public String save(Tuition tuition){
		tuitionService.save(tuition);
		return "redirect:getAll";
	}
	//字典表删除
	@RequestMapping("/del")
	public String delete(Integer id){
		tuitionService.delete(id);
		return "redirect:getAll";
	}
	@RequestMapping("/getOneId")
	@ResponseBody
	public Tuition getOneId(Integer id){
		return tuitionService.getOneId(id);
	}
	@RequestMapping("/update")
	public String update(Tuition tuition){
		tuitionService.update(tuition);
		return "redirect:getAll";
	}
}
