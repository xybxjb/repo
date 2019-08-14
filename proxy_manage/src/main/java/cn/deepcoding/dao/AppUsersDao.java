package cn.deepcoding.dao;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.AppUsers;

public interface AppUsersDao {
	
	// 根据账号 查询信息
	AppUsers getByName(@Param("appName") String appName);
	// 修改密码
	void updatePassword(AppUsers appUsers);
	// 根据手机号信息
	AppUsers getByTel(@Param("appTel") String appTel);
	// 添加信息
	void addAppUser(AppUsers appUsers);
	//根据招生老师ID查询 APP内的信息
	AppUsers getAppuserById(Integer proxyTeacherid);
}
