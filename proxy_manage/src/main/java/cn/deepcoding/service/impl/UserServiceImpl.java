package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.UserDao;
import cn.deepcoding.model.User;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.UserService;
import cn.deepcoding.util.AppMD5Util;



@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void save(User user,Integer roleid) {
		user.setPassword(AppMD5Util.getMD5(user.getPassword()));
		userDao.save(user);
		Integer uid=user.getId();
		userDao.add(uid, roleid);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		userDao.del(id);
		
	}

	@Override
	public void update(User user,Integer rid) {
		// TODO Auto-generated method stub

			user.setPassword(AppMD5Util.getMD5(user.getPassword()));

			userDao.update(user);
	}

	@Override
	public List<User> logins(String username) {
		// TODO Auto-generated method stub
		
		return userDao.loginUser(username);
	}

	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		User user = userDao.get(id);
		return user;
	}

	@Override
	public List<User> find(User user) {
		// TODO Auto-generated method stub
		return userDao.find(user);
	}

	@Override
	public PageData select(Page page, User user) {
		// TODO Auto-generated method stub
		return userDao.select(page, user);
	}

	public PageData find(Page page, User user) {
		// TODO Auto-generated method stub

		List<User> users=userDao.findByPage(user, page);
		int total= userDao.getTotal(user);
		
		PageData data=new PageData();
		data.setRows(users);
		data.setTotal(total);
		return data;
	}

	@Override
	public Boolean updatepwd(Integer id) {
		// TODO Auto-generated method stub
		String resetpw="123456";
		return userDao.updatepwd(id,AppMD5Util.getMD5(resetpw));
	}

	@Override
	public boolean newPass(String password, Integer id) {
		// TODO Auto-generated method stub
		return userDao.newPass(password,id);
		
	}
	@Override
	public User getuserbyname(String name) {
		// TODO Auto-generated method stub
		return userDao.getuserbyName(name);
	}

	
}
