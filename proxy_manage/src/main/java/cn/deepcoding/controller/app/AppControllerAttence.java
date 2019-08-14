package cn.deepcoding.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.deepcoding.service.AppServiceNew;
import cn.deepcoding.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/appNew")
@Api(value = "newApp接口", description = "考勤模块的接口")
public class AppControllerAttence {
	@Autowired
	private AppServiceNew appServicenew;
	// 根据学生 id 查询 钉钉唯一标识符并获取考勤信息
		@RequestMapping("/getAttenceByStudentId")
		@ResponseBody
		@ApiOperation(value = "学生考勤信息列表", httpMethod = "POST", notes = "根据学生 id 获取学生考勤信息")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer") })
		public ServerResponse getAttenceByStudentId(Integer id,  HttpServletRequest request,String token) throws Exception {
			//return appServicenew.getAttenceByStudentId1(id,  WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getAttenceByStudentId1(id,token);
		}

		// 根据学生id 查询 考勤记录查看最近一个月的明细表
		@RequestMapping("/getAttenceByStudent1")
		@ResponseBody
		@ApiOperation(value = "最近 一个月学生考勤明细表", httpMethod = "POST", notes = " 获取学生考勤明细表")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "token", value = "Token", required = true, dataType = "String"), })
		public ServerResponse getAttenceByStudent1(Integer id,  HttpServletRequest request,String token) {
			//return appServicenew.getAttenceByStudent1(id, WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getAttenceByStudent1(id,token);
		}

		// 根据学生id 查询 考勤记录查看最近三个月的明细表
		@RequestMapping("/getAttenceByStudent2")
		@ResponseBody
		@ApiOperation(value = "最近 三个月学生考勤明细表", httpMethod = "POST", notes = " 获取学生考勤明细表")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "token", value = "Token", required = true, dataType = "String"), })
		public ServerResponse getAttenceByStudent2(Integer id, HttpServletRequest request,String token) {
			//return appServicenew.getAttenceByStudent2(id, WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getAttenceByStudent2(id,token);
		}

		// 根据学生id 查询 考勤记录查看最近六个月的明细表
		@RequestMapping("/getAttenceByStudent3")
		@ResponseBody
		@ApiOperation(value = "最近 六个月学生考勤明细表", httpMethod = "POST", notes = " 获取学生考勤明细表")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "token", value = "Token", required = true, dataType = "String"), })
		public ServerResponse getAttenceByStudent3(Integer id, HttpServletRequest request,String token) {
			//return appServicenew.getAttenceByStudent3(id, WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getAttenceByStudent3(id,token);
		}
}
