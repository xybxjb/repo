package cn.deepcoding.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.deepcoding.model.Roles;
import cn.deepcoding.model.Users;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.UsersService;
import cn.deepcoding.util.aipUtil;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	// 进入 用户页面
	@RequiresPermissions("users:index")
	@RequestMapping("/index")
	public String users() {
		return "permission/users";
	}

	// 查询 所有用户
	@RequestMapping("/getAll")
	@ResponseBody
	public PageData getAll(Users users, Page page) {
		return usersService.getAll(users, page);
	}

	// 根据 id 查询 用户
	@RequestMapping("/getById")
	@ResponseBody
	public Users getById(Integer id) {
		Users byId = usersService.getById(id);
		return byId;
	}

	// 根据 id 查询 用户
	@RequestMapping("/getById2")
	@ResponseBody
	public List<Roles> getById2(Integer id) {
		List<Roles> byId2 = usersService.getById2(id);
		return byId2;
	}

	// 修改
	@RequestMapping("/update")
	@ResponseBody
	public void update(Users users, Roles roles) {
		usersService.update(users, roles);
	}

	// 添加
	@RequestMapping("/AddUser")
	@ResponseBody
	public int addUser(Users users) {
		return usersService.addUser(users);
	}

	// 添加关联角色
	@RequestMapping("/AddUser3")
	@ResponseBody
	public void addUser3(Users users) {
		// System.out.println(users);
		usersService.addUser3(users);
	}

	// 删除
	@RequestMapping("/del")
	@ResponseBody
	public void delete(Integer id) {
		usersService.del(id);
	}

	// 删除关联角色
	@RequestMapping("/del2")
	@ResponseBody
	public void delete2(Integer userId, Integer roleId) {
		usersService.del2(userId, roleId);
	}
	// 修改密码前验证

	@RequestMapping("/checkPassword")
	@ResponseBody
	public Boolean checkPassword(String password, HttpServletRequest request) {
		return usersService.checkPassword(password, request);
	}

	// 修改密码
	@RequestMapping("/newPass")
	@ResponseBody
	public boolean newPass(String password, Integer id) {
		return usersService.newPass(password, id);
	}

	// 重置密码
	@RequestMapping("/resertPassword")
	@ResponseBody
	public boolean resertPassword(Integer id) {
		return usersService.newPass("123456", id);
	}

	// 获取昵称

	@RequestMapping(value = "/niname", produces = { "application/text;charset=UTF-8" })
	@ResponseBody
	public String niname() {
		return usersService.niname();
	}

	// 捕获图片
	@RequestMapping("/cat")
	@ResponseBody
	public String cat(HttpServletRequest request) throws IOException {
		return usersService.cat(request);

	}

	// 上传图片
	@RequestMapping("/faceAdd")
	@ResponseBody
	public String faceAdd(String image, Integer id, HttpServletRequest request) {
		return usersService.faceAdd(image, id, request);
	}

	// 删除库里面的人脸识别照片
	@RequestMapping("/facedel")
	@ResponseBody
	public String facedel(Integer id) {
		return usersService.facedel(id);
	}

	// 比对人脸库照片
	@RequestMapping("/faceLogin")
	@ResponseBody
	public Boolean faceLogin(String image, HttpServletRequest request) {
		return usersService.faceLogin(image,request);
	}

}
