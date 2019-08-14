package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Users;
import cn.deepcoding.page.Page;

public interface UsersDao {
	
	// 查询所有
	List<Users> getAll(@Param("users")Users users,@Param("page")Page page);
	// 根据 id查询
	Users getById(Integer id);
	Users getById2(Integer id);
	// 根据名字查询
	Users getByName(String username);
	// 修改
	void update(Users users);
	// 添加
	int addUser(Users users);
	// 根据id 删除
	void del(Integer id);
	//分页查询总数
	Integer getAllCount(Users users);
	//更新密码
	boolean newPass(Users users);
	//根据ID更改百度人脸识别token
	void updatefacatokenByid(Users user);

}
