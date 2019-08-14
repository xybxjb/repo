package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.AreaDao;
import cn.deepcoding.model.Area;
import cn.deepcoding.service.AreaService;

@Service

@Transactional
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	public List<Map> findArea(){
		
		List<Area> areas = areaDao.getAll();	

		List list = new ArrayList<>();
		for(Area area:areas){
			if(area.getPerentCode().equals("0")){
				Map<String,Object> map = new HashMap<>();
				map.put("code", area.getCode());
				map.put("name", area.getName());
				map.put("perentCode", area.getPerentCode());
				map.put("children", getChildren(areas,area.getCode()));
				list.add(map);

			}
		}
//		System.out.println(list);
		return list;
	}

	public List<Object> getChildren(List<Area> areas,String code){
		List<Object> list = new ArrayList<>();
		for(Area area:areas){
			if(area.getPerentCode().equals(code)){
				Map<String,Object> map = new HashMap<>();
				map.put("code", area.getCode());
				map.put("name", area.getName());
				map.put("perentCode", area.getPerentCode());
				map.put("children", getChildren(areas,area.getCode()));
				list.add(map);
			}
		}
		
		return list;
	}
	
}
