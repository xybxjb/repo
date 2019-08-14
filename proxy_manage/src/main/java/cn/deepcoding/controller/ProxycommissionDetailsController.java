package cn.deepcoding.controller;


import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyCommissionDetailsService;
import cn.deepcoding.service.ProxyCommissionService;

@Controller
@RequestMapping("/proxyCommissionDetails1")
public class ProxycommissionDetailsController {
	@Autowired
	private ProxyCommissionDetailsService proxyCommissionDetailsService;
	@Autowired
	private ProxyCommissionService proxyCommissionService;
	String NAME="";
	String CID="";
	String YEARMONTH="";
	/**
	 * 查询跳转页面
	 */
	@RequestMapping("/proxyDeta")    
	public String jump(String name,String cid,String yearmonth,Model model,HttpServletRequest request){
		System.out.println(yearmonth);
		String year=yearmonth.substring(0, 4);
		String month=yearmonth.substring(5);
		NAME=name;
		CID=cid;
		YEARMONTH=yearmonth;
		model.addAttribute("name", name);//返回页面
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM").parse(yearmonth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, Double>  map=proxyCommissionService.everyStudent(date);
		Integer teacherId= proxyCommissionDetailsService.findId(name, cid);
		model.addAttribute("teacherId", teacherId);
		DecimalFormat df = new DecimalFormat("0.00");
		Double money = map.get(teacherId);
		if(money!=null){
			model.addAttribute("money", df.format(money));	
		}else
			model.addAttribute("money","0.00");
		HashMap<Integer, Double> mapNot= proxyCommissionDetailsService.hasNotPaid();
		if(mapNot.get(teacherId)!=null){
			model.addAttribute("hasNotPaid", df.format(mapNot.get(teacherId)));
		}else{
			model.addAttribute("hasNotPaid","0.00");
		}
		request.getSession().setAttribute("year", year);
		request.getSession().setAttribute("month", month);
		return "proxyCommission/proxy_commission_detais";
	}
	
	/**
	 * 
	 * 初始化需要的数据
	 */
	@RequestMapping("/proxyCommissionSkip")
	@ResponseBody
	public PageData skip(Page page){
		String name=NAME;
		String yearmonth=YEARMONTH;
		String cid=CID;
		return proxyCommissionDetailsService.findByName(name,cid,yearmonth,page);
	}
	
	@RequestMapping("/jump2")
	public String jump(Integer id,Model model,HttpServletRequest request){
		Date date=new Date();
		String date1=new SimpleDateFormat("yyyy-MM").format(date);
		ProxyTeacher proxyTeacher=proxyCommissionDetailsService.findById(id);
		NAME=proxyTeacher.getName();
		CID=proxyTeacher.getIdcard();
		YEARMONTH=date1;
		
		String year=date1.substring(0, 4);
		String month=date1.substring(5);
		model.addAttribute("name", proxyTeacher.getName());//返回页面
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("teacherId", id);
		HashMap<Integer, Double> map=proxyCommissionService.everyStudent(date);
		DecimalFormat df = new DecimalFormat("0.00");
		if (map.get(id)!=null) {
			model.addAttribute("money", Math.ceil(map.get(id)));  //---------
		}else
			model.addAttribute("money", "0.00");         //---------
		HashMap<Integer, Double> mapNot= proxyCommissionDetailsService.hasNotPaid();
		if(mapNot.get(id)!=null){
			model.addAttribute("hasNotPaid", df.format(mapNot.get(id)));   //---------
		}else{
			model.addAttribute("hasNotPaid","0.00");      //---------
		}
		request.getSession().setAttribute("year", year);
		request.getSession().setAttribute("month", month);
		//model.addAttribute("id", id);
		return "proxyCommission/proxy_commission_detais";
	}
	/**
	 * 
	 * 连续跳转
	 */
	@RequestMapping("/jump3")
	public String jump3(Integer id,Model model,HttpServletRequest request){
		ProxyTeacher proxyTeacher=proxyCommissionDetailsService.findById(id);
		NAME=proxyTeacher.getName();
		CID=proxyTeacher.getIdcard();
		String year=YEARMONTH.substring(0, 4);
		String month=YEARMONTH.substring(5);
		model.addAttribute("name", proxyTeacher.getName());//返回页面
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("teacherId", id);
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM").parse(YEARMONTH);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, Double> map=proxyCommissionService.everyStudent(date);
		if (map.get(id)!=null) {
			model.addAttribute("money", Math.ceil(map.get(id)));
		}else
			model.addAttribute("money", "0.00");
		DecimalFormat df = new DecimalFormat("0.00");
		HashMap<Integer, Double> mapNot= proxyCommissionDetailsService.hasNotPaid();
		if(mapNot.get(id)!=null){
			model.addAttribute("hasNotPaid", df.format(mapNot.get(id)));
		}else{
			model.addAttribute("hasNotPaid","0.00");
		}
		request.getSession().setAttribute("year", year);
		request.getSession().setAttribute("month", month);
		Integer leaderId=proxyCommissionDetailsService.findParentId(id);
		if(leaderId!=null){
			model.addAttribute("id", leaderId);
		}
		return "proxyCommission/proxy_commission_detais";
	}

