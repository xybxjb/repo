package cn.deepcoding.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import cn.deepcoding.model.Fee;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;



public interface ProxyCommissionService {
	//查询当月缴纳学费总金额
	public Double totalAmount(Date date);
	public Integer commissionno();//查询未缴纳学费总金额
	public HashMap<Integer, Double> everyStudent(Date date);//每个招生老师的提成
	public String teacherPoint(Integer id);//查找老师点位
	public Integer stuAmount(Integer id);//查询直接招生老师ID
	public PageData findNoParent(Page page);//寻找没有上级老师的招生老师
	public void save(Fee fee);

	/*public List<ProxyTeacherAmount> abc(Page page);*/
	public PageData history(String name,Date starttime,Date endtime,Page page);


	public List<ProxyTeacherAmount> export(Page page);//导入excel


}
