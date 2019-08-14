package cn.deepcoding.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.deepcoding.model.Graduated;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;
import cn.deepcoding.util.ServerResponse;

@Repository
public interface GraduatedDao {
/**
 * 查看所有
 * @param graduated 
 * @param page 
 * */
	List<Graduated> listAll(@Param("graduated")Graduated graduated,@Param("page")Page page);
	/**
	 * 添加喜报
	 * 
	 * */
	void addGraduated(@Param("graduated")Graduated graduated);
	
	/**
	 * 删除喜报
	 * 
	 * */
	void deleteGraduated(Integer id);
	
	/**
	 * 修改喜报
	 * 
	 * */
	void graduatedUpdate(Graduated graduated);

	/**
	 * 查询页数
	 * 
	 * */
	Integer getAllCount();
	
	// 点击修改 点击量
	void updateStudentpageView(Integer id);
	//定时添加 点击量
	void updateStudentpageView2(@Param("random") Integer random,@Param("id")Integer id);
	// 查询就业精英信息
	List<Graduated> getElite();
	/**
	 * 根据就业信息id查询就业信息
	 * @param id
	 * @return
	 */
	Graduated getGraduatedById(Integer id);
	/**
	 * 查询所有的就业信息倒序
	 * @return
	 */
	List<Graduated> getallgraduated();
	/**
	 * 只查询就业信息的id和title
	 * @return
	 */
	List<Graduated> getAllTitle();
	/**
	 * 根据学生id获取就业信息
	 * @param name
	 * @param address
	 * @return
	 */
	
	Graduated getGraduatedByStudentId(Integer id);
	
	
}
