package cn.deepcoding.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


import cn.deepcoding.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.util.ServerResponse;

public interface AppServiceNew {

	// 根据 招生老师 id 查询自己信息
	ServerResponse getProxyTeacher(String token);

	// 根据 招生老师 id 查询上级信息
	ServerResponse getParentProxyTeacher(String token);

	// 根据招生老师 id 查询下级 信息
	ServerResponse getSublevelProxyTeacher(String token);

	// 根据招生老师 id 查询学生信息
	ServerResponse getStudentByProxyTeacherId2(Integer id,String token, Page page);

	// 根据学生 id 查询学生信息
	ServerResponse getStudentById3(Integer id, String token);

	// 根据学生 id 查询学生缴费信息
	ServerResponse getFeeByStudentId2(Integer id, String token);

	// 根据学生 id 查询 钉钉唯一标识符
	ServerResponse getAttenceByStudentId1(Integer id, String token) throws Exception;
	// 查询学生最近一个月的明细表
	ServerResponse getAttenceByStudent1(Integer id, String token);
	// 查询学生最近三个月的明细表
	ServerResponse getAttenceByStudent2(Integer id, String token);
	// 查询学生最近六个月的明细表
	ServerResponse getAttenceByStudent3(Integer id, String token);
	// 根据 学生id 查询考试名称
	ServerResponse getExamByStudentId(Integer id, String token);
	List<Attence> getAttenceByStudentId(Integer id);
	//根据地址和姓名查询就业信息
	GraduatedResult getGraduatedByaddress(String keyword,int page, int rows);
	//根据id查询就业信息
	ServerResponse getGraduatedById(Integer id);
	//查询所有的就业信息倒序
	ServerResponse getallgraduated();
	// 根据学生id查询学生谈话记录
	ServerResponse getStudentConversationById(Integer id, String token);
	// 根据学生id 查询 谈话记录详细表（图片）
	 ServerResponse getStudentConversationPicById(Integer id, String token);
	
	// 点击修改 点击量
	void updateStudentpageView(Integer id);
	// 每晚 两点定时更新 点击量
	void updateStudentpageView2(Graduated graduated);
	// 查询就业精英信息
	ServerResponse getElite();
	// 根据账号 查询信息
	ServerResponse getByName(String appTel,String appPassword);

	// 发送短信
	ServerResponse getMessage(String tel);
	
	//新发送短信
	ServerResponse sendMassage(String tel);
	
	// 修改密码
	ServerResponse updatePassword(String code,String appTel,String password);
    //维护索引库
	Code updateSolr();
	
	//添加学生和其招生老师
	ServerResponse addStuAndProxyTeacher(String studentName,String phone,String teacherName,String teacherPhone,String remark);
	//添加招生老师的微信信息并绑定
	ServerResponse addProxyTeacherWatchInfo(String openid,String nickname,Integer sex,String city,String country,String province,String appTel,String code);
	//手机号登录
	ServerResponse appUserLogin(String appTel,String code);
	//添加推荐人信息
	ServerResponse addReferrer(Referrer referrer);
	// 学生姓名模糊查询
	ServerResponse likeStuName(String stuName,String token);
	//就业人员信息
	ServerResponse getGradutionPresonList();

	//依据登陆人id匹配自己的学生的1，3，6个月的考勤信息
	ServerResponse getStudentCheckingInByUserId(String token) throws Exception;

	/**
	 * 根据用户的id查找学生信息	
	 */
	ServerResponse getTokenIdFindStudent(String token);

	//根据学生姓名或者地区查询学生就业信息
	ServerResponse getGraduated(String name,String address);
	// 根据学生id查询学生就业信息
	ServerResponse getGraduatedByStudentId(Integer id);

	/**
	 * 根据用户id查找学生学费
	 */
	ServerResponse getTokenIdFindStuFee(String token);

/**
 * 根据用户id模糊查找学生学费
 */
	ServerResponse getTokenIdLikeStuFee(String stuName,String token);

ServerResponse getScoreByStudentId(Integer id, String token);
}
