package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.ConversationIntroduction;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ConversationIntroductionService;

@Controller
@RequestMapping("/conversationIntroduction")
public class ConversationIntroductionController {

	@Autowired
	private ConversationIntroductionService conversationIntroductionService;
	
	@RequestMapping("/conversationIntroduction")
	@RequiresPermissions("conversationIntroduction:conversationIntroduction")
	public String page(){
		return "conversationIntroduction/conversationIntroduction";
	}
	
	@RequestMapping("/getByNameAndTime")
	@ResponseBody
	public List<ConversationIntroduction> getByNameAndTime(ConversationIntroduction conversationIntroduction){
		List<ConversationIntroduction> byNameAndTime = conversationIntroductionService.getByNameAndTime(conversationIntroduction);
		return byNameAndTime;
	}
	
	
}
