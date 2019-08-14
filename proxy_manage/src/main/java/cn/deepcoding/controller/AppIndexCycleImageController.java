package cn.deepcoding.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.model.AppIndexCycleImage;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.AppIndexCycleImageService;


@Controller
@RequestMapping("/appIndexCycleImage")
public class AppIndexCycleImageController {
	
	@Autowired
	private AppIndexCycleImageService appIndexCycleImageService;
	
	/**
	 * 点击【APP首页轮播图管理】跳转页面
	 * @return
	 */
	@RequestMapping("/index")
	public String appIndexCycleImage(){
		return "appIndexCycleImage/cycleImage";
	}
	
	/**
	 * 查询所有可用于轮播图的图片
	 * @param appIndexCycleImage
	 * @param page
	 * @return
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public PageData getAll(AppIndexCycleImage appIndexCycleImage,Page page){
		return  appIndexCycleImageService.getAll(appIndexCycleImage,page);
		
	}
	
	/**
	 * 【是否使用当前图片】中所有的（是）改为（否）,即将数据库中的字段：image_use的值由1改为0
	 */
	@RequestMapping("/clearAllUse")
	@ResponseBody
	public void clearAllUse() {
		appIndexCycleImageService.clearAllUse();
	}
	
	/**
	 * 保存上传的轮播图图片
	 * @param appIndexCycleImage
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveCycleImage")
	@ResponseBody
	public String saveCycleImage(AppIndexCycleImage appIndexCycleImage,@RequestParam(value="file") MultipartFile file,HttpServletRequest request) {
		if (!file.isEmpty()) {
			try {
				// 文件保存路径			
				String path = request.getServletContext().getRealPath("/");
				System.err.println("1path"+path);
				path =path.replaceAll("proxy_manage","");
				System.err.println("2parh"+path);
				path=path.substring(0, path.length()-1);
				System.out.println("3path"+path);
				File newFile = new File(path+"images/appIndexCycleImage/");
				if(!newFile.exists()) newFile.mkdirs();
				String name =file.getOriginalFilename();
				String wei = name.substring(name.lastIndexOf("."),name.length());
				String uuid =	UUID.randomUUID().toString();
				String filePath = path+"images/appIndexCycleImage/" + uuid+wei;
				// 转存文件
				file.transferTo(new File(filePath));
				return "/appIndexCycleImage/"+uuid+wei;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
	
	/**
	 * 添加完整的轮播图图片信息
	 * @param studentConversation
	 */
	@RequestMapping("/addAppIndexCycleImage")
	@ResponseBody
	public void AddAppIndexCycleImage(AppIndexCycleImage appIndexCycleImage){
		System.err.println("要保存的图片信息："+appIndexCycleImage);
		appIndexCycleImageService.addappIndexCycleImage(appIndexCycleImage);
	}
	
	/**
	 * 根据 id 查询轮播图图片信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/getCycleImageById")
	@ResponseBody
	public AppIndexCycleImage getCycleImageById(Integer id){
		return appIndexCycleImageService.getCycleImageById(id);
	}
	
	/**
	 * 修改轮播图图片信息
	 * @param appIndexCycleImage
	 */
	@RequestMapping("/update")
	@ResponseBody
	public void updateAppIndexCycleImage(AppIndexCycleImage appIndexCycleImage){
		if(appIndexCycleImage.getImageSrc().contains("/images")){
			appIndexCycleImage.setImageSrc(appIndexCycleImage.getImageSrc().replaceAll("/images",""));
		}
		System.err.println("修改后的图片信息"+appIndexCycleImage);
		appIndexCycleImageService.updateAppIndexCycleImage(appIndexCycleImage);
	}
	
	/**
	 * 根据 id 删除轮播图图片信息
	 * @param id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void deleteAppIndexCycleImage(Integer id){
		appIndexCycleImageService.deleteAppIndexCycleImage(id);
	}
	
}
