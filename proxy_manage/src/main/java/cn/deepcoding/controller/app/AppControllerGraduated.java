package cn.deepcoding.controller.app;

import javax.servlet.http.HttpServletRequest;

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
@Api(value = "newApp接口", description = "就业模块的接口")
@Controller
@RequestMapping("/appNew")
public class AppControllerGraduated {
	@Autowired
	private AppServiceNew appServicenew;
	
	
	// 根据省，市，县，学生姓名 查询 就业信息
		@RequestMapping("/getgraduatedbyaddress")
		@ResponseBody
		@ApiOperation(value = "根据省，市，县，学生姓名 查询 就业信息", httpMethod = "POST", notes = "根据省，市，县，学生姓名 查询 就业信息")
		public ServerResponse getGraduatedByaddress(String keyword, int page, int rows) {
			GraduatedResult graduatedResult = appServicenew.getGraduatedByaddress(keyword, page, rows);
			return ServerResponse.getSuccess("SUCCESS", graduatedResult);
		}

		// 学生 就业信息详情
		@RequestMapping("/getgraduatedbyId")
		@ResponseBody
		@ApiOperation(value = "学生就业信息详情", httpMethod = "POST", notes = "学生就业信息详情")
		@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer")
		public ServerResponse getGraduatedById(Integer id) {
			return appServicenew.getGraduatedById(id);

		}

		// 查询所有的学生 就业信息详情
		@RequestMapping("/getallgraduated")
		@ResponseBody
		@ApiOperation(value = "所有学生就业信息根据时间倒序", httpMethod = "POST", notes = "所有学生就业信息根据时间倒序")
		public ServerResponse getallgraduated(Integer page, Integer rows) {
			PageHelper.startPage(page, rows);
			return appServicenew.getallgraduated();
		}

		// 查询就业精英信息
		@RequestMapping("/getElite")
		@ResponseBody
		@ApiOperation(value = "查询就业精英信息", httpMethod = "GET", notes = "查询就业精英信息")
		public ServerResponse getElite() {
			return appServicenew.getElite();

		}


		//就业人员按地区分类
		@RequestMapping("/getGradutionPresonList")
		@ResponseBody
		@ApiOperation(value = "就业人员按地区分类", httpMethod = "GET", notes = "就业人员按地区分类")
		public ServerResponse getGradutionPresonList() {
			return appServicenew.getGradutionPresonList();

		}
		
		// 根据学生姓名或者地区查询学生就业信息
		@RequestMapping("/getGraduated")
		@ResponseBody
		@ApiOperation(value = "学生就业信息列表", httpMethod = "POST", notes = "根据学生姓名或者地区查询学生就业信息")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "name", value = "学生姓名", required = false, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "address", value = "地址", required = false, dataType = "String")})
				
		public ServerResponse getGraduated(String name,String address) {
			
			return appServicenew.getGraduated(name,address);
		}
		// 根据学生id查询学生就业信息
				@RequestMapping("/getGraduatedByStudentId")
				@ResponseBody
				@ApiOperation(value = "学生个人就业信息", httpMethod = "POST", notes = "根据学生id查询学生就业信息")
				@ApiImplicitParams({
						@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = false, dataType = "String")})
						
				public ServerResponse getGraduatedByStudentId(Integer id) {
					
					return appServicenew.getGraduatedByStudentId(id);
				}
}
