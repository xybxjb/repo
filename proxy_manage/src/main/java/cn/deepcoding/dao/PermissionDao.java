package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Permission;

public interface PermissionDao {
	 //鏌ユ壘鎵�鏈夌殑鏉冮檺
	List<Permission> selectPermission(@Param("permission")Permission permission);
	// 鏌ヨ鎵�鏈�
	List<Permission> getAll(Permission permission);
	// 鏍规嵁 id鏌ヨ
	Permission getById(Integer id);
	// 淇敼
	void update(@Param("permission")Permission permission);
	// 娣诲姞
	void addPermission(@Param("permission")Permission permission);
	// 鍒犻櫎
	void del(Integer id);
	//涓嬫媺妗嗘樉绀虹埗绾d
	List<Permission> findPid(Permission permissions);
	
	List<Permission> findIdByText(@Param("permission")Permission permissions);
	
	List<Permission> getPid();
	
	List<Permission> getByCid(@Param("permission")Permission permission);
	
	void addPermission2(@Param("permission")Permission permissions);
	
	//通过角色id查找权限表对象  2019-8-6 修改权限表
	List<Permission> getRolesIdToPermission(@Param("id")Integer id);
//	删除关联表数据
	void deleteByPermissionId(Integer id);
	
	List<Permission> getUserIdAndRolesIdToPermission(@Param("uid")Integer uid,@Param("rid")Integer rid);
}
