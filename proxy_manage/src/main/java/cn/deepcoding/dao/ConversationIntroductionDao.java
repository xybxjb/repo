package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.ConversationIntroduction;

public interface ConversationIntroductionDao {

	List<ConversationIntroduction> getByNameAndTime(@Param("conversationIntroduction")ConversationIntroduction conversationIntroduction);

	Integer getAllCount(ConversationIntroduction conversationIntroduction);

}