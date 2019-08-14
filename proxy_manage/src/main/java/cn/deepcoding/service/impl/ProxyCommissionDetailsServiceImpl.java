package cn.deepcoding.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.ProxyCommissionDao;
import cn.deepcoding.dao.ProxyCommissionDetailsDao;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.model.ProxyTeacherHistory;
import cn.deepcoding.model.ProxyTeacherTree;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyCommissionDetailsService;
import cn.deepcoding.service.ProxyCommissionService;
@Transactional
@Service
public class ProxyCommissionDetailsServiceImpl implements ProxyCommissionDetailsService{

	@Autowired
	private ProxyCommissionDetailsDao proxyCommissionDetailsDao;
	@Autowired
	private ProxyCommissionService proxyCommissionService;
	@Autowired
	private ProxyCommissionDao proxyCommissionDao;
	
	@Override
	public Integer findId(String name, String cid) {
		return proxyCommissionDetailsDao.findByName(name, cid);
	}
	@Override
	public PageData findByName(String name, String cid,String date,Page page) {
		Integer ptid = proxyCommissionDetailsDao.findByName(name, cid);//通过姓名和身份证号查出需要查找的招生老师 id 	
		List<ProxyTeacher> puisneTeachers = new ArrayList<>();	
		Integer parentId= proxyCommissionDao.findParentTeacherId(ptid);
		puisneTeachers=childProxyTeacher(puisneTeachers,parentId);//通过招生老师 id 拿到他名下的所有招生老师的信息集合
		List<Integer> list =new ArrayList<>();
		for(ProxyTeacher pt:puisneTeachers){
			list.add(pt.getId());                            //遍历下级集合 将下级招生老师的 id 放入 list 集合中
		}
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM").parse(date);//将String类型转换成 date 类型
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, Double>  map=proxyCommissionService.everyStudent(date1);  //通过日期拿到规定日期所有老师提成集合		

		List<ProxyTeacherAmount> list1=proxyCommissionDetailsDao.findbyid(list,page); //通过 id 查出下级招生老师的详细信息
		for(ProxyTeacherAmount pta:list1){
			if(map.get(pta.getId())==null){
			pta.setAmount(0.00);                                      //遍历每个对象 将提成金额赋值
			}else
				pta.setAmount(Math.ceil(map.get(pta.getId())));
		}
		PageData pd=new PageData();
		pd.setRows(list1);
		int rows=proxyCommissionDetailsDao.gettotal1(list);
		pd.setTotal(rows);
		return pd;
	}
	
	public List<ProxyTeacher> childProxyTeacher(List<ProxyTeacher> puisneTeachers,Integer ptid){
		List<ProxyTeacher> list = new ArrayList<>();
		if (ptid!=null){
			list=proxyCommissionDetailsDao.getParentProxyTeacherId(ptid);
			if(list!=null&&list.size()>0){
				for(ProxyTeacher teacher:list){
					puisneTeachers.add(teacher);
					childProxyTeacher(puisneTeachers,teacher.getId());
				}
			}else{
				return null;
			}
		}		
		return puisneTeachers;
	}
	
