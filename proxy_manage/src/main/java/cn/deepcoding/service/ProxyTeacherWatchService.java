package cn.deepcoding.service;

import java.util.Map;

/**
 * 
 * 张旭，主要调用外网接口*/
import javax.servlet.http.HttpServletRequest;

import cn.deepcoding.model.ProxyTeacherWatch;
import cn.deepcoding.model.WatchXmlMessageEntity;

public interface ProxyTeacherWatchService {
	// 判断改oppenid是否在数据库存在
	boolean watchLogin(String entity,HttpServletRequest request);

	// 发送短信
	Map<String, String> getmessige(String tel, HttpServletRequest request);

	// 验证吗接入验证
	Map<String, String> getcode(HttpServletRequest request, String code);

}
