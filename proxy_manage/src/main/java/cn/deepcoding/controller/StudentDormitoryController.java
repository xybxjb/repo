package cn.deepcoding.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Dormitory;
import cn.deepcoding.model.StudentDormitory;
import cn.deepcoding.service.StudentDormitoryService;

@Controller
@RequestMapping("/studentDormitory")
public class StudentDormitoryController {
	@Autowired
	private StudentDormitoryService studentdormitoryservice;

	// 宿舍添加成员
	@RequestMapping("StudentDormitoryAdd")
	@ResponseBody
	public void StudentDormitoryAdd(StudentDormitory studentDormitory, HttpServletRequest request) {
		String did = (String) request.getSession().getAttribute("did");
		Dormitory dormitory = new Dormitory();
		dormitory.setId(Integer.parseInt(did));
		studentDormitory.setDormitroy(dormitory);
		studentdormitoryservice.StudentDormitoryAdd(studentDormitory);

	}

	// 宿舍退出成员（不在学校住宿，不可逆）
	@RequestMapping("studentexit")
	@ResponseBody
	public void studentexit(StudentDormitory studentDormitory, HttpServletRequest request) {
		String did = (String) request.getSession().getAttribute("did");
		Dormitory dormitory = new Dormitory();
		dormitory.setId(Integer.parseInt(did));
		studentDormitory.setDormitroy(dormitory);
		studentdormitoryservice.studentexit(studentDormitory);

	}

	// 宿舍移除成员
	@RequestMapping("studentremove")
	@ResponseBody
	public void studentremove(StudentDormitory studentDormitory, HttpServletRequest request) {
		String did = (String) request.getSession().getAttribute("did");
		Dormitory dormitory = new Dormitory();
		dormitory.setId(Integer.parseInt(did));
		studentDormitory.setDormitroy(dormitory);
		studentdormitoryservice.studentremove(studentDormitory);

	}
	// 宿舍设置宿舍长
		@RequestMapping("DormitorylleaderSystem")
		@ResponseBody
		public void DormitorylleaderSystem(String id, HttpServletRequest request) {
			Integer did = (Integer) request.getSession().getAttribute("systemdid");
			studentdormitoryservice.DormitorylleaderSystem(id, did);

		}

		//通过学生id查看住宿情况
		@RequestMapping("getStudentId")
		@ResponseBody
		public StudentDormitory getBoarder(Integer id){

			return studentdormitoryservice.getBoarder(id);
			

		}

}
