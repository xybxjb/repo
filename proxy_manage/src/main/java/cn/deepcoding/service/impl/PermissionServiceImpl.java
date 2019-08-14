package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.PermissionDao;
import cn.deepcoding.model.Permission;
import cn.deepcoding.service.PermissionService;
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;
	/*@Autowired
	private RolesPermissionsDao rolesPermissionsDao;*/
	// 鏌ヨ鎵�鏈�
	@Override
	public List<Permission> getAll(Permission permissions) {
	
		return permissionDao.getAll(permissions);
	}
	// 鏍规嵁 id 鏌ヨ
	@Override
	public Permission getById(Integer id) {
	
		return permissionDao.getById(id);
	}
	// 淇敼
	@Override
	public void update(Permission permissions) {
		permissionDao.update(permissions);

	}
	// 娣诲姞
	@Override
	public void addPermission(Permission permissions) {
		if(permissions.getpId().equals("")){
			permissionDao.addPermission2(permissions);
		}else{
			permissionDao.addPermission(permissions);
		}
		

	}
	// 鍒犻櫎
	@Override
	public void del(Integer id) {
		permissionDao.deleteByPermissionId(id);
		permissionDao.del(id);

	}
	 //鏌ユ壘鎵�鏈夌殑鏉冮檺
	@Override
	public List<Permission> selectPermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionDao.selectPermission(permission);
	}
	@Override
	public List<Permission> findIdByText(Permission permissions) {
		// TODO Auto-generated method stub
		return permissionDao.findIdByText(permissions);
	}
	@Override
	public List<Permission> getPid() {
		// TODO Auto-generated method stub
		return permissionDao.getPid();
	}
	@Override
	public List<Permission> getByCid(Permission permission) {
		// TODO Auto-generated method stub
		return permissionDao.getByCid(permission);
	}
	@Override
	public List<Permission> getRolesIdToPermission(Integer id) {
		// TODO Auto-generated method stub
		return permissionDao.getRolesIdToPermission(id);
	}

}
