package cn.deepcoding.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.dao.UnauditedDao;
import cn.deepcoding.model.Unaudited;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.UnauditedService;

@Service
@Transactional
public class UnauditedServiceImpl implements UnauditedService {
	@Autowired
	private UnauditedDao unauditedDao;
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public PageData list(Integer id,String studentName,String className,String startTime,String endTime,Page page) {
		
		List<Unaudited> list = unauditedDao.list(id, studentName,className, startTime, endTime,page);
		Integer count = unauditedDao.getAllCount();
		PageData pageData = new PageData();
		pageData.setTotal(count);
		pageData.setRows(list);
		return pageData;
	}
	
	@Override
	public Unaudited getById(Integer id){
		return unauditedDao.getById(id);
	}
	@Override
	public Unaudited get(Integer id){
		return unauditedDao.get(id);
	}
	@Override
	public void update(Unaudited unaudited,MultipartFile file){
	
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
				unaudited.setTicketVoucher("/images/unaudited/"+uuid+wei); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		unauditedDao.update(unaudited);
	}
}
