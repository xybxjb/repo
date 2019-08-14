package cn.deepcoding.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.deepcoding.dao.ProxyCommissionDao;
import cn.deepcoding.model.ProxyTeaMonth;
import cn.deepcoding.model.ProxyTeacherHistory;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyTecherStudentService;;

@Controller
@RequestMapping("/proxyteacherstudent")
public class ProxyTecherStudentController {
	
	@Autowired
    private ProxyTecherStudentService proxyTecherStudentService;
	@Autowired
	private ProxyCommissionDao proxyCommissionDao;
	
	@RequestMapping("/index")
	public String index(){
		return  "proxycommission/studentdetails"; 
	}
	
	
    @RequestMapping("/findPage")
    @ResponseBody
    public  PageData find(HttpServletRequest request,Page page){
		
    	
    	String year=(String)request.getSession().getAttribute("year");
    	String month=(String)request.getSession().getAttribute("month");
    	Integer id = (Integer) request.getSession().getAttribute("pid");
		PageData list=proxyTecherStudentService.find(id,year,month,page);
		if(list==null){
			return null;
		}
		return list;
    } 
	@RequestMapping("/get")
	@ResponseBody
	public Student get(Integer id){
		Student s = proxyTecherStudentService.get(id);
		System.out.println(s);
		return s;
	}
	ProxyTeaMonth proxyTeaMonth =new ProxyTeaMonth();
	@RequestMapping("/save")
	@ResponseBody
	public void save(Integer proxyteacherId ,String proxyteacherName,Double commissionAmount,String commissionDate,String remarks,String state){
		String proxyTeacherPoin=proxyCommissionDao.teacherPoint(proxyteacherId);
		Double proxyTeacherPoint=Double.parseDouble(proxyTeacherPoin);
		proxyTeaMonth.setProxyTeacherId(proxyteacherId);
		proxyTeaMonth.setCommissionAmount(commissionAmount);
		proxyTeaMonth.setCommissionPoint(proxyTeacherPoint);
		proxyTeaMonth.setProxyTeacherName(proxyteacherName);
		proxyTeaMonth.setRemarks(remarks);
		proxyTeaMonth.setState(state);
		Long date1=new Long(commissionDate);
		Date date=new Date(date1);
		proxyTeaMonth.setCommissionDate(date);
		System.out.println(proxyTeaMonth.getRemarks());
		proxyTecherStudentService.save(proxyTeaMonth);
		
	}
}
