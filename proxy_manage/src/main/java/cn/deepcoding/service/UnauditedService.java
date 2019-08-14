package cn.deepcoding.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.model.Unaudited;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;


public interface UnauditedService {
	PageData  list(Integer id,String studentName,String className,String startTime,String endTime, Page page);
	
	Unaudited getById(Integer id);

	Unaudited get(Integer id);
	
	public	void update(Unaudited unaudited,MultipartFile file);
}
