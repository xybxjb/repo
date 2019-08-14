package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.CheckDormitory;
import cn.deepcoding.model.Dormitory;
import cn.deepcoding.page.Page;

@Repository
public interface CheckDormitoryDao { 
	// 查询查寝所有信息
	List<CheckDormitory> FindCheckDormitoryAll(@Param("checkdormitory")CheckDormitory checkdormitory,@Param("page")Page page);

	//所有分页
	Integer getAllCount(CheckDormitory checkdormitory);

	
	// 添加查寝记录
	void CheckDormitoryAdd(CheckDormitory checkdormitory);

	// 根据ID删除查寝记录
	void CheckDormitoryDelete(Integer id);

	// 根据ID查询查寝信息
	CheckDormitory CheckDormitoryGetById(Integer id);
	// 修改查寝记录
	void CheckDormitoryUpdate(CheckDormitory checkdormitory);

}
