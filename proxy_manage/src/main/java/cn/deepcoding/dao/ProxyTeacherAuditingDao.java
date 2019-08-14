package cn.deepcoding.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherExpense;
import cn.deepcoding.model.StudentTeacherAuditing;
import cn.deepcoding.model.Transportation;
import cn.deepcoding.model.VisitFee;
import cn.deepcoding.page.Page;

@Repository
public interface ProxyTeacherAuditingDao {

	public List<ProxyTeacherExpense> ListAll(@Param("name")String name, @Param("tel")String tel,
			@Param("idCard")String idCard, @Param("starttime")Date starttime, 
			@Param("endtime")Date endtime, @Param("page")Page page);//未审核和未通过审核
	public Integer getTotal(@Param("name")String name, @Param("tel")String tel, 
			@Param("idCard")String idCard,@Param("starttime") Date starttime,
			@Param("endtime")Date endtime);
	public Transportation getTransportationById(Integer id);//交通方式
	public ProxyTeacherExpense getById(Integer id);//审核个人
	public void saveOne(@Param("id")Integer id,@Param("auditState")String auditState,@Param("datetime")Date datetime);
	public void saveTwo(@Param("id")Integer id,@Param("auditState")String auditState, 
			@Param("auditor")String auditor, @Param("dateTime")Date dateTime);
	public List<ProxyTeacherExpense> waitToSubmit(@Param("name")String name, @Param("tel")String tel,
			@Param("idCard")String idCard, @Param("starttime")Date starttime, 
			@Param("endtime")Date endtime, @Param("page")Page page);//待报销人员
	public Integer getTotalWaitToSubmit(@Param("name")String name, @Param("tel")String tel, 
			@Param("idCard")String idCard,@Param("starttime") Date starttime,
			@Param("endtime")Date endtime);//待报销人员
	public ProxyTeacherExpense getByIdWait(Integer id);//审核个人
	public void update(@Param("id")Integer id, @Param("auditor")String auditor, 
			@Param("auditTime")String auditTime, @Param("auditState")String auditState);
	public List<ProxyTeacherExpense> haveToSubmit(@Param("name")String name, @Param("tel")String tel,
			@Param("idCard")String idCard, @Param("starttime")Date starttime, 
			@Param("endtime")Date endtime, @Param("page")Page page);
	public Integer haveTotal(@Param("name")String name, @Param("tel")String tel, 
			@Param("idCard")String idCard,@Param("starttime") Date starttime,
			@Param("endtime")Date endtime);
	
	public ProxyTeacher findTeacherId(Integer id);
	public Integer findproxyTeacher(Integer id);
	public List<VisitFee> findVisitFeeByVisitId(Integer id);
	public List<StudentTeacherAuditing> findTeacherAuditingById(Integer id);
    public ProxyTeacherExpense	haveToSubmitById(Integer id);
}