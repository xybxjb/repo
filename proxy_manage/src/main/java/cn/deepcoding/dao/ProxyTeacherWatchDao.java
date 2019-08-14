package cn.deepcoding.dao;

import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ProxyTeacherWatch;

@Repository
public interface ProxyTeacherWatchDao {
	//通过oppenid查询用户信息
	ProxyTeacherWatch FindProxyTeacherWatchByOppenid(String openid);
	//向内添加oppenid等信息
	void  ProxyTeacherWatchAdd(ProxyTeacherWatch proxyteachera);
	//添加之前先删除原有的oppenid信息
	void DeleteProxyTeacherWatchByoppenId(String oppenid);
}
