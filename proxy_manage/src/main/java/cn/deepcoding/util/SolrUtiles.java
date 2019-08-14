package cn.deepcoding.util;

import javax.annotation.PostConstruct;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepcoding.model.Graduated;

/**
 * 
 * solr工具类
 */
@Component
public class SolrUtiles {
	@Autowired
	private  SolrServer solrServerold;
	
	private  static SolrServer solrServer;
	@PostConstruct
	public void init(){
		solrServer=solrServerold;
		
	}
	public static String graduatedAdd(Graduated graduated) {
		
		try {
			// 创建一个文档对象
			SolrInputDocument document = new SolrInputDocument();
			// 向文档对象中添加域
			document.addField("id", graduated.getId());
			document.addField("graduated_title",graduated.getTitle());
			document.addField("graduated_time",graduated.getGraduatedTime());
			// 根据主键删除索引库
			// solrServer.deleteById("1");
			// 把文档写入索引库
			solrServer.add(document);
			// 提交
			solrServer.commit();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		

	}
public static String graduatedUpdate(Graduated graduated) {
		
		try {
			// 根据主键删除索引库
		     solrServer.deleteById(graduated.getId().toString());
			// 创建一个文档对象
			SolrInputDocument document = new SolrInputDocument();
			// 向文档对象中添加域
			document.addField("id", graduated.getId());
			document.addField("graduated_title",graduated.getTitle());
			document.addField("graduated_time",graduated.getGraduatedTime());
			// 把文档写入索引库
			solrServer.add(document);
			// 提交
			solrServer.commit();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		

	}
public static String graduatedDelete(Integer id) {
	
	try {
		// 根据主键删除索引库
	    solrServer.deleteById(id.toString());
		solrServer.commit();
		return "success";
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
	

}
}
