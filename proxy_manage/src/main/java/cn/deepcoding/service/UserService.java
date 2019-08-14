package cn.deepcoding.service;

import java.util.List;
import java.util.Set;

import cn.deepcoding.model.User;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface UserService {

	public List<User> logins(String username);
	
	void save(User user,Integer roleid);

	

	void delete(Integer id);

	void update(User user,Integer rid);

	public User get(Integer id);

	public List<User> find(User user);

	public PageData select(Page page, User user);


	public PageData find(Page page, User user);

	public boolean newPass(String password, Integer id);
	public Boolean updatepwd(Integer id);
	public User getuserbyname(String name);
}
