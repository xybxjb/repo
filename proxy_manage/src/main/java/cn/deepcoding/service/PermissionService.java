package cn.deepcoding.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Permission;

public interface PermissionService {

	List<Permission> selectPermission(Permission permission);
	// 鏌ヨ鎵�鏈�
	List<Permission> getAll(Permission permissions);
	// 鏍规嵁 id鏌ヨ
	Permission getById(Integer id);
	// 淇敼
	void update(Permission permissions);
	// 娣诲姞
	void addPermission(Permission permissions);
	// 鍒犻櫎
	void del(Integer id);
	//涓嬫媺妗嗘樉绀虹埗绾d
	
	List<Permission> findIdByText(Permission permissions);
	
	List<Permission> getPid();
	
	List<Permission> getByCid(Permission permission);
	//通过角色id查找权限表对象  2019-8-6 修改权限表
	List<Permission> getRolesIdToPermission(Integer id);
}