	@RequestMapping("/jump4")
	public String jump4(Integer id,Model model,HttpServletRequest request){
		ProxyTeacher proxyTeacher=proxyCommissionDetailsService.findById(id);
		request.getSession().setAttribute("pid", id);
		NAME=proxyTeacher.getName();
		CID=proxyTeacher.getIdcard();
		String year=YEARMONTH.substring(0, 4);
		String month=YEARMONTH.substring(5);
		System.out.println(year+"==="+month);
		model.addAttribute("name", proxyTeacher.getName());//返回页面
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM").parse(YEARMONTH);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, Double> map=proxyCommissionService.everyStudent(date);
		if (map.get(id)!=null) {
			model.addAttribute("money", Math.ceil(map.get(id)));
		}else
			model.addAttribute("money", "0.00");
		DecimalFormat df = new DecimalFormat("0.00");
		HashMap<Integer, Double> mapNot= proxyCommissionDetailsService.hasNotPaid();
		if(mapNot.get(id)!=null){
			model.addAttribute("hasNotPaid", df.format(mapNot.get(id)));
		}else{
			model.addAttribute("hasNotPaid","0.00");
		}
		request.getSession().setAttribute("year", year);
		request.getSession().setAttribute("month", month);
		return "proxyCommission/student_details";
	}
	@RequestMapping("/jump5")
	public String jump5(Model model){
		Date date=new Date();
		String date1=new SimpleDateFormat("yyyy-MM").format(date);
		YEARMONTH=date1;		
		String year=date1.substring(0, 4);
		String month=date1.substring(5);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "proxyCommission/wage";
	}
	@RequestMapping("/proxyTeacherWage")
	@ResponseBody
	public PageData createWage(Page page){
		return proxyCommissionDetailsService.createWage(page);
	}
	@RequestMapping("/export")
	@ResponseBody
	public String export(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String strDirPath = request.getSession().getServletContext().getRealPath("/");
		Page page = new Page();
		page.setPage(1);
		page.setRows(10000);
		Date date=new Date();
		String date1=new SimpleDateFormat("yyyy-MM").format(date);
		date1=YEARMONTH;
		String year=date1.substring(0, 4);
		String month=date1.substring(5);
		String name = NAME;
		String cid = CID;
		List<ProxyTeacherAmount> lists = proxyCommissionDetailsService.export(name,cid,date1,page);
		//创建 HSSFWorkbook 对象( excel 的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("提成表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);		
		//设置单元格内容
		cell.setCellValue(name+"老师名下所有招生老师"+year+"年"+month+"月提成详情");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		//在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		 //创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("姓名");
		row2.createCell(1).setCellValue("提成总金额");
		row2.createCell(2).setCellValue("区域地址");
		row2.createCell(3).setCellValue("联系方式");
		row2.createCell(4).setCellValue("级别");
		row2.createCell(5).setCellValue("点位");
		for(int i=3;i<lists.size()+3;i++){
			HSSFRow row = sheet.createRow(i-1);
			row.createCell(0).setCellValue(lists.get(i-3).getName());
			row.createCell(1).setCellValue(lists.get(i-3).getAmount());
			row.createCell(2).setCellValue(lists.get(i-3).getAddress());
			row.createCell(3).setCellValue(lists.get(i-3).getTel());
			row.createCell(4).setCellValue(lists.get(i-3).getRanks().getName());
			row.createCell(5).setCellValue(lists.get(i-3).getPoint());
		}
		String uuid=UUID.randomUUID().toString().replace("-", "").toUpperCase();
		FileOutputStream fos = new FileOutputStream(strDirPath+"static/xls/"+uuid+".xls");
		wb.write(fos);
		fos.flush();
		fos.close();
		wb.close();
		return "static/xls/"+uuid+".xls";
	}
	
	
	
	@RequestMapping("/proxyCommissionTree")
	@ResponseBody
	public List<ProxyTeacherAmount> getIndexTree(){
		String name=NAME;
		String yearmonth=YEARMONTH;
		String cid=CID;
		return proxyCommissionDetailsService.getTree(name, cid, yearmonth);
	}
	
	@RequestMapping("/jump6")
	public String jump6(Integer id,Model model,HttpServletRequest request){
		Integer id2=proxyCommissionDetailsService.findParentId(id);
		ProxyTeacher proxyTeacher=proxyCommissionDetailsService.findById(id);
		if(proxyTeacher!=null){
			NAME=proxyTeacher.getName();
			CID=proxyTeacher.getIdcard();
			String year=YEARMONTH.substring(0, 4);
			String month=YEARMONTH.substring(5);
			model.addAttribute("name", proxyTeacher.getName());//返回页面
			model.addAttribute("year", year);
			model.addAttribute("month", month);
			model.addAttribute("teacherId", id);
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM").parse(YEARMONTH);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<Integer, Double> map=proxyCommissionService.everyStudent(date);
			if (map.get(id)!=null) {
				model.addAttribute("money", Math.ceil(map.get(id)));
			}else
				model.addAttribute("money", "0.00");
			DecimalFormat df = new DecimalFormat("0.00");
			HashMap<Integer, Double> mapNot= proxyCommissionDetailsService.hasNotPaid();
			if(mapNot.get(id)!=null){
				model.addAttribute("hasNotPaid", df.format(mapNot.get(id)));
			}else{
				model.addAttribute("hasNotPaid","0.00");
			}
			request.getSession().setAttribute("year", year);
			request.getSession().setAttribute("month", month);
			model.addAttribute("id", id2);
			return "proxyCommission/proxy_commission_detais";
		}
		return "proxyCommission/commission";
	}
}
