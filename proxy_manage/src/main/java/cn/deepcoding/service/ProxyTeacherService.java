package cn.deepcoding.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherPoint;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

@Repository
public interface ProxyTeacherService {
	public void add(ProxyTeacher proxyTeacher,MultipartFile file);
	
	public PageData findByPage(ProxyTeacher proxyTeacher,Page page);
	
	public ProxyTeacher get(Integer id);
	
	public void delete(Integer id);
	
	
	public ProxyTeacher getParentProxyTeacherId(Integer id);
	public List<ProxyTeacher> getRankProxteacher(Integer rankId);
	public List<ProxyTeacher> getAll();
	
	public List<ProxyTeacher> getById(Integer id);

	public void update(ProxyTeacher proxyTeacher, MultipartFile file);
	public void deljia(ProxyTeacher proxyTeacher);

	public List<ProxyTeacherPoint> getpoint(Integer id);


	public String addpoint(ProxyTeacherPoint proxyteacherpoint, HttpServletRequest request);

	public ProxyTeacherPoint selectpointbyid(Integer id, int proxyTeacherId);

	public String updatepoint(ProxyTeacherPoint proxyteacherpoint);

	public void deletepoint(Integer id, int proxyTeacherId);





	
	
}
