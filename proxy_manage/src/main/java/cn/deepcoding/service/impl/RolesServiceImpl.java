package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.deepcoding.dao.PermissionDao;
import cn.deepcoding.dao.RolesDao;
import cn.deepcoding.model.Permission;
import cn.deepcoding.model.Roles;
import cn.deepcoding.service.RolesService;
@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesDao rolesDao;
	@Autowired
	private PermissionDao permissionDao;

//	@Autowired
//	private RolesPermissionsDao rolesPermissionsDao;
	
	// 查询所有角色
	@Override
	public List<Roles> getAll(Roles roles) {
		
		return rolesDao.getAll(roles);
	}
	// 查询所有权限
	@Override
	public List<Permission> getAllPermission(Permission permissions) {
		List<Permission> all = permissionDao.getAll(permissions);
		return all;
	}
	
	// 根据 id 查询
	@Override
	public Roles getById(Integer id) {
		
		return rolesDao.getById(id);
	}
	// 修改角色
	@Override
	public void updateRole(Roles roles) {
		rolesDao.updateRole(roles);

	}
	// 添加角色
	@Override
	public void addRole(Roles roles) {
		rolesDao.addRole(roles);

	}
	// 添加关联权限
	@Override
	public void addRole3(Roles roles) {
		//根据权限id查找权限表，判断state的状态
		List<Permission> permissions=roles.getPermissions();
		for (Permission permission : permissions) {
			Permission find = permissionDao.getById(permission.getCid());
			if(find.getState()==null||find.getState().equals("")){
				//不存在就添加(根据 角色 id 添加权限)
				//INSERT INTO roles_permissions(role_id,permission_id) VALUES(#{rid},#{cid});
				rolesDao.addByRoleId(roles.getId(),permission.getCid());
			}else{
				System.out.println("状态为：closed");
			}
		}
	}
	// 删除角色
	@Override
	public void deleteRole(Integer id) {
		rolesDao.deleteUserRoleByRoleId(id);
		rolesDao.deleteAll(id);
		rolesDao.deleteRole(id);
	}
	// 根据 角色 id ，权限 id 删除关联信息
	@Override
	public void deleteByRoleIdPermissionId(Roles roles) {
		//等待创建  DELECT FROM roles_permissions WHERE role_id = #{rid} AND permission_id = #{cid};
		System.out.println(roles.toString()+":::::::::::::::::::");
		rolesDao.deleteByRoleIdPermissionId(roles.getId(),roles.getPermissions().get(0).getCid());
	}
	// 根据 角色 id 查匈关联权限
	@Override
	public List<Permission> getById2(Integer id) {
		/*Roles byId = rolesDao.getById(id);;;;;;;;;;*/
	/*byId.getRolesPermissions();*/
		List<Permission> permission = permissionDao.getRolesIdToPermission(id);

		return permission;
	}
	//通过角色ID删除所有权限  2019-08-6  原 通过关联表对象删除关联表数据
	@Override
	public void deleteAll(Roles roles) {
		// TODO Auto-generated method stub
		rolesDao.deleteAll(roles.getId());
	}
	
}
