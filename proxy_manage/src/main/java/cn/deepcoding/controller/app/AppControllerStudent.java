package cn.deepcoding.controller.app;

import javax.servlet.http.HttpServletRequest;

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
@Api(value = "newApp接口", description = "学生信息模块的接口")
public class AppControllerStudent {
	@Autowired
	private AppServiceNew appServicenew;
	// 根据学生 id 查询学生信息
		@RequestMapping("/getStudentById3")
		@ResponseBody
		@ApiOperation(value = "学生信息列表", httpMethod = "POST", notes = "根据学生 id 获取学生信息")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer") })
		public ServerResponse getStudentById3(Integer id, HttpServletRequest request,String token) {
			//return appServicenew.getStudentById3(id, WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getStudentById3(id,token);
		}

}
