package cn.deepcoding.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.deepcoding.model.User;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.UserService;
import cn.deepcoding.util.AppMD5Util;


@Controller
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user")
	public String user() {
		
		return "user/user";
	}
	
	@RequestMapping("/save")
	public String save(User user,Integer roleid) {
		userService.save(user,roleid);
		return "user/user";
	}
	@RequestMapping("/updatepwd")
	@ResponseBody
	public Boolean updatepwd(Integer id) {
		Boolean bo=userService.updatepwd(id);
		return bo;
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public User get(Integer id,ModelMap model) {
		User users=userService.get(id);
		return users;
	}
	@RequestMapping("/delete")
	public String delete(Integer id) {
		userService.delete(id);
		return "user/user";
	}
	
	@RequestMapping("/update")
	public String update(User user,Integer rid) {
			userService.update(user,rid);
		return "user/user";
	}
	@RequestMapping("/newPass")
	@ResponseBody
	public boolean newPass(String password,Integer id){
		boolean bl = userService.newPass(AppMD5Util.getMD5(password),id);
		return bl;
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public List<User> find(User user){
		List<User> users=userService.find(user);
		return users;
	}
	@RequestMapping("/userpage")
	@ResponseBody
	public PageData Userpage(User user,ModelMap map,Page page){
		
		return userService.find(page, user);
	}
	
	@RequestMapping("/select")
	@ResponseBody
	public PageData userselectpage(User user,ModelMap map,Page page) {
		return userService.select(page, user);
	}
	@RequestMapping("/checkPassword")
	@ResponseBody
	public boolean checkPassword(HttpServletRequest req, String password) {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if(password!=null && AppMD5Util.getMD5(password).equals(user.getPassword())){
			
			return true;
		}else{
			return false;
		}
	}
	@RequestMapping(value="/usermanager")
	public String usermanage(){
		return "user/usermanager";
	}
}
