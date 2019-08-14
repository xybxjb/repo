package cn.deepcoding.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.AdvisoryTeacher;
import cn.deepcoding.model.Classroom;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ClassroomService;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {
	
	@Autowired
	private ClassroomService classroomService;
	//通过id获取这个班级对象
	@RequestMapping("/getById")
	@ResponseBody
	public Classroom getById (Integer id){
		return classroomService.getById(id);
	}
	
	//获取所有的班级，包括有结束时间的班级
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Classroom> getAll (){
		return classroomService.getAll();
	}
	
	//获取所有状态为存在的班级
	@RequestMapping("/getAllBe")
	@ResponseBody
	public List<Classroom> getAllBe(){
		return classroomService.getAllBe();
	}
	//字典表
	@RequiresPermissions("classroom:classroom")
	@RequestMapping("/classroom")
	public String dataDictionary(){
		return "data_dictionary/classroom";
		
	}
	//字典表
	@RequestMapping("/del")
	public String delete(Integer id){
		classroomService.delete(id);
		return "redirect:getAll";
	}
	//字典表
	@RequestMapping("/upd")
	public String update(Classroom classroom,@RequestParam("teacherIds")Integer[] teacherIds){
		System.err.println(classroom);
		classroomService.update(classroom);
		classroomService.updateTeacherByClassId(classroom, teacherIds);
		return "redirect:getAll";
		
	}
	//字典表
	@RequestMapping("/add")
	public String save(Classroom classroom,@RequestParam("teacherId")Integer [] teacherId){
		System.out.println(classroom+"+++++++++++++++++++++++++++++"+teacherId);
		for(int i =0;i<teacherId.length;i++){
			System.err.println(teacherId[i]);
		}
		//classroomService.save(classroom);
		classroomService.save(classroom, teacherId);
		return "redirect:getAll";
	}
	
		
}
