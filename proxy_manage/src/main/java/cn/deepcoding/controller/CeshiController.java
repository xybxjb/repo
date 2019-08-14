package cn.deepcoding.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Ceshi")
public class CeshiController {
		@RequestMapping("index")
		public String CheckDormitoryIndex() {
			return "Ceshi";

		}
		@RequestMapping("ceshi")
		@ResponseBody
		public String ceshi(String  Content) {
			
			System.err.println("获得的"+Content);
			return "Ceshi";

		}
		
	


}
