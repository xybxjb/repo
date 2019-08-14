package cn.deepcoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.EmergencyPerson;
import cn.deepcoding.service.EmergencyPersonService;

@Controller
@RequestMapping("emergencyPerson")
public class EmergencyPersonController {

	@Autowired
	private EmergencyPersonService emergencyPersonService;
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(EmergencyPerson emergencyPerson){
		emergencyPersonService.save(emergencyPerson);
	}
	
	
}
