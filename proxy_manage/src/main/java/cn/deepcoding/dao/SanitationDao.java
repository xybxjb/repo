package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.Sanitation;
import cn.deepcoding.page.Page;

@Repository
public interface SanitationDao {

	// 查询所有
	public List<Sanitation> getAll(@Param("sanitation")Sanitation sanitation,@Param("page")Page page);
	public Integer getAllCount(Sanitation sanitation);
	// 根据 id 查询
	public Sanitation getById(Integer id);
	// 根据 月份 宿舍号 查询评比
	public List<Sanitation> getCountA(Sanitation sanitation);
	public List<Sanitation> getCountB(Sanitation sanitation);
	public List<Sanitation> getCountC(Sanitation sanitation);
	// 修改
	public void updateSanitation(Sanitation sanitation);
	// 添加
	public void addSanitation(Sanitation sanitation);
	// 删除
	public void deleteSanitation(Integer id);
}
