package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.FindJodPreson;

/**
 * 就业人员dao
 * @author zhaoyihe
 * @date 2019年6月1日
 *
 */

public interface GradutionPresonDao {
	
	List<FindJodPreson> getGradutionPresonList();
	
	//依据地区模糊查询就业人员集合
	List<FindJodPreson> getFindJodPresonByAddress(@Param("addressStr")String addressStr);
	
}	
