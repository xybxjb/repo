package cn.deepcoding.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.SanitationDao;
import cn.deepcoding.model.Sanitation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.SanitationService;
import cn.deepcoding.util.ImagUtils;
@Service
@Transactional
public class SanitationServiceImpl implements SanitationService {
	
	@Autowired
	private SanitationDao sanitationDao;
	// 查询所有
	@Override
	public PageData getAll(Sanitation sanitation,Page page) {
		List<Sanitation> list = sanitationDao.getAll(sanitation,page);
		Integer allCount = sanitationDao.getAllCount(sanitation);
		PageData pageData = new PageData();
		pageData.setTotal(allCount);
		pageData.setRows(list);
		return pageData;
	}
	// 添加
	@Override
	public void addSanitation(Sanitation sanitation) {
		sanitationDao.addSanitation(sanitation);
		
	}
	// 根据id 查询
	@Override
	public Sanitation getById(Integer id) {
		Sanitation byId = sanitationDao.getById(id);
		// imUurl路径
		String imgUrl = ImagUtils.imgUrl();
		byId.setPic(imgUrl+byId.getPic());
		System.err.println("图片路径："+byId.getPic());
		return byId;
	}
	// 修改
	@Override
	public void updateSanitation(Sanitation sanitation) {
		sanitationDao.updateSanitation(sanitation);
		
	}
	// 删除
	@Override
	public void deleteSanitation(Integer id) {
		sanitationDao.deleteSanitation(id);
		
	}
	// 根据 宿舍号 月份 查询评比
	@Override
	public Map<String, Integer> getCount(Sanitation sanitation) {
		Map<String, Integer> m = new HashMap<String, Integer>();
		List<Sanitation> listA = sanitationDao.getCountA(sanitation);
		List<Sanitation> listB = sanitationDao.getCountB(sanitation);
		List<Sanitation> listC = sanitationDao.getCountC(sanitation);
/*		System.err.println(listA);
		System.err.println(listA.size());
		System.err.println(listB.size());
		System.err.println(listC.size());*/
		m.put("A等级宿舍", listA.size());
		m.put("B等级宿舍", listB.size());
		m.put("C等级宿舍", listC.size());
		return m;
	}
	
}
