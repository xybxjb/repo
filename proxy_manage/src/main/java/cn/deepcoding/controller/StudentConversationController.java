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
import cn.deepcoding.model.StudentConversation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.StudentConversationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("studentConversation")
//@Api(value = "谈话记录",description="谈话记录模块的接口")
public class StudentConversationController {
	
	@Autowired
	private StudentConversationService studentConversationService;
	// 跳转页面
	
	@RequiresPermissions("studentConversation:studentConversation")
	@RequestMapping("/studentConversation")
	public String studentConversation(){
		return "conversation/studentConversation";
	}
	
	// 查询所有
	@RequestMapping("/getAll")
	@ResponseBody
//	@ApiOperation(value = "谈话记录列表",httpMethod = "POST",notes = "获取所有的谈话记录信息")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "query",name = "page", value = "起始索引",required = true,dataType = "Integer"),
//			@ApiImplicitParam(paramType = "query",name = "rows", value = "每页个数",required = true,dataType = "Integer")  
//
//	})
	public PageData getAll(StudentConversation studentConversation,Page page){
		return  studentConversationService.getAll(studentConversation,page);
		
	}
	
	@RequestMapping("/savePice")
	@ResponseBody
	public String savePic(StudentConversation studentConversation,@RequestParam(value="file") MultipartFile file,HttpServletRequest request) {
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
//				String imgUrl = ImagUtils.imgUrl();
				return "/conversation/"+uuid+wei;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
	// 添加谈话记录
	@RequiresPermissions("studentConversation:AddStudentConversation")
	@RequestMapping("/AddStudentConversation")
	@ResponseBody
	public void AddStudentConversation(StudentConversation studentConversation){
	
		studentConversationService.AddStudentConversation(studentConversation);
	}
	// 根据 id 查询
	@RequestMapping("/getById")
	@ResponseBody
//	@ApiOperation(value = "根据 id 查询谈话记录",httpMethod = "POST",notes = "根据id获取谈话记录信息")
//	@ApiImplicitParam(paramType = "query", name = "id", value = "学生谈话记录ID", required = true, dataType = "Integer")  // 有参数时用
	public StudentConversation getById(Integer id){
		return studentConversationService.getById(id);
		
	}
	// 修改 信息
	@RequestMapping("/update")
	@ResponseBody
	public void updateStudentConversation(StudentConversation studentConversation){
		System.err.println(studentConversation);
		studentConversationService.updateStudentConversation(studentConversation);
	}
	// 删除谈话记录
	@RequestMapping("/del")
	@ResponseBody
	@RequiresPermissions("studentConversation:del")
	public void deleteStudentConversation(Integer id){
		studentConversationService.deleteStudentConversation(id);
	}
	// 获取老师 和咨询老师的姓名
	@RequestMapping("/getAllName")
	@ResponseBody
	public List<StudentConversation> getAllName(){
		List<StudentConversation> allName = studentConversationService.getAllName();
		/*System.err.println(allName);*/
		return allName;
	}
}