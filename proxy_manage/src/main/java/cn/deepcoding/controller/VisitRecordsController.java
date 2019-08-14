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
import cn.deepcoding.model.VisitFee;
import cn.deepcoding.model.VisitRecords;
import cn.deepcoding.service.VisitRecordsService;
import cn.deepcoding.util.ImagUtils;

@Controller
@RequestMapping("/visitRecords")
public class VisitRecordsController {

	@Autowired 
	private VisitRecordsService visitRecordsService;
	@Autowired
	private HttpServletRequest request;
	@RequiresPermissions("visitRecords:index")
	@RequestMapping("/visitRecords")
	public String index(){
		return "visitRecords/visit_records";
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<VisitRecords> getAll(){
		return visitRecordsService.getAll();
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public VisitRecords getById(Integer id){
		return visitRecordsService.getById(id);
	}

	@RequestMapping("/save")
	@ResponseBody
	private void save(VisitRecords visitRecords,Double[] actualAmount,Integer[] trans,Double [] ticketAmount,String reimbVoucher,String reimbMileage){
		visitRecordsService.save(visitRecords, actualAmount, trans, ticketAmount, reimbVoucher,reimbMileage);;
	}	
	
	@RequestMapping("/update")
	@ResponseBody
	private void update(VisitRecords visitRecords,Double[] actualAmount,Integer[] trans,Double [] ticketAmount,String reimbVoucher,String reimbMileage){
		visitRecordsService.update(visitRecords, actualAmount, trans, ticketAmount, reimbVoucher,reimbMileage);;
	}	
	
	
	@RequestMapping("/savePic")
	@ResponseBody
	public String savePic(@RequestParam(value="file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String path = request.getSession().getServletContext().getRealPath("");
				path =path.replaceAll("proxy_manage","");
				path=path.substring(0, path.length()-1);
				File newFile = new File(path+"images/vistRecords/");
				if(!newFile.exists()) newFile.mkdirs();
				String name =file.getOriginalFilename();
				String wei = name.substring(name.lastIndexOf("."),name.length());
				String uuid =	UUID.randomUUID().toString();
				String filePath = path+"images/vistRecords/" + uuid+wei;
				// 转存文件
				file.transferTo(new File(filePath));
//				// 图片路经
//				String imgUrl = ImagUtils.imgUrl();
				return "/vistRecords/"+uuid+wei;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "err";
	}	
	
	@RequestMapping("/getVisitRecords")
	@ResponseBody
	public List<VisitRecords> getVisitRecords(@RequestParam(value="name",required=false) String name,@RequestParam(value="purpose",required=false)String purpose,@RequestParam(value="beginDateTime",required=false)String beginDateTime,@RequestParam(value="endDateTime",required=false)String endDateTime,@RequestParam(value="startFromAddress1",required=false)String startFromAddress1) {
		
		return visitRecordsService.getVisitRecords(name, purpose, beginDateTime, endDateTime, startFromAddress1);
	
	}

		
	
}
