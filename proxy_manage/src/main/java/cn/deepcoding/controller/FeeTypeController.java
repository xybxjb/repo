package cn.deepcoding.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.constants.ResponseCode;
import cn.deepcoding.model.FeeType;
import cn.deepcoding.service.FeeTypeService;

@Controller
@RequestMapping("/feeType")
public class FeeTypeController {

	@Autowired
	private FeeTypeService feeTypeService;
	
	
	@RequestMapping("/index")
	public String index() {
		return "data_dictionary/feeType";
	}
	@RequestMapping("/save")
	@ResponseBody
	public void save(FeeType feeType) {
		feeTypeService.save(feeType);
	}
	@RequestMapping("/find")
	@ResponseBody
	public List<FeeType> find(){
		return feeTypeService.find();
	}
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseCode delete(Integer id) {
		
		return feeTypeService.delete(id);
	}
}
