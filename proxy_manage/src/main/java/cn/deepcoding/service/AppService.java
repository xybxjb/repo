package cn.deepcoding.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import cn.deepcoding.model.AppUsers;
import cn.deepcoding.model.Attence;
import cn.deepcoding.model.AttenceCount;
import cn.deepcoding.model.Code;
import cn.deepcoding.model.Exam;
import cn.deepcoding.model.Fee;
import cn.deepcoding.model.Graduated;
import cn.deepcoding.model.GraduatedResult;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.QueryVo2;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentConversation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface AppService {

	// 根据 招生老师 id 查询自己信息
	ProxyTeacher getProxyTeacher(Integer id);

	// 根据 招生老师 id 查询上级信息
	ProxyTeacher getParentProxyTeacher(Integer id);

	// 根据招生老师 id 查询下级 信息
	List<ProxyTeacher> getSublevelProxyTeacher(Integer id);

	// 根据招生老师 id 查询学生信息
	PageData getStudentByProxyTeacherId2(Integer id, Page page);

	// 根据学生 id 查询学生信息
	Student getStudentById3(Integer id);

	// 根据学生 id 查询学生缴费信息
	List<Fee> getFeeByStudentId2(Integer id);

	// 根据学生 id 查询 钉钉唯一标识符
	List<QueryVo2> getAttenceByStudentId1(Integer id) throws Exception;
	// 查询学生最近一个月的明细表
	List<AttenceCount> getAttenceByStudent1(Integer id);
	// 查询学生最近三个月的明细表
	List<AttenceCount> getAttenceByStudent2(Integer id);
	// 查询学生最近六个月的明细表
	List<AttenceCount> getAttenceByStudent3(Integer id);
	// 根据 学生id 查询考试名称
	List<Exam> getExamByStudentId(Integer id);
	List<Attence> getAttenceByStudentId(Integer id);
	//根据地址和姓名查询就业信息
	GraduatedResult getGraduatedByaddress(String keyword,int page, int rows);
	//根据id查询就业信息
	Graduated getGraduatedById(Integer id);
	//查询所有的就业信息倒序
	List<Graduated> getallgraduated();
	// 根据学生id查询学生谈话记录
	List<StudentConversation> getStudentConversationById(Integer id);
	// 根据学生id 查询 谈话记录详细表（图片）
	public String getStudentConversationPicById(Integer id);
	
	// 点击修改 点击量
	void updateStudentpageView(Integer id);
	// 每晚 两点定时更新 点击量
	void updateStudentpageView2(Graduated graduated);
	// 查询就业精英信息
	List<Graduated> getElite();
	// 根据账号 查询信息
	Code getByName(String appTel,String appPassword,String token);
	// 发送短信
	Code getMessage(String tel,HttpServletRequest request);
	// 修改密码
	Code updatePassword(AppUsers appUsers,HttpServletRequest request);
    //维护索引库
	Code updateSolr();
}