	@Override
	public ProxyTeacher findById(Integer id) {
		// TODO Auto-generated method stub
		return proxyCommissionDetailsDao.findById(id);
	}
	//所有未缴够学费的学生对应的招生老师未获得的提成
	public HashMap<Integer, Double> hasNotPaid(){
		HashMap<Integer, Double> map = new HashMap<>();
	    List<Student> students=	proxyCommissionDetailsDao.hasNotPaid();//未交学费的学生
		for(Student student :students){
			Integer directTeacher = proxyCommissionDao.stuAmount(student.getId());//直接招生老师ID
			int notPaid=(int) student.getUnpaiTuition();//学生未缴清的学费数
			Double point = Double.parseDouble(proxyCommissionDao.teacherPoint(directTeacher))/100;//点位
			if (!map.containsKey(directTeacher)) {
				map.put(directTeacher, notPaid * point);
			} else {
				map.put(directTeacher, map.get(directTeacher) + notPaid * point);
			}
			Integer leader = proxyCommissionDao.findParentTeacherId(directTeacher);// 查找上级ID
			while (leader != null) {
				Double ab = Double.parseDouble(proxyCommissionDao.teacherPoint(directTeacher))/100;// 招生老师点位
				if (leader == 0) {
					break;
				}
				Double ab1 = Double.parseDouble(proxyCommissionDao.teacherPoint(leader))/100;// 上级点位
				Double point1 = (ab1 - ab);// 点位差
				if (!map.containsKey(leader)) {
					map.put(leader, point1 * notPaid);
				} else {
					map.put(leader, map.get(leader) + point1 * notPaid);
				}
				directTeacher = leader;
				leader = proxyCommissionDao.findParentTeacherId(leader); 
				
			}
		}
		return map;
	}
	@Override
	public PageData createWage(Page page) {
		// TODO Auto-generated method stub
		List<ProxyTeacherHistory> list = proxyCommissionDetailsDao.createWage(page);
		int total = proxyCommissionDetailsDao.getTotal();
		PageData pd = new PageData();
		pd.setTotal(total);
		pd.setRows(list);
		return pd;
	}
	@Override
	public List<ProxyTeacherAmount> export(String name, String cid, String date, Page page) {
		// TODO Auto-generated method stub
		Integer ptid = proxyCommissionDetailsDao.findByName(name, cid);//通过姓名和身份证号查出需要查找的招生老师 id 	
		List<ProxyTeacher> puisneTeachers = new ArrayList<>();	
		puisneTeachers=childProxyTeacher(puisneTeachers,ptid);//通过招生老师 id 拿到他名下的所有招生老师的信息集合
		List<Integer> list =new ArrayList<>();
		for(ProxyTeacher pt:puisneTeachers){
			list.add(pt.getId());                            //遍历下级集合 将下级招生老师的 id 放入 list 集合中
		}
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM").parse(date);//将String类型转换成 date 类型
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, Double>  map=proxyCommissionService.everyStudent(date1);  //通过日期拿到规定日期所有老师提成集合		

		List<ProxyTeacherAmount> list1=proxyCommissionDetailsDao.findbyid(list,page); //通过 id 查出下级招生老师的详细信息
		for(ProxyTeacherAmount pta:list1){
			if(map.get(pta.getId())==null){
			pta.setAmount(0.00);                                      //遍历每个对象 将提成金额赋值
			}else
				pta.setAmount(Math.ceil(map.get(pta.getId())));
		}
		return list1;
	}
	HashMap<Integer, Double>  map=null;
	@Override
	public List<ProxyTeacherAmount> getTree(String name, String cid, String date) {
		List<ProxyTeacherAmount> list = new ArrayList<ProxyTeacherAmount>();
		ProxyTeacherAmount proxyTeacherAmount = proxyCommissionDetailsDao.findByName2(name, cid);
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM").parse(date);//将String类型转换成 date 类型
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map=proxyCommissionService.everyStudent(date1);  //通过日期拿到规定日期所有老师提成集合		
		if(map.get(proxyTeacherAmount.getId())==null){
			proxyTeacherAmount.setAmount(0.00);
		}else{
			proxyTeacherAmount.setAmount(map.get(proxyTeacherAmount.getId()));
		}
		ProxyTeacherAmount childTree = getChildTree(proxyTeacherAmount);
		list.add(childTree);
		return list;
		
	}
	public ProxyTeacherAmount getChildTree(ProxyTeacherAmount proxyTeacherAmount) {
		if(proxyCommissionDetailsDao.getParentProxyTeacherId2(proxyTeacherAmount.getId())==null){			
			return proxyTeacherAmount;
		}else{
			List<ProxyTeacherAmount> child =proxyCommissionDetailsDao.getParentProxyTeacherId2(proxyTeacherAmount.getId());
			for(ProxyTeacherAmount node : child){
				if(map.get(node.getId())==null){
					node.setAmount(0.00);
				}else{
					node.setAmount(map.get(node.getId()));
				}
				proxyTeacherAmount.getChildren().add(node);
				getChildTree(node);		
			}
		}
		return proxyTeacherAmount;
	}
	
	public Integer findParentId(Integer id){
		return proxyCommissionDao.findParentTeacherId(id);
	}
}
