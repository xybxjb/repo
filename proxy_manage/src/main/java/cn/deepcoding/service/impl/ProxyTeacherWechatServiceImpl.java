package cn.deepcoding.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.constants.SexConst;
import cn.deepcoding.dao.FeeDao;
import cn.deepcoding.dao.ProxyTeacherDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.model.Fee;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyTeacherWechatService;
@Service
@Transactional
public class ProxyTeacherWechatServiceImpl implements ProxyTeacherWechatService {
	
	@Autowired
	private ProxyTeacherDao proxyTeacherDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private FeeDao feeDao;
	
	// 获取招生老师的信息
	public Map<String,Object> getProxyTeacherByTel(String tel){
		 Map<String, Object> proxyTeacherByTel = proxyTeacherDao.getProxyTeacherByTel(tel);
		return proxyTeacherByTel;
	}
	// 根据招生老师的 id 获取学生信息
	public PageData getStudentByProxyTeacherId(Integer proxyTeacherId,Page page) throws Exception{
		List<Map<String, Object>> studentByProxyTeacherId = studentDao.getStudentByProxyTeacherId(proxyTeacherId,page);
		int studentCountByProxyTeacherId = studentDao.getStudentCountByProxyTeacherId(proxyTeacherId);
		Integer m = SexConst.male.getCode();
		Integer wm = SexConst.female.getCode();

		for (Map<String, Object> map : studentByProxyTeacherId) {
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				if(string.equals("sex")){
					if(map.get(string)==m ){
						map.put(string, "男");
					}else if(map.get(string)==wm){
						map.put(string, "女");
					}
				}
				if(string.equals("join_time")){
					Object object = map.get(string);
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					String format = date.format(object);
					map.put(string,format);
				}
			}
		}
		System.err.println(studentByProxyTeacherId);
		PageData pageData = new PageData();
		pageData.setTotal(studentCountByProxyTeacherId);
		pageData.setRows(studentByProxyTeacherId);
		return pageData;
	}
	// 根据学生 id 查询学生信息
	public Map<String,Object> getStudentById2(Integer studentid) throws Exception{
		Map<String, Object> studentById2 = studentDao.getStudentById2(studentid);
	
		//已交学费
		Integer feeSelect = feeDao.feeSelect(studentid);
		if(studentById2 == null){
			return null;
		}
		Set<String> keySet = studentById2.keySet();
		studentById2.put("amountTuition", feeSelect);
		Integer w = SexConst.male.getCode();
		Integer wm = SexConst.female.getCode();

		for (String string : keySet) {
			if(string.equals("sex")){
				if(studentById2.get(string)==w ){
					studentById2.put(string, "男");
				}else if(studentById2.get(string)==wm){
					studentById2.put(string, "女");
				}
			}
			if(string.equals("join_time")){
				 Object object = studentById2.get(string);
				 SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				 String format = date.format(object);
				 studentById2.put(string, format);
			}
		}
		return studentById2;
	}
	// 根据学生 id 查询缴费详情
	public PageData select(Integer studentId,Page page){
		 List<Fee> select = feeDao.getFeeByStudentId(studentId,page);
		 int feeCountByStudentId = feeDao.getFeeCountByStudentId(studentId);
		 PageData pageData = new PageData();
		 pageData.setRows(select);
		 pageData.setTotal(feeCountByStudentId);
		return pageData;
	}
}
