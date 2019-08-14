package cn.deepcoding.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.PrincipalCollection;

import cn.deepcoding.model.Roles;
import cn.deepcoding.model.Users;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface UsersService {

	// 查询所有
	PageData getAll(Users users, Page page);

	// 根据 id查询
	Users getById(Integer id);

	List<Roles> getById2(Integer id);

	// 修改
	void update(Users users, Roles roles);

	// 添加
	int addUser(Users users);

	// 关联角色添加
	void addUser3(Users users);

	// 根据id 删除
	void del(Integer id);

	// 关联角色删除
	void del2(Integer userId, Integer roleId);

	// 修改密码前判断用户
	Boolean checkPassword(String password, HttpServletRequest request);

	// 修改密码
	boolean newPass(String password, Integer id);

	// 专门为获取昵称
	String niname();

	// 图片捕获
	String cat(HttpServletRequest request);

	// 上传图片
	String faceAdd(String image, Integer id, HttpServletRequest request);

	// 删除库里面的人脸识别
	String facedel(Integer id);
	// 比对人脸库照片
	Boolean faceLogin(String image, HttpServletRequest request);

}
