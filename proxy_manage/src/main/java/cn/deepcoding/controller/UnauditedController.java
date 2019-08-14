package cn.deepcoding.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.deepcoding.model.Unaudited;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.UnauditedService;
import cn.deepcoding.util.ImagUtils;

/**
 * 
 * @author 陈大发
 *	学生报销未审核
 */
@Controller
@RequestMapping("/unaudited")
public class UnauditedController {
	@Autowired
	private UnauditedService unauditedService;
	@Autowired
	private HttpServletRequest request;
	
		//返回学生审核list数据
		@RequestMapping("/list")
		@ResponseBody
		public PageData list(@RequestParam("id")Integer id,@RequestParam(value="studentName",required=false)String studentName,@RequestParam(value="className",required=false)String className,@RequestParam(value="startTime",required=false)String startTime,@RequestParam(value="endTime",required=false)String endTime,Page page){
			
			return unauditedService.list(id, studentName,className, startTime, endTime,page);
		}
		
		//返回学生未审核的单个学生的记录
		@RequestMapping("/getById")
		@ResponseBody
		public Unaudited getById(Integer studentId){
			
			return unauditedService.getById(studentId);
		}
		
		//返回学生已报销和作废的单个学生的记录
		@RequestMapping("/get")
		@ResponseBody
		public Unaudited get(Integer studentId){
			return unauditedService.get(studentId);
		}
		
		
		//图片回显
		@RequestMapping("/savePic")
		@ResponseBody
		public String savePic(@RequestParam(value="file") MultipartFile file) {
			if (!file.isEmpty()) {
				try {
					// 文件保存路径
					String path = request.getSession().getServletContext().getRealPath("");
					path =path.replaceAll("proxy_manage","");
					path=path.substring(0, path.length()-1);
					File newFile = new File(path+"images/unaudited/");
					if(!newFile.exists()) newFile.mkdirs();
					String name =file.getOriginalFilename();
					String wei = name.substring(name.lastIndexOf("."),name.length());
					String uuid =	UUID.randomUUID().toString();
					String filePath = path+"images/unaudited/" + uuid+wei;
					// 转存文件
					file.transferTo(new File(filePath));
					// 图片路经
					String imgUrl = ImagUtils.imgUrl();
					return imgUrl+"/unaudited/"+uuid+wei;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return "err";
		}
		
		//修改资料
		@RequestMapping("/update")
		@ResponseBody
		public void update(Unaudited unaudited,@RequestParam(value="file",required=false) MultipartFile file){
			
			unauditedService.update(unaudited,file);
			
		}
}
