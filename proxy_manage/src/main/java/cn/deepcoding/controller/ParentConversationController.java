package cn.deepcoding.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.model.ParentConversation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ParentConversationService;

@Controller
@RequestMapping("parentConversation")
public class ParentConversationController {
	
	@Autowired
	private ParentConversationService parentConversationService;
	
	// 跳转页面
	@RequiresPermissions("parentConversation:parentConversation")
	@RequestMapping("/parentConversation")
	public String parentConversation(){
		return "conversation/parentConversation";
	}
	
	// 查询所有
	@RequestMapping("/getAll")
	@ResponseBody
	public PageData getAll(ParentConversation parentConversation,Page page){
		return parentConversationService.getAll(parentConversation,page);
		
	}
	
	@RequestMapping("/savePice")
	@ResponseBody
	public String savePic(ParentConversation parentConversation,@RequestParam(value="file") MultipartFile file,HttpServletRequest request) {
		if (!file.isEmpty()) {
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
				String name =file.getOriginalFilename();
				String wei = name.substring(name.lastIndexOf("."),name.length());
				String uuid =	UUID.randomUUID().toString();
				String filePath = path+"images/conversation/" + uuid+wei;
				// 转存文件
				file.transferTo(new File(filePath));
//				// ImgUrl 路径
//				String imgUrl = ImagUtils.imgUrl();
				return "/conversation/"+uuid+wei;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
	// 添加谈话记录
	@RequestMapping("/AddParentConversation")
	@ResponseBody
	public void AddParentConversation(ParentConversation parentConversation){
		System.err.println(parentConversation);
		System.err.println();
		parentConversationService.AddParentConversation(parentConversation);
	}
	// 根据 id 查询
	@RequestMapping("/getById")
	@ResponseBody
	public ParentConversation getById(Integer id){
		return parentConversationService.getById(id);
		
	}
	// 修改 信息
	@RequestMapping("/update")
	@ResponseBody
	public void updateParentConversation(ParentConversation parentConversation){
		System.err.println(parentConversation);
		parentConversationService.updateParentConversation(parentConversation);
	}
	// 删除谈话记录
	@RequestMapping("/del")
	@ResponseBody
	public void deleteParentConversation(Integer id){
		parentConversationService.deleteParentConversation(id);
	}
	// 获取老师 和咨询老师的姓名
	@RequestMapping("/getAllName")
	@ResponseBody
	public List<ParentConversation> getAllName(){
		List<ParentConversation> allName = parentConversationService.getAllName();
		/*System.err.println(allName);*/
		return allName;
	}
	
}
