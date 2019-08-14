package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.CheckDormitory;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.CheckDormitoryService;

@Controller
@RequestMapping("/CheckDormitory")
public class CheckDormitoryController {
	@Autowired
	private CheckDormitoryService checkdormitoryservice;

	// 宿舍查寝首页
	@RequiresPermissions("CheckDormitory:indexfind")
	@RequestMapping("indexfind")
	public String CheckDormitoryIndex() {
		return "dormitory/checkdormitory";

	}

	// 所有宿舍查寝信息展示
	@RequestMapping("listAll")
	@ResponseBody
	public PageData FindCheckDormitoryAll(CheckDormitory checkdormitory,Page page) {

		 return checkdormitoryservice.FindCheckDormitoryAll(checkdormitory,page);
	}

	// 添加查寝记录
	@RequestMapping("CheckDormitoryAdd")
	@ResponseBody
	public String CheckDormitoryAdd(CheckDormitory checkdormitory) {

		return checkdormitoryservice.CheckDormitoryAdd(checkdormitory);

	}

	// 删除查寝记录
	@RequestMapping("CheckDormitoryDelete")
	@ResponseBody
	public void CheckDormitoryDelete(CheckDormitory checkdormitory) {

		checkdormitoryservice.CheckDormitoryDelete(checkdormitory);
	}

	// 删除查寝记录
	@RequestMapping("CheckDormitoryGetById")
	@ResponseBody
	public CheckDormitory CheckDormitoryGetById(CheckDormitory checkdormitory) {

		return checkdormitoryservice.CheckDormitoryGetById(checkdormitory);
	}

	// 修改查寝记录
	@RequestMapping("CheckDormitoryUpdate")
	@ResponseBody
	public String CheckDormitoryUpdate(CheckDormitory checkdormitory) {

		return checkdormitoryservice.CheckDormitoryUpdate(checkdormitory);
	}

}
