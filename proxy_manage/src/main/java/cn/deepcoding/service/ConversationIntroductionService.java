package cn.deepcoding.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.ConversationIntroduction;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface ConversationIntroductionService {

	List<ConversationIntroduction> getByNameAndTime(@Param("conversationIntroduction")ConversationIntroduction conversationIntroduction);

}
