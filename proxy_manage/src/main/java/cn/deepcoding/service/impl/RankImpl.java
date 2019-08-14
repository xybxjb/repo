package cn.deepcoding.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.RankDao;
import cn.deepcoding.model.Rank;

import cn.deepcoding.service.RankService;

@Service
@Transactional
public class RankImpl implements RankService {

	@Autowired
	private RankDao rankDao;

	@Override
	public void add(Rank rank) {
		// TODO Auto-generated method stub
		rankDao.add(rank);

	}

	@Override
	public Map list(Rank rank, Integer start, Integer size) {
	
		Integer total = rankDao.getTotal(rank);
		List<Rank> rankss = rankDao.list(rank, start, size);
		Map<String, Object> map = new HashMap<>();
		map.put("total", total);
		map.put("rows", rankss);
		return map;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		rankDao.delete(id);
	}

	@Override
	public void update(Rank rank) {
		// TODO Auto-generated method stub
		rankDao.update(rank);
	}

	@Override
	public Rank get(Integer id) {
		// TODO Auto-generated method stub
		return rankDao.get(id);
	}

	@Override
	public List<Rank> getAll() {
		// TODO Auto-generated method stub
		return rankDao.getAll();
	}

	@Override
	public Rank getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
