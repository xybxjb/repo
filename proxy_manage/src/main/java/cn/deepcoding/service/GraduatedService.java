package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.Graduated;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface GraduatedService {
	/**
	 * 查看所有就业信息
	 * @param graduated 
	 */
	PageData listAll(Graduated graduated,Page page);
	/**
	 * 
	 * 根据ID查看主要文章内容
	 */
	Graduated getGraduatedByid(Graduated graduated);
	
	/**
	 * 添加喜报
	 */
	String addGraduated(Graduated graduated);
	/**
	 * 删除喜报
	 * 
	 * */
	String deleteGraduated(Integer id);
	/**
	 * 
	 * 修改内容
	 * */
	String graduatedUpdate(Graduated graduated);
	
}
