package cn.deepcoding.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.model.Sanitation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.SanitationService;

@Controller
@RequestMapping("sanitation")
public class SanitationContorller {
	
	@Autowired
	private SanitationService sanitationService;
	
	// 去页面
	@RequiresPermissions("sanitation:sanitation")
	@RequestMapping("/sanitation")
	public String saintation(){
		return "dormitory/sanitation";
	}
	// 查询所有
	@RequestMapping("getAll")
	@ResponseBody
	public PageData getAll(Sanitation sanitation,Page page){
		return sanitationService.getAll(sanitation, page);
	}
	// 添加
	@RequestMapping("/addSanitation")
	@ResponseBody
	public void addSanitation(Sanitation sanitation){
//		System.err.println(sanitation);

		sanitationService.addSanitation(sanitation);
	}
	@RequestMapping("/savePice")
	@ResponseBody
	public String savePic(@RequestParam("pic") MultipartFile pic,HttpServletRequest request) {
		if (!pic.isEmpty()) {
			try {
				// 文件保存路径			
				String path = request.getServletContext().getRealPath("/");
				System.err.println("1path"+path);
				path =path.replaceAll("proxy_manage","");
				System.err.println("2parh"+path);
				path=path.substring(0, path.length()-1);
				System.out.println("3path"+path);
				File newFile = new File(path+"images/conversation/");
				if(!newFile.exists()) newFile.mkdirs();
				String name =pic.getOriginalFilename();
				String wei = name.substring(name.lastIndexOf("."),name.length());
				String uuid =	UUID.randomUUID().toString();
				String filePath = path+"images/conversation/" + uuid+wei;
				// 转存文件
				pic.transferTo(new File(filePath));
//				// 图片路经
//				String imgUrl = ImagUtils.imgUrl();
				return "/conversation/"+uuid+wei;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
	
	// 根据 id查询
	@RequestMapping("/getById")
	@ResponseBody
	public Sanitation getById(Integer id){
		return sanitationService.getById(id);
	}
	// 根据 宿舍号 月份 查询
	@RequestMapping("/getCount")
	@ResponseBody
	public List<Map<String, Integer>> getCount(Sanitation sanitation){
		Map<String, Integer> map = sanitationService.getCount(sanitation);
		List<Map<String, Integer>> lm = new ArrayList<Map<String, Integer>>();
		lm.add(map);
		return lm;
	}
	// 修改
	@RequestMapping("/update")
	@ResponseBody
	public void update(Sanitation sanitation){
		sanitationService.updateSanitation(sanitation);
	}
	// 删除
	@RequestMapping("/del")
	@ResponseBody
	public void deleteSanitation(Integer id){
		sanitationService.deleteSanitation(id);
	}
	// echarts
	@RequestMapping("/toEcharts")
	public String toEcharts(){
		return "dormitory/dormitoryEcharts";
	}
}
