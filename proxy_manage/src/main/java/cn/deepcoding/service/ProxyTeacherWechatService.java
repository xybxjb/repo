package cn.deepcoding.service;

import java.util.Map;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
public interface ProxyTeacherWechatService {
	
	// 根据 手机号获取招生老师信息
	Map<String,Object> getProxyTeacherByTel(String tel);
	// 根据招生老师的 id 获取学生信息
	PageData getStudentByProxyTeacherId(Integer proxyTeacherId,Page page) throws Exception;
	// 根据学生 id 查询学生信息
	Map<String,Object> getStudentById2(Integer studentid) throws Exception;
	// 根据学生 id 查询缴费详情
	public PageData select(Integer studentId,Page page);
}
