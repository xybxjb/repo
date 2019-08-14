package cn.deepcoding.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.ProxyTeacherExpense;
import cn.deepcoding.model.User;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyTeacherAuditingService;

@Controller
@RequestMapping("/proxyTeacherAuditing")
public class ProxyTeacherAuditingController {
	
	@Autowired
	ProxyTeacherAuditingService proxyTeacherAuditingService;
	
	@RequiresPermissions("proxyTeacherAuditing:proxyTeacherAuditing")
	@RequestMapping("/proxyTeacherAuditing")
	public String index(){
		return "proxyTeacherAuditing/proxyTeacherAuditing";
	}
	
	
	@RequestMapping("/ListAll")
	@ResponseBody
	public PageData ListAll(String name,String tel,String idCard,String startTime,String endTime, Page page) throws ParseException{
		Date starttime=null;
		Date endtime=null;	
		System.out.println(startTime+"--"+endTime);
		if(startTime!=null &startTime!="" & endTime !=null & endTime!=""){
			starttime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(startTime);
			endtime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endTime);
		}
		
		return proxyTeacherAuditingService.ListAll(name,tel,idCard,starttime,endtime,page);
		
	}
	@RequestMapping("/getById")
	@ResponseBody
	public ProxyTeacherExpense getById(Integer id){
		return proxyTeacherAuditingService.getById(id);
	}
	
	@RequestMapping("/save1")
	public String save1(Integer id,String auditTime,HttpServletRequest request){
		String auditState="2";
		User user=(User)request.getSession().getAttribute("user");
		String auditor=user.getName();
		proxyTeacherAuditingService.saveTwo(id, auditState, auditor, auditTime);
		proxyTeacherAuditingService.saveOne(id,auditState,auditTime);
		return "proxyTeacherAuditing/proxyTeacherAuditing";
	}
	@RequiresPermissions("proxyTeacherAuditing:proxyTeacherAuditing1")
	@RequestMapping("/proxyTeacherAuditing1")
	public String index1(){
		return "proxyTeacherAuditing/proxyTeacherAuditing1";
	}
	@RequestMapping("/waitToSubmit")
	@ResponseBody
	public PageData find1(String name,String tel,String idCard,String startTime,String endTime,Page page) throws ParseException{
		Date starttime=null;
		Date endtime=null;	
//		Date now=new Date();
//		Date start=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2018-01-01 00:00:00");
		System.out.println(startTime+"--"+endTime);
//		if((startTime!=null&&startTime.trim()!="") & (endTime==null|endTime.trim()!="")){
//			starttime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(startTime);
//			endtime=now;
//		}
//		if(startTime==null & endTime!=null){
//			starttime=start;
//			endtime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endTime);
//		}
		if(startTime!=null &startTime!="" & endTime !=null & endTime!=""){
			starttime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(startTime);
			endtime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endTime);
		}
		return proxyTeacherAuditingService.waitToSubmit(name,tel,idCard,starttime,endtime,page);
	}
	@RequestMapping("/getByIdWait")
	@ResponseBody
	public ProxyTeacherExpense getByIdWait(Integer id){
		return proxyTeacherAuditingService.getByIdWait(id);
	}
	@RequestMapping("/save2")
	public String save2(Integer id,String auditTime,HttpServletRequest request){
		String auditState="3";
		User user=(User)request.getSession().getAttribute("user");
		String auditor=user.getName();
		proxyTeacherAuditingService.saveOne(id,auditState,auditTime);
		proxyTeacherAuditingService.saveTwo(id,auditState,auditor,auditTime);
		//proxyTeacherAuditingService.update(id,auditor,auditTime,auditState);
		return "proxyTeacherAuditing/proxyTeacherAuditing1";
	}
	@RequestMapping("/save3")
	public String save3(Integer id,String auditTime,HttpServletRequest request){
		String auditState="1";
		User user=(User)request.getSession().getAttribute("user");
		String auditor=user.getName();
		proxyTeacherAuditingService.saveOne(id,auditState,auditTime);
		proxyTeacherAuditingService.saveTwo(id,auditState,auditor,auditTime);
		//proxyTeacherAuditingService.update(id,auditor,auditTime,auditState);
		return "proxyTeacherAuditing/proxyTeacherAuditing1";
	}
	@RequiresPermissions("proxyTeacherAuditing:proxyTeacherAuditing2")
	@RequestMapping("/proxyTeacherAuditing2")
	public String index2(){
		return "proxyTeacherAuditing/proxyTeacherAuditing2";
	}
	
	@RequestMapping("/haveToSubmit")
	@ResponseBody
	public PageData haveToSubmit(String name,String tel,String idCard,String startTime,String endTime,Page page) throws ParseException{
		Date starttime=null;
		Date endtime=null;	
		System.out.println(startTime+"--"+endTime);
		if(startTime!=null &startTime!="" & endTime !=null & endTime!=""){
			starttime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(startTime);
			endtime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endTime);
		}
		return proxyTeacherAuditingService.haveToSubmit(name, tel, idCard, starttime, endtime, page);
	}
	
	@RequestMapping("/getBySubmitId")
	@ResponseBody
	public ProxyTeacherExpense getBySubmitId(Integer id){
		return proxyTeacherAuditingService.getBySubmitId(id);
	}
	
	
}
