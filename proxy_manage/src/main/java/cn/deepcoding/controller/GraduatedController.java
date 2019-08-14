package cn.deepcoding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Code;
import cn.deepcoding.model.Graduated;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.AppService;
import cn.deepcoding.service.AppServiceNew;
import cn.deepcoding.service.GraduatedService;

/**
 * 就业喜报
 * 
 */
@Controller
@RequestMapping("/graduated")
public class GraduatedController {
	Map<String, String> map = new HashMap<>();
	@Autowired
	private GraduatedService graduatedService;

	@Autowired
	private AppService appService;

	/**
	 * 进入主页
	 */
	@RequestMapping("/index")
	public String index() {
		return "graduated/graduated";
	}

	/**
	 * 
	 * 查看所有就业信息
	 */

	@RequestMapping("/listAll")
	@ResponseBody
	public PageData listAll(Graduated graduated, Page page) {
		return graduatedService.listAll(graduated, page);
	}

	/**
	 * 
	 * 根据ID查看主要文章内容
	 */

	@RequestMapping("/getGraduatedByid")
	@ResponseBody
	public Graduated getGraduatedByid(Graduated graduated, HttpServletRequest request) {
		return graduatedService.getGraduatedByid(graduated);
	}

	/**
	 * 添加喜报
	 */
	@RequestMapping("/graduatedAdd")
	@ResponseBody
	public String addGraduated(Graduated graduated) {
		return graduatedService.addGraduated(graduated);
	}

	/**
	 * 删除喜报
	 */
	@RequestMapping("/graduatedDelete")
	@ResponseBody
	public String deleteGraduated(Integer id) {
		return graduatedService.deleteGraduated(id);
	}

	/**
	 * 修改内容
	 * 
	 */
	@RequestMapping("/graduatedUpdate")
	@ResponseBody
	public String updateGraduated(Graduated graduated) {
		return graduatedService.graduatedUpdate(graduated);

	}

	/**
	 * 进入添加页面
	 */
	@RequestMapping("/graduatedAddUrl")
	public String graduatedAdd(HttpServletRequest request) {
		request.getSession().setAttribute("id", null);
		return "graduated/graduatedAdd";
	}

	/**
	 * 进入修改页面
	 */
	@RequestMapping("/graduatedUpdateUrl")
	public String graduatedUpdateUrl(Integer id, ModelMap map) {
		// 将要修改的ID放到session域并且跳转到修改页面，打算将修改和添加整合成一个页面
		map.addAttribute("id", id);
		return "graduated/graduatedAdd";
	}

	/**
	 * 维护索引库
	 */
	@RequestMapping("/updateSolr")
	@ResponseBody
	public Code updateSolr() {
		Code code = appService.updateSolr();
		return code;
	}

}
