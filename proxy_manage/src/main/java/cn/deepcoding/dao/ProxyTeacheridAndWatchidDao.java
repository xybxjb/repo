package cn.deepcoding.dao;

import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ProxyTeacherIdAndWatchId;

@Repository
public interface ProxyTeacheridAndWatchidDao {
	// 通过微信信息的id获取招生老师的ID
	ProxyTeacherIdAndWatchId getByWatchid(Integer id);

	// 通过招生老师Id删除中间表信息
	void DeleteproxyteacherIdandwatchIdByproteacherId(Integer proteacherid);

	// 将招生老师微信信息与招生老师信息关联
	void proxyteacherIdandwatchIdAdd(ProxyTeacherIdAndWatchId proxyTeacherIdAndWatchId);
}
