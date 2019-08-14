package cn.deepcoding.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.deepcoding.model.Rank;
import cn.deepcoding.model.User;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface RankDao  {
	public Rank  getById(Integer id);
	public void add(Rank rank);
	public List<Rank> list(@Param("ranks") Rank rank,@Param("start") Integer start,@Param("size") Integer size);
	public Integer getTotal(@Param("ranks") Rank rank);
	public void delete(Integer id);
	public Rank get (Integer id);
	public void update(Rank rank);
	public List<Rank> getAll();


}
