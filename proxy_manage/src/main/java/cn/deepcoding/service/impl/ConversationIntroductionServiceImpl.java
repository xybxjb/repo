package cn.deepcoding.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.deepcoding.dao.ConversationIntroductionDao;
import cn.deepcoding.model.ConversationIntroduction;
import cn.deepcoding.service.ConversationIntroductionService;

@Service
@Transactional
public class ConversationIntroductionServiceImpl implements ConversationIntroductionService{

	@Autowired
	private ConversationIntroductionDao conversationIntroductionDao;

	@Override
	public List<ConversationIntroduction> getByNameAndTime(@Param("conversationIntroduction")ConversationIntroduction conversationIntroduction) {
		return conversationIntroductionDao.getByNameAndTime(conversationIntroduction);
	}

}
