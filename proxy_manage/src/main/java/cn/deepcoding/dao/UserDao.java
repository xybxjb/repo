package cn.deepcoding.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.User;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface UserDao  {

	int save(User user);

	void del(Integer id);

	void update(User user);
	
	public List<User> loginUser(String username);

	User get(Integer id);

	List<User> find(User user);

	PageData select(Page page, User user);


	List<User> findByPage(@Param("user") User user,@Param("page")Page page);

	void add(Integer uid, Integer roleid);



	int getTotal(@Param("user") User user);

	boolean newPass(String password, Integer id);
	Boolean updatepwd(@Param("id")Integer id,@Param("pw")String pw);
	
	public User getuserbyName(String name);
}
