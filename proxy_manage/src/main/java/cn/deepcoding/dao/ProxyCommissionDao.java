package cn.deepcoding.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ProxyTeaMonth;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.model.ProxyTeacherHistory;
import cn.deepcoding.model.Rank;
import cn.deepcoding.model.StudentBean;
import cn.deepcoding.page.Page;

@Repository
public interface ProxyCommissionDao {
	//查询当月缴纳学费总金额
	public Double totalAmount(@Param("date")String date);
	
	public Integer commissionNo();//查询未缴纳学费总金额

	public List<StudentBean> everyStudent(Date date);//每个学生的缴费总数
	
	public Integer stuAmount(Integer id);//按学生ID找出每个学生的直接招生老师ID
	
	public String teacherPoint(Integer id);//老师点位
	public Integer findParentTeacherId(Integer id);	//查找上级老师ID	
	
	public Rank selectRankId(Integer id);//查找级别
	
	public List<ProxyTeacherAmount> findNoParent(@Param("page")Page page);//寻找没有上级的招生老师
	public Integer getTotal();
	public ProxyTeacher findById(Integer id);
	public void saveHistory(ProxyTeacherHistory proxyTeacherHistory);
	public List<ProxyTeacherHistory> selectEveryProxyTeacherAmount(Date date); //每个学生的提成
	public List<ProxyTeaMonth> history(@Param("name")String name,@Param("starttime")Date starttime,@Param("endtime")Date endTime,@Param("page")Page page);
	public Integer historyTotal(@Param("name")String name,@Param("starttime")Date starttime,@Param("endtime")Date endTime);
}
