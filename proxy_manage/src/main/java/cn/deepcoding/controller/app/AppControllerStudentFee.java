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
@Api(value = "newApp接口", description = "学生缴费模块的接口")
public class AppControllerStudentFee {
	@Autowired
	private AppServiceNew appServicenew;
		// 根据学生 id 查询学生缴费信息
		@RequestMapping("/getFeeByStudentId2")
		@ResponseBody
		@ApiOperation(value = "学生缴费信息列表", httpMethod = "POST", notes = "根据学生 id 获取学生缴费信息")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "page", value = "当前页数", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "rows", value = "每页个数", required = true, dataType = "Integer"), })
		public ServerResponse getFeeByStudentId2(Integer id, Integer page, Integer rows, HttpServletRequest request,String token) {
			PageHelper.startPage(page, rows);
			//return appServicenew.getFeeByStudentId2(id, WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getFeeByStudentId2(id,token);
		}
		
		/**
		 * 根据用户id查找学生学费
		 */
		@RequestMapping("/getTokenIdFindStuFee")
		@ResponseBody
		@ApiOperation(value = "学生缴费信息列表Fee", httpMethod = "POST", notes = "根据学生 id 获取学生缴费信息")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String"),
				})
		public ServerResponse getTokenIdFindStuFee(String token) {
			
			return appServicenew.getTokenIdFindStuFee(token);
		}
		
		/**
		 * 根据用户id模糊查找学生学费
		 */
		@RequestMapping("/getTokenIdLikeStuFee")
		@ResponseBody
		@ApiOperation(value = "模糊查找学生缴费信息列表", httpMethod = "POST", notes = "根据学生 id 获取学生缴费信息")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "stuName", value = "stuName", required = true, dataType = "String"),
				})
		public ServerResponse getTokenIdLikeStuFee(String stuName,String token) {
			
			return appServicenew.getTokenIdLikeStuFee(stuName, token);
		}
}
