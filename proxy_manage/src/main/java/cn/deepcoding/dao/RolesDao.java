package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Roles;

public interface RolesDao {
	
	// 查询所有角色
	List<Roles> getAll(Roles roles);
	// 根据id 查询角色
	Roles getById(Integer id);
	// 修改角色
	void updateRole(Roles roles);
	// 添加角色
	void addRole(Roles roles);
	// 删除角色
	void deleteRole(Integer id);
	
	Roles findById(Integer id);
	
	//通过角色ID删除所有权限  2019-08-6 
	void deleteAll(Integer id);
	//根据用户id查找角色
	List<Roles> getRoleByUserId(Integer userId);
	//添加关系
	void addByRoleId(@Param("rolesId") Integer rolesId,@Param("permissionId") Integer permissionId);
//	根据角色Id删除用户角色关系
	void deleteUserRoleByRoleId(Integer id);
//	根据角色Id权限id删除角色权限关系
	void deleteByRoleIdPermissionId(@Param("rolesId") Integer rolesId,@Param("permissionId") Integer permissionId);
	//添加角色用户关系
	void addUserRoles(@Param("userId") Integer userId,@Param("rolesId") Integer rolesId);
	//根据用户id删除角色用户关系
	void deleteByUserId(Integer id);
//	根据角色Id用户id删除角色用户关系
	void deleteByUserIdRoleId(@Param("userId") Integer userId,@Param("rolesId") Integer rolesId);

}
