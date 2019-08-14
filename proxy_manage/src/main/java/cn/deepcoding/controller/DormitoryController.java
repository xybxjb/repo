package cn.deepcoding.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Dormitory;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentDormitoryQuerry;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.DormitoryService;
/**
 * @RequiresPermissions("***")
 * 权限名称
 * */
@Controller 
@RequestMapping("/Dormitory")
public class DormitoryController {

	@Autowired
	private DormitoryService dormitoryservice;

	// 进入首页

	@RequestMapping("indexfind")
	@RequiresPermissions("Dormitory:indexfind")
	public String indexfind() {
		return "dormitory/dormitory";

	}

	// 展示所有，并且通过条件查询展示
	@RequestMapping("listAll")
	@ResponseBody
	public PageData listAll(Dormitory dormitory, String StudentName, Page page) {
		return dormitoryservice.FindDormitoryAll(dormitory, StudentName, page);

	}
	
	// 展示所有，并且通过条件查询展示
		@RequestMapping("listdormitorynumber")
		@ResponseBody
		public List<Dormitory> listdormitorynumber() {
			 List<Dormitory> list = dormitoryservice.listAllnumber();
			 
			 return list;

		}
	
	
	

	// 添加宿舍
	@RequestMapping("DormitoryAdd")
	@ResponseBody
	public String DormitoryAdd(Dormitory dormitory) {

		return dormitoryservice.DormitoryAdd(dormitory);

	}

	// 添加宿舍成员，过滤未住宿的
	@RequestMapping("getstudnt")
	@ResponseBody
	public List<Student> getstudnt(HttpServletRequest request) {
		return dormitoryservice.getstudnt(request);

	}

	// 添加宿舍成员，把宿舍ID存入
	@RequestMapping("setdormitoryID")
	@ResponseBody
	public void setdormitoryID(String id, HttpServletRequest request) {
		request.getSession().setAttribute("did", id);

	}

	// 通过ID查看宿舍成员信息，这个只是为了返回宿舍内的信息方便
	@RequestMapping("GetStudentDormitoryById")
	@ResponseBody
	public List<StudentDormitoryQuerry> GetStudentDormitoryById(Dormitory dormitory, HttpServletRequest request) {
		Integer id = dormitory.getId();
		request.getSession().setAttribute("systemdid", id);
		return dormitoryservice.GetStudentDormitoryById(dormitory);

	}

	// 通过ID获取宿舍信息
	@RequestMapping("GetById")
	@ResponseBody
	public Dormitory GetById(Dormitory dormitory) {

		return dormitoryservice.GetById(dormitory);

	}
	// 修改宿舍信息

	@RequestMapping("DormitoryUpdate")
	@ResponseBody
	public String DormitoryUpdate(Dormitory dormitory) {
		return dormitoryservice.DormitoryUpdate(dormitory);

	}

	// 修改宿舍信息

	@RequestMapping("deleteDormitory")
	@ResponseBody
	public void deleteDormitory(Dormitory dormitory) {
		System.err.println("要删除的宿舍ID：" + dormitory.getId());

		dormitoryservice.deleteDormitory(dormitory.getId());

	}
	
}
