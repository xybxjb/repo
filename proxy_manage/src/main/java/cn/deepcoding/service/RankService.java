package cn.deepcoding.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import cn.deepcoding.model.Rank;

@Repository
public interface RankService {
	public Rank  getById(Integer id);
	public void add(Rank rank);
	public Map list(@Param("ranks") Rank rank,@Param("start") Integer start,@Param("size") Integer size);
	public void delete(Integer id);
	public void update(Rank rank);
	public Rank get(Integer id);
	public List<Rank> getAll();

	
	
}
