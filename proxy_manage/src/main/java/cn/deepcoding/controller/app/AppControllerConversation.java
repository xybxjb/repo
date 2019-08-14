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
@Api(value = "newApp接口", description = "学生谈话记录模块的接口")
public class AppControllerConversation {
	@Autowired
	private AppServiceNew appServicenew;
	// 根据学生id 查询 谈话记录
		@RequestMapping("/getStudentConversationById")
		@ResponseBody
		@ApiOperation(value = "学生谈话记录", httpMethod = "POST", notes = "根据学生 id 获取学生谈话记录")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "page", value = "当前页数", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "rows", value = "每页个数", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "token", value = "Token", required = true, dataType = "String"), })
		public ServerResponse getStudentConversationById(Integer id, Integer page, Integer rows, HttpServletRequest request,String token) {
			PageHelper.startPage(page, rows);
			//return appServicenew.getStudentConversationById(id, WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getStudentConversationById(id,token);
		}

		// 根据id 查询 谈话记录详细表（图片）
		@RequestMapping("/getStudentConversationPicById")
		@ResponseBody
		@ApiOperation(value = "学生谈话记录明细", httpMethod = "POST", notes = "根据 id 获取学生谈话记录明细")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "id", value = "id", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "token", value = "Token", required = true, dataType = "String"), })
		public ServerResponse getStudentConversationPicById(Integer id, HttpServletRequest request,String token) {
			//return appServicenew.getStudentConversationPicById(id, WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getStudentConversationPicById(id,token);
		}
}
