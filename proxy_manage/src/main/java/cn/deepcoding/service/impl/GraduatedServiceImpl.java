package cn.deepcoding.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.dao.FeeDueTimeDao;
import cn.deepcoding.dao.GraduatedDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.model.Graduated;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.GraduatedService;
import cn.deepcoding.util.ImagUtils;
import cn.deepcoding.util.SolrUtiles;

@Service
@Transactional
public class GraduatedServiceImpl implements GraduatedService {

	@Autowired
	private GraduatedDao graduatedDao;
	@Autowired
	private SolrServer solrServer;
	@Autowired
	private StudentDao studentDao;

	/**
	 * 查询所有就业信息
	 */
	@Override
	public PageData listAll(Graduated graduated, Page page) {
		// TODO Auto-generated method stub
		List<Graduated> list = graduatedDao.listAll(graduated, page);
		Integer count = graduatedDao.getAllCount();
		PageData pageData = new PageData();
		pageData.setTotal(count);
		pageData.setRows(list);
		return pageData;
	}

	/**
	 * 
	 * 根据ID查看主要文章内容
	 */
	@Override
	public Graduated getGraduatedByid(Graduated graduated) {
		// TODO Auto-generated method stub
		Page page = new Page();
		page.setPage(1);
		page.setRows(10);
		return graduatedDao.listAll(graduated, page).get(0);
	}

	/**
	 * 添加喜报
	 */
	@Override
	public String addGraduated(Graduated graduated) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		graduated.setCreateTime(df.format(new Date()));
		graduated.setPageView(0);
		//将P标签更换
		String newContent = graduated.getContent().replace("<p>", "<p style='text-indent: 0px;'>");
		graduated.setContent(newContent);
		graduatedDao.addGraduated(graduated);
		studentDao.updateFindJob(graduated);
		return SolrUtiles.graduatedAdd(graduated);
	}

	/**
	 * 删除喜报
	 */
	@Override
	public String deleteGraduated(Integer id) {
		graduatedDao.deleteGraduated(id);
		return	SolrUtiles.graduatedDelete(id);

	}

	/**
	 * 
	 * 修改内容
	 */
	@Override
	public String graduatedUpdate(Graduated graduated) {
		graduatedDao.graduatedUpdate(graduated);
		return	SolrUtiles.graduatedUpdate(graduated);
	}


}
