package cn.deepcoding.model;

import java.util.List;

// 角色表
public class Roles {

	private Integer id;
	private String rolename; // 角色名称
	private String description; // 描述
	private Integer available; // 状态
//	private Permissions permission; // 权限编号
	private List<Permission> Permissions; // 角色权限

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public List<Permission> getPermissions() {
		return Permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		Permissions = permissions;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", rolename=" + rolename + ", description=" + description + ", available="
				+ available + ", Permission=" + Permissions + "]";
	}

	


	

}
