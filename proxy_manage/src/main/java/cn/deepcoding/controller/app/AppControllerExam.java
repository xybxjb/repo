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
@Api(value = "newApp接口", description = "考试模块的接口")
public class AppControllerExam {
	@Autowired
	private AppServiceNew appServicenew;
	// 根据 学生id 查询考试记录
		@RequestMapping("/getExamByStudentId")
		@ResponseBody
		@ApiOperation(value = "学生考试记录", httpMethod = "POST", notes = "根据学生 id 获取学生考试记录")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "page", value = "当前页数", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "rows", value = "每页个数", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "token", value = "Token", required = true, dataType = "String"), })
		public ServerResponse getExamByStudentId(Integer id, Integer page, Integer rows, HttpServletRequest request,String token) {
			PageHelper.startPage(page, rows);
			//return appServicenew.getExamByStudentId(id, WebUtils.toHttp(request).getHeader("Authorization"));
			return appServicenew.getExamByStudentId(id,token);
		}
		@RequestMapping("/getScoreByStudentId")
		@ResponseBody
		@ApiOperation(value = "学生考试成绩", httpMethod = "POST", notes = "根据学生 id 获取学生考试成绩")
		@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "id", value = "学生id", required = true, dataType = "Integer"),
				//@ApiImplicitParam(paramType = "query", name = "page", value = "当前页数", required = true, dataType = "Integer"),
				//@ApiImplicitParam(paramType = "query", name = "rows", value = "每页个数", required = true, dataType = "Integer"),
				@ApiImplicitParam(paramType = "query", name = "token", value = "Token", required = true, dataType = "String"), })
		public ServerResponse getScoreByStrudentId(Integer id,  HttpServletRequest request,String token){
			//PageHelper.startPage(page, rows);
			return appServicenew.getScoreByStudentId(id,token);
		}
}
