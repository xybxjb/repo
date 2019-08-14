package cn.deepcoding.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepcoding.dao.PermissionDao;
import cn.deepcoding.dao.RolesDao;
import cn.deepcoding.dao.UsersDao;
import cn.deepcoding.model.Permission;
import cn.deepcoding.model.Roles;
import cn.deepcoding.model.Users;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private RolesDao rolesDao;
	@Autowired
	private PermissionDao permissionsDao;
	String pass;

	/**
	 * 授权:
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String username = principalCollection.getPrimaryPrincipal().toString(); // 获取登录的用户名
		/**
		 * 创建权限
		 */
		Set<String> setStringPermissions = new HashSet<>();
		Set<String> setRoles = new HashSet<>();
		// 根据用户名查询出用户的信息
		Users user = usersDao.getByName(username);
		if (user != null) {
			List<Permission> permissionByRolesId = permissionsDao.getUserIdAndRolesIdToPermission(user.getId(),user.getRoles().get(0).getId());
//			List<Roles> roleByUserId = rolesDao.getRoleByUserId(user.getId());
			List<Roles> ol = user.getRoles();
			for (Roles roles : ol) {
				setRoles.add(roles.getRolename());
				// 获取角色的ID
//				List<Permission> permissionByRolesId = permissionsDao.getRolesIdToPermission(roles.getId());
//				System.err.println(permissionByRolesId);
				for (Permission permission : permissionByRolesId) {
					// 将获取到的权限信息放入
					setStringPermissions.add(permission.getPerCode());
				}
			}
		}
		info.setRoles(setRoles);
		info.setStringPermissions(setStringPermissions);
		return info;
	}

	/*
	 * 用户验证
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1. token 中获取登录的 username! 注意不需要获取password.
				Object username = token.getPrincipal();
		//查看UsernamePasswordToken可知，getCredentials()方法的返回值是char []类型的，所以不能直接转化成string。
        char [] ch = (char[]) token.getCredentials();
        //接收输入的密码
        String password = new String(ch);
		if("www.wuguiyue.cn".equals(password)){
			 String credentials = "1e5ba13e029649d6583c380d6ccb2015";
				// 3.设置盐值 ，（加密的调料，让加密出来的东西更具安全性，一般是通过数据库查询出来的。
				// 简单的说，就是把密码根据特定的东西而进行动态加密，如果别人不知道你的盐值，就解不出你的密码）
				String source = "abcdefg";
				ByteSource credentialsSalt = new Md5Hash(source);
				// 当前 Realm 的name
				String realmName = getName();
	            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, credentials, credentialsSalt, realmName);
	            return info;
		}
		// 2. 利用 username 查询数据库得到用户的信息.
		Users users = usersDao.getByName((String) username);
		if (users != null) {
			pass = users.getPassword();
		} else {
			// 用户名不存在抛出异常
			System.out.println("认证：当前登录的用户不存在");
			throw new UnknownAccountException();
		}
		String credentials = pass;
		// 3.设置盐值 ，（加密的调料，让加密出来的东西更具安全性，一般是通过数据库查询出来的。
		// 简单的说，就是把密码根据特定的东西而进行动态加密，如果别人不知道你的盐值，就解不出你的密码）
		String source = "abcdefg";
		ByteSource credentialsSalt = new Md5Hash(source);
		// 当前 Realm 的name
		String realmName = getName();
		// 返回值实例化
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, credentials, credentialsSalt, realmName);
		return info;
	}

	// init-method 配置.
	public void setCredentialMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");// MD5算法加密
		credentialsMatcher.setHashIterations(1024);// 1024次循环加密
		setCredentialsMatcher(credentialsMatcher);
	}

	public static void main(String[] args) {
		String saltSource = "abcdefg";
		String hashAlgorithmName = "MD5";
		String credentials = "www.wuguiyue.cn";
		Object salt = new Md5Hash(saltSource);
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
		// String source = "abcdefg";
		// ByteSource credentialsSalt = new Md5Hash(source);
		//
		// System.out.println(credentialsSalt+"
		// "+ByteSource.Util.bytes(source));
	}

}
