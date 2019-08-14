package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baidu.aip.face.AipFace;

import cn.deepcoding.dao.RolesDao;
import cn.deepcoding.dao.UsersDao;
import cn.deepcoding.model.Dormitory;
import cn.deepcoding.model.Roles;
import cn.deepcoding.model.Users;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.UsersService;
import cn.deepcoding.util.AppMD5Util;
import cn.deepcoding.util.aipUtil;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private RolesDao rolesDao;

	// 获取所有
	@Override
	public PageData getAll(Users users, Page page) {
		List<Users> list = usersDao.getAll(users, page);
		Integer count = usersDao.getAllCount(users);
		PageData pageData = new PageData();
		pageData.setTotal(count);
		pageData.setRows(list);
		return pageData;
		// return usersDao.getAll(users);
	}

	// 根据 id获取
	@Override
	public Users getById(Integer id) {

		return usersDao.getById(id);
	}

	// 根据 id获取2
	@Override
	public List<Roles> getById2(Integer id) {
		List<Roles> role =rolesDao.getRoleByUserId(id);
		return role;
	}

	/**
	 * 只需要修改昵称
	 * 
	 */
	// 修改
	@Override
	public void update(Users users, Roles roles) {
		// users.setPassword(AppMD5Util.getMD52(users.getPassword()));
		// usersRoles.setRoles(roles);
		// usersRolesDao.updateByUserId(usersRoles);
		usersDao.update(users);

	}

	// 添加用户
	@Override
	public int addUser(Users users) {
		// 获取新建的用户名
		String username = users.getUsername();
		// 判断这个用户名是否存在
		if (usersDao.getByName(username) != null) {
			return 0;
		}
		System.out.println(users);
		// 添加默认密码
		users.setPassword(AppMD5Util.getMD52("123456"));
		int addUser = usersDao.addUser(users);
		Integer userId = users.getId();
		System.out.println(userId);
		// usersRoles.getUsers().setId(userId);
		Roles r = new Roles();
		r.setId(users.getRoleId());
		rolesDao.addUserRoles(userId,r.getId());
		return addUser;

	}

	// 关联角色添加
	@Override
	public void addUser3(Users users) {
//		UsersRoles usersRoles = new UsersRoles();
//		usersRoles.setUsers(users);
//		Roles r = new Roles();
//		r.setId(users.getRoleId());
//		usersRoles.setRoles(r);
//		;
//		usersRolesDao.add(usersRoles);
		rolesDao.addUserRoles(users.getId(), users.getRoleId());
	}

	// 删除
	@Override
	public void del(Integer id) {
		rolesDao.deleteByUserId(id);
		usersDao.del(id);

	}

	// 关联角色删除
	@Override
	public void del2(Integer userId, Integer roleId) {
		rolesDao.deleteByUserIdRoleId(userId, roleId);

	}

	// 修改密码前判断
	@Override
	public Boolean checkPassword(String password, HttpServletRequest request) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		Users users = usersDao.getByName(username);
		request.getSession().setAttribute("user", users);
		// TODO Auto-generated method stub
		if (password != null && AppMD5Util.getMD52(password).equals(users.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	// 修改密码
	@Override
	public boolean newPass(String password, Integer id) {

		// TODO Auto-generated method stub
		Users users = new Users();
		users.setId(id);
		users.setPassword(AppMD5Util.getMD52(password));
		return usersDao.newPass(users);
	}

	// 获取昵称
	@Override
	public String niname() {
		// TODO Auto-generated method stub
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		Users users = usersDao.getByName(username);
		return users.getName();
	}

	// 上传图片
	@Override
	public String cat(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String basePath = "/images/upload/";
		String path = request.getSession().getServletContext().getRealPath("");
		path = path.replaceAll("proxy_manage", "");
		path = path.substring(0, path.length() - 1);
		String filePath = path + basePath;
		// String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".png";
		String fileName = (new Date()).getTime() + ".png";
		// 默认传入的参数带类型等参数：data:image/png;base64,
		String imgStr = request.getParameter("image");
		if (null != imgStr) {
			imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
		}
		Boolean flag = aipUtil.GenerateImage(imgStr, filePath, fileName);
		String result = "";
		if (flag) {
			result = basePath + fileName;
		}
		return result;
	}

	// 上传图片
	@Override
	public String faceAdd(String image, Integer id, HttpServletRequest request) {
		// 获取对应的用户信息
		Users users = usersDao.getById(id);
		// TODO Auto-generated method stub
		String path = request.getSession().getServletContext().getRealPath("");
		path = path.replaceAll("proxy_manage", "");
		path = path.substring(0, path.length() - 1);
		// 初始化一个AipFace
		AipFace client = new AipFace(aipUtil.APP_ID, aipUtil.API_KEY, aipUtil.SECRET_KEY);
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("user_info", users.getUsername());
		options.put("quality_control", "NONE");
		options.put("liveness_control", "NORMAL");
		String uid = users.getUsername();
		// 参数为本地图片路径
		String imageurl = path + image;
		System.out.println(imageurl);
		JSONObject updateUser = client.updateUser(aipUtil.getImgStr(imageurl), "BASE64", aipUtil.GROUP_ID, uid,
				options);
		// 根据返回信息判断是trueAndfalse
		String updatemsg = (String) updateUser.get("error_msg");
		if (!"SUCCESS".equals(updatemsg)) {
			JSONObject addUser = client.addUser(aipUtil.getImgStr(imageurl), "BASE64", aipUtil.GROUP_ID, uid, options);
			//删除缓存图片
			aipUtil.delete(imageurl);
			String addmsg = (String) addUser.get("error_msg");
			if ("SUCCESS".equals(addmsg)) {
				JSONObject result = addUser.getJSONObject("result");
				String face_token = (String) result.get("face_token");
				Users user = new Users();
				user.setId(id);
				user.setFackToken(face_token);
				usersDao.updatefacatokenByid(user);
				System.err.println("添加时的信息" + result.toString(2));
				return "success";
			} else {
				return "error";
			}
		}
		//删除缓存图片
		aipUtil.delete(imageurl);
		JSONObject result = updateUser.getJSONObject("result");
		String face_token = (String) result.get("face_token");
		Users user = new Users();
		user.setId(id);
		user.setFackToken(face_token);
		usersDao.updatefacatokenByid(user);
		System.err.println("添加时的信息" + result.toString(2));
		return "success";
	}

	// 删除库里面的人脸识别
	@Override
	public String facedel(Integer id) {
		// TODO Auto-generated method stub
		// 获取对应的用户信息
		Users users = usersDao.getById(id);
		// 初始化一个AipFace
		AipFace client = new AipFace(aipUtil.APP_ID, aipUtil.API_KEY, aipUtil.SECRET_KEY);
		JSONObject deleteuser = client.faceDelete(users.getUsername(), aipUtil.GROUP_ID, users.getFackToken(), null);
		System.err.println("123456" + deleteuser.toString(2));
		return (String) deleteuser.get("error_msg");
	}

	// 比对人脸库照片
	@Override
	public Boolean faceLogin(String image, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String path = request.getSession().getServletContext().getRealPath("");
		path = path.replaceAll("proxy_manage", "");
		path = path.substring(0, path.length() - 1);
		// 初始化一个AipFace
		AipFace client = new AipFace(aipUtil.APP_ID, aipUtil.API_KEY, aipUtil.SECRET_KEY);
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "NONE");
		options.put("liveness_control", "NORMAL");
		// 参数为本地图片路径
		String imageurl = path + image;
		JSONObject search = client.search(aipUtil.getImgStr(imageurl), "BASE64", aipUtil.GROUP_ID, options);
		//删除缓存图片
		aipUtil.delete(imageurl);
		if (search.get("error_msg").equals("SUCCESS")) {
			JSONArray List = search.getJSONObject("result").getJSONArray("user_list");
			for (int a = 0; a < List.length(); a++) {
				JSONObject user = (JSONObject) List.get(a);
				Double score = (Double) user.get("score");
				//当返回分数值大于90分时,直接登陆
				System.err.println("人脸识别分数："+score);
				if (score > aipUtil.SCORE) {
					// 获取主体
					Subject subject = SecurityUtils.getSubject();
					String username = (String) user.get("user_id");
					String password = "www.wuguiyue.cn";
					UsernamePasswordToken token = new UsernamePasswordToken(username, password);
					subject.login(token);
					return true;
				}
				return false;
			}
		}

		return false;
	}

}
