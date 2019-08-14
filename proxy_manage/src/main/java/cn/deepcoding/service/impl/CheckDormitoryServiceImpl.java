package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.CheckDormitoryDao;
import cn.deepcoding.dao.DormitoryDao;
import cn.deepcoding.model.CheckDormitory;
import cn.deepcoding.model.Dormitory;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.CheckDormitoryService;

@Service
@Transactional
public class CheckDormitoryServiceImpl implements CheckDormitoryService {
	@Autowired
	private CheckDormitoryDao checkdormitorydao;

	// 查询查寝所有信息
	@Override
	public PageData FindCheckDormitoryAll(CheckDormitory checkdormitory, Page page) {
		// TODO Auto-generated method stub
		PageData pageData = new PageData();
		Integer count = checkdormitorydao.getAllCount(checkdormitory);
		pageData.setTotal(count);
		// 判断宿舍不为空添加
		List<CheckDormitory> listold = checkdormitorydao.FindCheckDormitoryAll(checkdormitory,page);
		List<CheckDormitory> newList = new ArrayList<CheckDormitory>();
		for (CheckDormitory checkDormitory2 : listold) {
			if (checkDormitory2.getDormitory() != null) {
				newList.add(checkDormitory2);
			}
		}
		pageData.setRows(newList);

		return pageData;
	}

	// 添加查寝记录
	@Override
	public String CheckDormitoryAdd(CheckDormitory checkdormitory) {
		// TODO Auto-generated method stub
		CheckDormitory checkDormitory2 = new CheckDormitory();
		checkDormitory2.setCheckdata(checkdormitory.getCheckdata());
		checkDormitory2.setDid(checkdormitory.getDid());
		Page page = new Page();
		page.setPage(1);
		page.setRows(10);
		page.setStart(0);
		
		List<CheckDormitory> list = checkdormitorydao.FindCheckDormitoryAll(checkDormitory2,page);
		if (list.size() > 0) {
			return "error";
		}
		checkdormitorydao.CheckDormitoryAdd(checkdormitory);

		return "success";
	}

	// 删除查寝记录
	@Override
	public void CheckDormitoryDelete(CheckDormitory checkdormitory) {
		// TODO Auto-generated method stub
		Integer id = checkdormitory.getId();
		checkdormitorydao.CheckDormitoryDelete(id);

	}

	// 根据ID查询查寝信息
	@Override
	public CheckDormitory CheckDormitoryGetById(CheckDormitory checkdormitory) {
		// TODO Auto-generated method stub
		Integer id = checkdormitory.getId();

		return checkdormitorydao.CheckDormitoryGetById(id);

	}

	// 修改查寝记录
	@Override
	public String CheckDormitoryUpdate(CheckDormitory checkdormitory) {
		// TODO Auto-generated method stub
		CheckDormitory checkDormitory2 = new CheckDormitory();
		checkDormitory2.setCheckdata(checkdormitory.getCheckdata());
		checkDormitory2.setDid(checkdormitory.getDid());
		
		Page page = new Page();
		page.setPage(1);
		page.setRows(10);
		page.setStart(0);
		
		
		List<CheckDormitory> list = checkdormitorydao.FindCheckDormitoryAll(checkDormitory2,page);
		if (list.size() > 0) {
			return "error";
		}

		checkdormitorydao.CheckDormitoryUpdate(checkdormitory);

		return "success";
	}

}
