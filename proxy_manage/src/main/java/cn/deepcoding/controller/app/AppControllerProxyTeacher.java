package cn.deepcoding.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Referrer;
import cn.deepcoding.page.Page;
import cn.deepcoding.service.AppServiceNew;
import cn.deepcoding.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/appNew")
@Api(value = "newApp接口", description = "招生老师模块的接口")
public class AppControllerProxyTeacher {
	@Autowired
	private AppServiceNew appServicenew;
	

	// 根据 招生老师 id 查询信息
	@RequestMapping("/getProxyTeacher")
	@ResponseBody
	@ApiOperation(value = "招生老师信息列表", httpMethod = "POST", notes = "根据招生老师 id 获取招生老师信息")
	@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String")
	public ServerResponse getProxyTeacher(HttpServletRequest request,String token) {
		//return appServicenew.getProxyTeacher(WebUtils.toHttp(request).getHeader("Authorization"));
		return appServicenew.getProxyTeacher(token);
	}

	// 根据 招生老师 id 查询信息
	@RequestMapping("/getParentProxyTeacher")
	@ResponseBody
	@ApiOperation(value = "招生老师上级信息列表", httpMethod = "POST", notes = "根据招生老师 id 获取招生老师上级信息")
	@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String")
	public ServerResponse getParentProxyTeacher(HttpServletRequest request,String token) {
	//	return appServicenew.getParentProxyTeacher(WebUtils.toHttp(request).getHeader("Authorization"));
		return appServicenew.getParentProxyTeacher(token);
	}

	// 根据招生老师查询下级信息 和下级 招生的个数
	@RequestMapping("/getSublevelProxyTeacher")
	@ResponseBody
	@ApiOperation(value = "招生老师下级信息列表", httpMethod = "POST", notes = "根据招生老师 id 获取招生老师下级信息")
	@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String")
	public ServerResponse getSublevelProxyTeacher(HttpServletRequest request,Integer id,String token) {
		System.err.println("token信息："+WebUtils.toHttp(request).getHeader("Authorization"));
		//return appServicenew.getSublevelProxyTeacher(WebUtils.toHttp(request).getHeader("Authorization"));
		return appServicenew.getSublevelProxyTeacher(token);
	}

	// 根据招生老师 id 查询学生信息
	@RequestMapping("/getStudentByProxyTeacherId2")
	@ResponseBody
	@ApiOperation(value = "招生老师下的学生列表", httpMethod = "POST", notes = "根据招生老师 id 获取招生老师下的学生信息")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query", name = "id", value = "招生老师id", required = false, dataType = "Integer"),
		@ApiImplicitParam(paramType="query", name = "token", value = "TOKEN", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query",name = "page", value = "当前页数",required = true,dataType = "Integer"),
		@ApiImplicitParam(paramType = "query",name = "rows", value = "每页个数",required = true,dataType = "Integer") , 
	})			
	public ServerResponse getStudentByProxyTeacherId2(Integer id,Page page, HttpServletRequest request,String token) {
		// 从请求头中获取token
	//	return appServicenew.getStudentByProxyTeacherId2(id,WebUtils.toHttp(request).getHeader("Authorization"), page);
		return appServicenew.getStudentByProxyTeacherId2(id,token, page);
	}
	
	//添加学生和其招生老师的姓名及其联系方式
	@RequestMapping("/addStuAndProxyTeacher")
	@ResponseBody
	@ApiOperation(value = "添加学生和其招生老师的姓名及其联系方式", httpMethod = "POST", notes = "添加学生和其招生老师的姓名及其联系方式")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "studentName", value = "学生的姓名", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "phone", value = "联系方式", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "teacherName", value = "招生老师的姓名", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "teacherPhone", value = "招生老师的电话", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "remark", value = "备注", dataType = "String")}	
			)
		
	public ServerResponse addStuAndProxyTeacher(String studentName,String phone,String teacherName,String teacherPhone,String remark) {
		return appServicenew.addStuAndProxyTeacher(studentName,phone,teacherName,teacherPhone,remark);
	}
	//添加推荐人信息
			@RequestMapping("/addReferrer")
			@ResponseBody
			@ApiOperation(value = "添加推荐人信息", httpMethod = "POST", notes = "添加推荐人信息及相关信息")
			@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "name", value = "姓名", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "phone", value = "联系方式", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "wechat", value = "微信", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "organization", value = "机构名称", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "referrer_name", value = "推荐人姓名",required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "referrer_tel", value = "推荐人电话", required = true,dataType = "String")}	
					)
				
			public ServerResponse addStuAndProxyTeacher(String name,String phone,String wechat,String organization,String referrer_name,String referrer_tel) {
				Referrer referrer = new Referrer(name,phone,wechat,organization,referrer_name,referrer_tel);
				return appServicenew.addReferrer(referrer);
			}
			
	//查询招生老师对应的学生和学生考勤
	@RequestMapping("/getStudentCheckingInByUserId")
	@ResponseBody
	@ApiOperation(value = "查询招生老师对应的学生和学生考勤", httpMethod = "POST", notes = "添加推荐人信息及相关信息")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query", name = "token", value = "TOKEN", required = true, dataType = "String")
	})
	public ServerResponse getStudentCheckingInByUserId(String token) throws Exception {
		return appServicenew.getStudentCheckingInByUserId(token);
	}
/**
 * 根据用户的id查找学生信息	
 */
			@RequestMapping("/getTokenIdFindStudent")
			@ResponseBody
			@ApiOperation(value = "已登录的招生老师下的学生列表", httpMethod = "POST", notes = "根据已登录的招生老师 id 获取招生老师下的学生信息")
			@ApiImplicitParams({
				@ApiImplicitParam(paramType="query", name = "token", value = "TOKEN", required = true, dataType = "String"),
			})			
			public ServerResponse getTokenIdFindStudent(String token) {
				System.out.println("Controller");
				return appServicenew.getTokenIdFindStudent(token);
			}	
			// 学生姓名模糊查询
			@RequestMapping("/likeStuName")
			@ResponseBody
			@ApiOperation(value = "学生姓名模糊查询", httpMethod = "POST", notes = "学生姓名模糊查询")
			@ApiImplicitParams({
				@ApiImplicitParam(paramType = "query", name = "token", value = "TOKEN", required = true, dataType = "String"),
				@ApiImplicitParam(paramType = "query", name = "stuName", value = "学生姓名", required = true, dataType = "String") })
			public ServerResponse likeStuName(String stuName,String token) {
				return appServicenew.likeStuName(stuName,token);

			}

}
