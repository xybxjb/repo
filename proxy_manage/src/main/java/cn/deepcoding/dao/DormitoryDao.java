package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.Dormitory;
import cn.deepcoding.model.StudentConversation;
import cn.deepcoding.page.Page;

@Repository
public interface DormitoryDao {
	// 查询所有
	List<Dormitory> FindDormitoryAll(@Param("dormitory") Dormitory dormitory, @Param("page") Page page);

	// 查询所有页数
	Integer getAllCount(Dormitory dormitory);

	// 增加宿舍
	void DormitoryAdd(Dormitory dormitory);

	// 更改宿舍信息
	void DormitoryUpdate(Dormitory dormitory);

	// 通过ID查询宿舍信息
	Dormitory GetById(Integer id);

	// 获取 宿舍号接口
	List<Dormitory> FindDormitorynumber();
	
	Dormitory GetStudentId(Integer id);
	
}
