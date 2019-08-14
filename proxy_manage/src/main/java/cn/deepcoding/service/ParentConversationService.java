package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.ParentConversation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface ParentConversationService {
	
	// 查询所有家长谈话记录信息
	public PageData getAll(ParentConversation parentConversation,Page page);
	// 根据 id 查询
	public ParentConversation getById(Integer id);
	// 修改 信息
	public void updateParentConversation(ParentConversation parentConversation);
	// 添加谈话记录
	public void AddParentConversation(ParentConversation parentConversation);
	// 删除谈话记录
	public void deleteParentConversation(Integer id);
	// 获取老师 和咨询老师的姓名
	public List<ParentConversation> getAllName();
}
