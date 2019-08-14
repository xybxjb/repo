package cn.deepcoding.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.deepcoding.model.GraduatedResult;
import cn.deepcoding.service.AppServiceNew;
import cn.deepcoding.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping("/appNew")
@Api(value = "newApp接口", description = "个人中心模块的接口")
public class AppControllerPersonalCenter {
	@Autowired
	private AppServiceNew appServicenew;
	// 我的资料
		@RequestMapping("/getProxyTeacher2")
		@ResponseBody
		@ApiOperation(value = "查看自己的资料", httpMethod = "POST", notes = "查看自己的资料")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "token", value = "Token", required = true, dataType = "String"), })
		public ServerResponse getProxyTeacher2(HttpServletRequest request,String token) {
			String string = WebUtils.toHttp(request).getHeader("Authorization");
//			System.out.println("---------fsdfsd-------"+string);
//			return null;
			return appServicenew.getProxyTeacher(token);
		}
	
}
