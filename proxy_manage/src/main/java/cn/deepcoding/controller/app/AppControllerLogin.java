package cn.deepcoding.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.deepcoding.model.ProxyTeacherWatch;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.deepcoding.service.AppServiceNew;
import cn.deepcoding.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/appNew")
@Api(value = "newApp接口", description = "app登陆模块的接口")
public class AppControllerLogin {
	@Autowired
	private AppServiceNew appServicenew;
	// app 用户登陆
	@RequestMapping("/appLogin")
	@ResponseBody
	@ApiOperation(value = "app 用户登陆", httpMethod = "POST", notes = "app 用户登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "appTel", value = "app 手机号", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "appPassword", value = "app 密码", required = true, dataType = "String"), })
	public ServerResponse appLogin(HttpServletRequest request, HttpServletResponse response, String appTel,
								   String appPassword) {
		return appServicenew.getByName(appTel, appPassword);

	}
	// 手机号登陆
	@RequestMapping("/appUserLogin")
	@ResponseBody
	@ApiOperation(value = "手机号的登陆", httpMethod = "POST", notes = "app 用户登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "tel", value = "手机号", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "code", value = "手机验证码", required = true, dataType = "String"),})
	public ServerResponse appUserLogin(HttpServletRequest request, HttpServletResponse response, String tel,
								   String code) {
		return appServicenew.appUserLogin(tel, code);

	}
	//手机号微信绑定
		@RequestMapping("/bound")
		@ResponseBody
		@ApiOperation(value = "app 用户微信绑定", httpMethod = "POST", notes = "app 用户微信绑定")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "openid", value = "用户的openidID", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "nickname", value = "用户的昵称", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "sex", value = "用户的性别", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "city", value = "用户所在城市", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "country", value = "用户所在国家", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "province", value = "用户所在省份", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "code", value = "手机验证码", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "appTel", value = "app 手机号", required = true, dataType = "String"), })
		public ServerResponse bound(String openid,String nickname,Integer sex,String city,String country,String province,String appTel,String code){
			return  appServicenew.addProxyTeacherWatchInfo(openid,nickname,sex,city,country,province,appTel,code);

		}
		// app 发送短信
		@RequestMapping("/appMseeage")
		@ResponseBody
		@ApiOperation(value = "发送短信获取验证码", httpMethod = "POST", notes = "根据手机号发送短信")
		@ApiImplicitParam(paramType = "query", name = "appTel", value = "手机号码", required = true, dataType = "String")
		public ServerResponse getMessage(String appTel) {
			return appServicenew.sendMassage(appTel);

		}

		// 修改密码
		@RequestMapping("/updatePassword")
		@ResponseBody
		@ApiOperation(value = "修改密码", httpMethod = "POST", notes = "修改密码")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "appTel", value = "手机号", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "code", value = "验证码", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "password", value = "新密码", required = true, dataType = "String"), })
		public ServerResponse updatePassword(String code, String appTel, String password) {
			return appServicenew.updatePassword(code, appTel, password);
		}
}
