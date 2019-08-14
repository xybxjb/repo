package cn.deepcoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Source;
import cn.deepcoding.service.SourceService;

//招生老师数据来源
@Controller
@RequestMapping("/source")
public class SourceController {
	@Autowired
	private SourceService sourceService;
	
	@RequestMapping("/findSource")
	@ResponseBody
  public List<Source> findSource(){
	  List<Source> Source = sourceService.findSource();
	return Source;
	  
  }
	

}
