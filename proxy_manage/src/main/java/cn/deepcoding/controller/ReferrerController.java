package cn.deepcoding.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Referrer;
import cn.deepcoding.model.StudentProxyTeacher;
import cn.deepcoding.service.ReferrerService;
import cn.deepcoding.service.StudentProxyTeacherService;

@Controller
@RequestMapping("/referrer")
public class ReferrerController {
	@Autowired
	private ReferrerService referrerService;
	
	@RequiresPermissions("referrer:index")
	@RequestMapping("/index")
	public String index(){
		return "proxyTeacher/referrer";
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public Map<String,Object> getAll(Referrer referrer,Integer page,Integer rows){
		Map<String,Object> list = referrerService.getAll(referrer,page,rows);
		return list;
	}
}
