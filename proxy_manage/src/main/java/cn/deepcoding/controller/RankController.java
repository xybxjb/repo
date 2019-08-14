package cn.deepcoding.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.Rank;
import cn.deepcoding.service.RankService;


@Controller
@RequestMapping("/rank")
public class RankController {
	@Autowired
	private RankService RankService;
	@RequiresPermissions("rank:index")
	@RequestMapping("/index")
	public String index(){
		return "rank/rank";
	}
	@RequestMapping("/list")
	@ResponseBody
	public Map list(Rank ranks, Integer page, Integer rows) {
		int start = (page - 1) * rows;
		return RankService.list(ranks, start, rows);
	}
	@RequestMapping("/add")
	@ResponseBody
	public void add(Rank rank){
		RankService.add(rank);;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(Integer id){
		RankService.delete(id);
		
	}
	@RequestMapping("get")
	@ResponseBody
	public Rank get(Integer id){
		return RankService.get(id);
	}
	@RequestMapping("update")
	@ResponseBody
	public void update(Rank rank){
		RankService.update(rank);
	}
	@RequestMapping("getAll")
	@ResponseBody
	public List<Rank> getAll(){
		return RankService.getAll();
		
		
	}
	
	

}
