package cn.deepcoding.service;



import java.util.HashMap;
import java.util.List;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface ProxyCommissionDetailsService {
		//通过姓名和身份证号查出某个招生老师的id
		public PageData findByName(String name,String cid,String date,Page page);
		public Integer findId(String name,String cid);
		public ProxyTeacher findById(Integer id);
		public HashMap<Integer, Double> hasNotPaid();//所有未缴够学费的学生对应的招生老师未获得的提成
		public PageData createWage(Page page);
		public List<ProxyTeacherAmount> export(String name,String cid,String date,Page page);
		public List<ProxyTeacherAmount> getTree(String name, String cid, String yearmonth);
		public Integer findParentId(Integer id);
}
