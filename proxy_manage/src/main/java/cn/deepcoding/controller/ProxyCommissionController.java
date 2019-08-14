package cn.deepcoding.controller;


import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyCommissionService;

@Controller
@RequestMapping("/commission")
public class ProxyCommissionController {
		
	@Autowired
	private ProxyCommissionService proxyCommissionService;//调用service层
	//跳转页面,并返回提成总额
	
	@RequiresPermissions("commission:commissionSkip")
	@RequestMapping("/commissionSkip")
	public String skip(Model model){
		Double amount=0.00;
		HashMap<Integer, Double> map=proxyCommissionService.everyStudent(new Date());
		Set<Integer> set= map.keySet();
		Iterator<Integer> iterator=set.iterator();
		while(iterator.hasNext()){
			amount+=map.get(iterator.next());
		}
		DecimalFormat df =  new DecimalFormat("0.00");
		model.addAttribute("money2", df.format(amount));
		return "proxyCommission/commission";

	}
	//当月学费总额
	@RequestMapping("/commissionTatal")
	@ResponseBody
	public String totalAmount(){
		Double totalAmount=	proxyCommissionService.totalAmount(new Date());
		DecimalFormat df = new DecimalFormat("0.00");
		if(totalAmount==null){
			totalAmount=0.00;
		}
		return df.format(totalAmount);
	}
	//未缴纳学费总金额
	@RequestMapping("/commissionNo")
	@ResponseBody
	public Integer totalAmount1(){ 
		Integer a= proxyCommissionService.commissionno();
		if(a==null){
			return 0;
		}
		return a;
	}
	//团队长信息Json
	@RequestMapping("/commiss")
	@ResponseBody
	public PageData everyStudent(Page page){
		return proxyCommissionService.findNoParent(page);
	}

	
	//  导出excel
	@RequestMapping("/print1")
	@ResponseBody
	public String export(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		String strDirPath = request.getSession().getServletContext().getRealPath("/"); 
		Page page=new Page();
		page.setPage(1);page.setRows(10000);
		List<ProxyTeacherAmount> lists=(proxyCommissionService).export(page);

		HSSFWorkbook wb=new HSSFWorkbook();//创建HSSFWorkbook对象
		HSSFSheet sheet=wb.createSheet("提成表");//创建HSSFSheet对象
		HSSFRow row1=sheet.createRow(0);//创建HSSFRow对象
		HSSFCell cell=row1.createCell(0);//创建HSSFCell对象
		Date date=new Date();
		String sdf=new SimpleDateFormat("yyyy-MM-dd").format(date);
		String year=sdf.substring(0, 4);
		String month=sdf.substring(5, 7);
		cell.setCellValue("招生老师"+year+"年"+month+"月提成信息");//设置单元格的值
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);    
		      //创建单元格并设置单元格内容
		      row2.createCell(0).setCellValue("团队长姓名");
		      row2.createCell(1).setCellValue("团队提成总额(元)");    
		      row2.createCell(2).setCellValue("团队未提成金额");
		      row2.createCell(3).setCellValue("家庭住址 ");
		      row2.createCell(4).setCellValue("联系方式  ");
		      row2.createCell(5).setCellValue("级别");
		for(int i=3;i<lists.size()+3;i++){
			HSSFRow row=sheet.createRow(i-1);
			row.createCell(0).setCellValue(lists.get(i-3).getName());
			row.createCell(1).setCellValue(lists.get(i-3).getAmount());
			row.createCell(2).setCellValue(lists.get(i-3).getNoPaid());
			row.createCell(3).setCellValue(lists.get(i-3).getAddress());
			row.createCell(4).setCellValue(lists.get(i-3).getTel());
			row.createCell(5).setCellValue(lists.get(i-3).getRanks().getName());		
		}
		String uuid=UUID.randomUUID().toString().replace("-", "").toUpperCase();
		FileOutputStream output=new FileOutputStream(strDirPath+"static/xls/"+uuid+".xls");
	    wb.write(output);
	    output.flush();
	    wb.close();
		return "static/xls/"+uuid+".xls";

	    
	}
	
		
	@RequestMapping("/commissionSkip1")	
	@ResponseBody
	public PageData history(String name,String startTime,String endTime,Page page) throws Exception{
		Date starttime=null;
		Date endtime=null;
		if(startTime!=null&startTime!=""&endTime!=null&endTime!=""){
			starttime = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
			endtime = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
		}
		return proxyCommissionService.history(name,starttime,endtime,page);
	}
	@RequiresPermissions("commission:commissionSkip11")
	@RequestMapping("/commissionSkip11")	
	public String history1(){
		return "proxyCommission/wageHistory";
	}
}
