package cn.deepcoding.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.model.ProxyTeacherHistory;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentBean;
import cn.deepcoding.page.Page;

@Repository
public interface ProxyCommissionDetailsDao {
	//通过姓名和身份证号查出某个招生老师的id
	public Integer findByName(@Param("name")String name,@Param("cid")String cid);
	public ProxyTeacher find(@Param("name")String name,@Param("cid")String cid);
	
	//通过招生老师的 id 查出他名下的学生 id
	public List<Integer> findByPtid(Integer ptid);
	
	//通过学生 id 查出 学生缴费总额
	public Integer findBySid(List<Integer> sids);
	
	public List<ProxyTeacherAmount> findbyid(@Param("li")List<Integer> ids ,@Param("page")Page page);//查找对应的对象
	public Integer gettotal1(List<Integer> ids);
	public ProxyTeacher getbyid(Integer id);
	public List<ProxyTeacher> findAll();
	public ProxyTeacher findById(Integer id);

	public List<ProxyTeacher> getParentProxyTeacherId(Integer ptid);
	
	public List<Student> hasNotPaid();//所有未缴清学费的学生信息
	//public ProxyTeacher findProxyTeacher(Integer id); 查找学生的直接招生老师

	public List<ProxyTeacherHistory> createWage(@Param("page")Page page);

	public int getTotal();
	
	public ProxyTeacherAmount findByName2(@Param("name")String name,@Param("cid")String cid);
	public List<ProxyTeacherAmount> getParentProxyTeacherId2(Integer id);
}
