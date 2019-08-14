package cn.deepcoding.service;

import java.util.Map;

import cn.deepcoding.model.Sanitation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface SanitationService {
	
	// 查询所有
	public PageData getAll(Sanitation sanitation,Page page);
	// 根据 id 查询
	public Sanitation getById(Integer id);
	// 根据 月份 宿舍号 查询评比
	public Map<String, Integer> getCount(Sanitation sanitation);
	// 修改
	public void updateSanitation(Sanitation sanitation);
	// 添加
	public void addSanitation(Sanitation sanitation);
	// 删除
	public void deleteSanitation(Integer id);
}	
