package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ParentConversation;
import cn.deepcoding.page.Page;


@Repository
public interface ParentConversationDao {
	
	// 查询所有家长谈话记录信息
	public List<ParentConversation> getAll(@Param("parentConversation")ParentConversation parentConversation,@Param("page")Page page);
	public Integer getAllCount(ParentConversation parentConversation);
	// 根据 id 查询
	public ParentConversation getById(Integer id);
	// 修改 信息
	public void updateParentConversation(ParentConversation parentConversation);
	// 添加谈话记录
	public void AddParentConversation(ParentConversation parentConversation);
	// 删除谈话记录
	public void deleteParentConversation(Integer id);
	
}
