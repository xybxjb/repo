package cn.deepcoding.dao;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.Code;
import cn.deepcoding.model.Graduated;
import cn.deepcoding.model.GraduatedResult;

@Repository
public class GraduatedSearch {

	@Autowired
	private SolrServer solrServer;
	@Autowired
	private GraduatedDao graduatedDao;
	public GraduatedResult srarch(SolrQuery query) throws Exception{
		//根据query查询索引库
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		//取查询结果的总记录数
		long numFound = solrDocumentList.getNumFound();
		//创建-返回值--总记录数
		GraduatedResult graduatedResult=new GraduatedResult();
		graduatedResult.setRecordCount(numFound);
		//取商品列表，需要取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		List<Graduated> graduateds = new ArrayList<Graduated>();
		for (SolrDocument solrDocument : solrDocumentList) {
			
			Graduated graduated=new Graduated();
			//将查询到的结果封装到集合中
			String ids=(String)solrDocument.get("id");
			graduated.setId(Integer.parseInt(ids));
			//取高亮显示
			String title="";
			String graduatedTime="";
			List<String> list = highlighting.get(solrDocument.get("id")).get("graduated_title");
			if(list!=null && list.size()>0){
				title=list.get(0);
				graduatedTime=(String) solrDocument.get("graduated_time");
			}
			
			graduated.setTitle(title);
			graduated.setGraduatedTime(graduatedTime);
			graduateds.add(graduated);
		}
		graduatedResult.setGraduateds(graduateds);
		//返回结果
		
		return graduatedResult;
	}

	//维护索引库
	public Code updateSolr() {
		 Code code=new Code();
		//删除索引库
			try {
				solrServer.deleteById("*");
			} catch (SolrServerException | IOException e1) {
				code.setStatus("500");
				 code.setMessage("索引库数据删除失败");
			}
			try {
				//查询所有的数据
				List<Graduated> graduates=graduatedDao.getAllTitle();
				//遍历商品信息
				for (Graduated graduated : graduates) {
				//创建文档对象
				SolrInputDocument document=new SolrInputDocument();
				document.addField("id",graduated.getId());
				document.addField("graduated_title", graduated.getTitle());
				document.addField("graduated_time",graduated.getGraduatedTime());
				//把文档对象写入索引库
				solrServer.add(document);
				}
				//提交事务
				solrServer.commit();
			} catch (Exception e) {
				
				 code.setStatus("500");
				 code.setMessage("数据导入到索引库异常");
				 return code;
			}
			
			
	
		code.setStatus("200");
		code.setMessage("数据导入到索引库成功");
		return code;
	}
}
