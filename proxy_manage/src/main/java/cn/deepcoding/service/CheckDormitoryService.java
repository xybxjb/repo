package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.CheckDormitory;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface CheckDormitoryService {
	// 查询查寝所有信息
	PageData FindCheckDormitoryAll(CheckDormitory checkdormitory,Page page);

	// 添加查寝记录
	String CheckDormitoryAdd(CheckDormitory checkdormitory);

	// 删除查寝记录
	void CheckDormitoryDelete(CheckDormitory checkdormitory);

	// 根据ID查询查寝信息
	CheckDormitory CheckDormitoryGetById(CheckDormitory checkdormitory);

	// 修改查寝记录
	String CheckDormitoryUpdate(CheckDormitory checkdormitory);

}
