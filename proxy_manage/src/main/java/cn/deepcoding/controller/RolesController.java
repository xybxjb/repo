package cn.deepcoding.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Permission;
import cn.deepcoding.model.Roles;
import cn.deepcoding.service.RolesService;

// 角色
@Controller
@RequestMapping("/roles")
public class RolesController {
	
	@Autowired
	private RolesService rolesService;
	
	// 进入 角色管理页面
	@RequiresPermissions("roles:index")
	@RequestMapping("/index")
	public String roles(){
		return "permission/roles";
	}
	@RequiresPermissions("roles:index2")
	@RequestMapping("/index2")
	/*@RequestMapping(value="/index2",method=RequestMethod.GET)*/
	public String roles1(){
		return "permission/roles2";
	}
	// 查询所有
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Roles> getAll(Roles roles){
		List<Roles> all = rolesService.getAll(roles);
		return all;
	}

	// 根据id 查询
	@RequestMapping("/getById")
	@ResponseBody
	public Roles getBuId(Integer id){
		Roles byId = rolesService.getById(id);
		return byId;
	}
	// 根据id 查询关联权限
	//根据角色id获取所有权限
	@RequestMapping("/getById2")
	@ResponseBody
	public List<Permission> getBuId2(Integer id){
		List<Permission> byId2 = rolesService.getById2(id);
		return byId2;
	}
	// 查询所有权限
	@RequestMapping("/getAllPermission")
	@ResponseBody
	public List<Permission> getAllPermission(Permission permissions){
		 List<Permission> allPermission = rolesService.getAllPermission(permissions);
		return allPermission;
	}
	// 修改
	@RequestMapping("/update")
	@ResponseBody
	public void update(Roles roles){
		rolesService.updateRole(roles);
	}
	// 添加
	@RequestMapping("/AddRole")
	@ResponseBody
	public void addRole(Roles roles){
		rolesService.addRole(roles);
	}
	// 添加 关联权限
	//根据角色id和权限id添加中间表
	@RequestMapping("/AddRole3")
	@ResponseBody
	public void addRole3(Roles roles){
		rolesService.addRole3(roles);
	}
	
	// 删除
	@RequestMapping("/del")
	@ResponseBody
	public void deleteRole(Integer id){
		rolesService.deleteRole(id);
	}
	// 根据角色 id 和 权限 id 删除关联信息
	//根据角色id和权限id删除中间表
	@RequestMapping("/del2")
	@ResponseBody
	public void deleteByRoleIdPermissionId(Roles roles){
		rolesService.deleteByRoleIdPermissionId(roles);
	}
	//根据角色id查询中间表
//	@RequestMapping("/getByPrid")
//	@ResponseBody
//	public List<Roles> getByPrid(Roles roles){
//		List<Roles> allPermission = rolesService.getByPrid(roles);
//		return allPermission;
//	}
	
	@RequestMapping("/deleteAll")
	@ResponseBody
	public void deleteAll(Roles roles){
		rolesService.deleteAll(roles);
	}
}
