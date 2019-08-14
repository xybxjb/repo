package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Referrer;

public interface ReferrerDao {
	void addReferrer(@Param("referrer")Referrer referrer);

	List<Referrer> getAll(@Param("referrer")Referrer referrer, @Param("start")Integer start, @Param("rows")Integer rows);
}
