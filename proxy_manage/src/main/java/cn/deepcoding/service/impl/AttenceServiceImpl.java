package cn.deepcoding.service.impl;

import java.io.IOException;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRequest;
import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.dingtalk.api.response.OapiAttendanceListResponse.Recordresult;
import com.taobao.api.ApiException;
import cn.deepcoding.dao.AttenceDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.model.*;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.AttenceService;
import cn.deepcoding.util.AccessToken;
import cn.deepcoding.util.DingtalkUserIdList;
import cn.deepcoding.util.*;
@Service
@Transactional
public class AttenceServiceImpl implements AttenceService,Runnable{
	@Autowired
    private AttenceDao attenceDao;
	@Autowired
	private StudentDao studentDao;
	
	/**
	 * 定时更新数据
	 */
	@Scheduled(cron="0 0 6 * * ?")
	@Override
	public int automaticAttendance(){
		List<String> userIdList =DingtalkUserIdList.getDingtalkUserIdList();
		List<String> smallUserIdList = new ArrayList<String>();
		int num =0;
    	int sum = 0;
    	for(int i =0;i<userIdList.size()/8;i++){
    		smallUserIdList=userIdList.subList(num, num+8);
    		num=num+8;
    		sum=sum+method(smallUserIdList);
    	}
    	if(userIdList.size()%8!=0){
    		smallUserIdList=userIdList.subList(userIdList.size()-userIdList.size()%8, userIdList.size());
    		sum=sum+method(smallUserIdList);
		}
    	return sum;
	}
	
	public int method(List<String> smallUserIdList){
		String maxWorkDate = attenceDao.getMaxWorkDate();
		List<String> maxDateList = new ArrayList<String>();
		List<String> nullDateList = new ArrayList<String>();
//		String maxDate= attenceDao.getMaxWorkDate();
		String oldDate = "";
		int num = 0;
		for (String string : smallUserIdList) {
			String maxWorkDate1 = attenceDao.getMaxWorkDate1(string);
			if(maxWorkDate1 == null){
				nullDateList.add(string);		
			}
			if(maxWorkDate != null && maxWorkDate.equals(maxWorkDate1)){
				maxDateList.add(string);
			}
			if(maxWorkDate1 != null & !maxWorkDate.equals(maxWorkDate1)){
				List<String> maxDateList2= new ArrayList<String>();
				maxDateList2.add(string);
				oldDate = maxWorkDate1;
				String newDate = UtilDate.getTimesmorning();
				DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
				OapiAttendanceListRequest request = new OapiAttendanceListRequest();
				
				List<String> dateList =UtilDate.getMonthBetweenDateStr(oldDate, newDate);
				for(String date:dateList){
					request.setWorkDateFrom(date+" 00:00:00");
					request.setWorkDateTo(date+" 00:00:00");
					request.setUserIdList(maxDateList2);
					request.setOffset(0L);
					request.setLimit(50L);
					OapiAttendanceListResponse response = null;
					try {
						 response = client.execute(request,AccessToken.getAccessToken());
					} catch (ApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(response.getRecordresult().isEmpty()){
						continue;
					}
					List<Recordresult> recordsultList =response.getRecordresult();
					List<Attence> attenceList = new ArrayList<Attence>();
					SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
					recordsultList.forEach(recordsult->{
						Attence attence = new Attence();
						attence.setId(recordsult.getId()+"");
						if(recordsult.getCheckType().equals("OnDuty")){
							attence.setCheckType(0);
						}else if(recordsult.getCheckType().equals("OffDuty")){
							attence.setCheckType(1);
						}
						attence.setUserId(recordsult.getUserId());
						attence.setBaseCheckTime(time.format(recordsult.getBaseCheckTime()));
						attence.setUserCheckTime(time.format(recordsult.getUserCheckTime()));
						attence.setWorkDate(day.format(recordsult.getWorkDate()));
						if(recordsult.getTimeResult().equals("Normal")){
							attence.setTimeResult(0);
						}else if(recordsult.getTimeResult().equals("Early")){
							attence.setTimeResult(1);
						}else if(recordsult.getTimeResult().equals("Late")){
							attence.setTimeResult(2);
						}else if(recordsult.getTimeResult().equals("SeriousLate")){
							attence.setTimeResult(3);
						}else if(recordsult.getTimeResult().equals("Absenteeism")){
							attence.setTimeResult(4);
						}else if(recordsult.getTimeResult().equals("NotSigned")){
							attence.setTimeResult(5);
						}
						attenceList.add(attence);
					});
					num=num+attenceDao.saveAttence(attenceList);
				}
			}
		}
		

		String oldDate2 = "";
		int method2=0;
		int method22=0;
		oldDate2 = "2019-03-01";
		if(nullDateList.size()>0){
			method2 = method2(nullDateList,oldDate2);
		}
		if(maxDateList.size()>0){
			method22 = method2(maxDateList,maxWorkDate);
		}

		if(method2==0){
			num +=method22;
		}else if(method22==0){
			num +=method2;
		}else{
			num =method2+method22;
		}
		

//		String oldDate= attenceDao.getMaxWorkDate();
//		if(oldDate==null||oldDate==""){
//			oldDate="2018-10-20";
//		}

//		oldDate="2018-10-20";
//		String newDate = UtilDate.getTimesmorning();
//		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
//		OapiAttendanceListRequest request = new OapiAttendanceListRequest();
////		int num = 0;
//		List<String> dateList =UtilDate.getMonthBetweenDateStr(oldDate, newDate);
//		for(String date:dateList){
//			request.setWorkDateFrom(date+" 00:00:00");
//			request.setWorkDateTo(date+" 00:00:00");
//			request.setUserIdList(nullDateList);
//			request.setOffset(0L);
//			request.setLimit(50L);
//			OapiAttendanceListResponse response = null;
//			try {
//				 response = client.execute(request,AccessToken.getAccessToken());
//			} catch (ApiException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(response.getRecordresult().isEmpty()){
//				continue;
//			}
//			List<Recordresult> recordsultList =response.getRecordresult();
//			List<Attence> attenceList = new ArrayList<Attence>();
//			SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
//			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
//			recordsultList.forEach(recordsult->{
//				Attence attence = new Attence();
//				attence.setId(recordsult.getId()+"");
//				if(recordsult.getCheckType().equals("OnDuty")){
//					attence.setCheckType(0);
//				}else if(recordsult.getCheckType().equals("OffDuty")){
//					attence.setCheckType(1);
//				}
//				attence.setUserId(recordsult.getUserId());
//				attence.setBaseCheckTime(time.format(recordsult.getBaseCheckTime()));
//				attence.setUserCheckTime(time.format(recordsult.getUserCheckTime()));
//				attence.setWorkDate(day.format(recordsult.getWorkDate()));
//				if(recordsult.getTimeResult().equals("Normal")){
//					attence.setTimeResult(0);
//				}else if(recordsult.getTimeResult().equals("Early")){
//					attence.setTimeResult(1);
//				}else if(recordsult.getTimeResult().equals("Late")){
//					attence.setTimeResult(2);
//				}else if(recordsult.getTimeResult().equals("SeriousLate")){
//					attence.setTimeResult(3);
//				}else if(recordsult.getTimeResult().equals("Absenteeism")){
//					attence.setTimeResult(4);
//				}else if(recordsult.getTimeResult().equals("NotSigned")){
//					attence.setTimeResult(5);
//				}
//				attenceList.add(attence);
//			});
//			num=num+attenceDao.saveAttence(attenceList);
//		}
		return num;
	}
	
	// 添加数据
	public int method2(List<String> maxDateList,String str){
		String oldDate = str;
		if(oldDate == null){
			return 0;
		}	
		if(maxDateList == null){
			return 0;
		}
//		oldDate="2018-10-20";

		String newDate = UtilDate.getTimesmorning();
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
		OapiAttendanceListRequest request = new OapiAttendanceListRequest();
		int num = 0;
		List<String> dateList =UtilDate.getMonthBetweenDateStr(oldDate, newDate);
		for(String date:dateList){
			request.setWorkDateFrom(date+" 00:00:00");
			request.setWorkDateTo(date+" 00:00:00");
			request.setUserIdList(maxDateList);
			request.setOffset(0L);
			request.setLimit(50L);
			OapiAttendanceListResponse response = null;
			try {
				 response = client.execute(request,AccessToken.getAccessToken());
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(response.getRecordresult().isEmpty()){
				continue;
			}
			List<Recordresult> recordsultList =response.getRecordresult();
			List<Attence> attenceList = new ArrayList<Attence>();
			SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
			recordsultList.forEach(recordsult->{
				Attence attence = new Attence();
				attence.setId(recordsult.getId()+"");
				if(recordsult.getCheckType().equals("OnDuty")){
					attence.setCheckType(0);
				}else if(recordsult.getCheckType().equals("OffDuty")){
					attence.setCheckType(1);
				}
				attence.setUserId(recordsult.getUserId());
				attence.setBaseCheckTime(time.format(recordsult.getBaseCheckTime()));
				attence.setUserCheckTime(time.format(recordsult.getUserCheckTime()));
				attence.setWorkDate(day.format(recordsult.getWorkDate()));
				if(recordsult.getTimeResult().equals("Normal")){
					attence.setTimeResult(0);
				}else if(recordsult.getTimeResult().equals("Early")){
					attence.setTimeResult(1);
				}else if(recordsult.getTimeResult().equals("Late")){
					attence.setTimeResult(2);
				}else if(recordsult.getTimeResult().equals("SeriousLate")){
					attence.setTimeResult(3);
				}else if(recordsult.getTimeResult().equals("Absenteeism")){
					attence.setTimeResult(4);
				}else if(recordsult.getTimeResult().equals("NotSigned")){
					attence.setTimeResult(5);
				}
				attenceList.add(attence);
			});
			num=num+attenceDao.saveAttence(attenceList);
		}
		return num;
	}
	//根据用户userID以及时间查询
	@Override
	public PageData showStudentAttence(Page page,
            String workDateFrom, String workDateTo,
            String userId)  {
		PageData pageData = new PageData();
		List<Attence> attences =attenceDao.listByPagetStudentAttence(page, workDateFrom, workDateTo, userId);
	
		int total =attenceDao.countStudentAttence(workDateFrom, workDateTo, userId);
		pageData.setRows(attences);
		pageData.setTotal(total);
		return pageData;
	}
	
	@Override
	public PageData listAttenceCount(String workDateFrom, String workDateTo,List<Student> students,int classId) {
		// TODO Auto-generated method stub
		List<AttenceCount> attenceCounts = new ArrayList<AttenceCount>();
		
		Map<String,String> nameDing =new HashMap<String,String>();
		students.forEach(student->{
			nameDing.put(student.getName(), student.getDingId());
		});
		
		nameDing.forEach((name,userId)->{
			List<AttenceGroup> AttenceGroups =attenceDao.getAttenceCount(workDateFrom, workDateTo, userId);
			AttenceCount attenceCount = new AttenceCount();
			AttenceGroups.forEach(attenceGroup->{
				if(attenceGroup.getTimeResult()==0){
					attenceCount.setNormal(attenceGroup.getAmount());
				}else if(attenceGroup.getTimeResult()==1){
					attenceCount.setEarly(attenceGroup.getAmount());
				}else if(attenceGroup.getTimeResult()==2){
					attenceCount.setLate(attenceGroup.getAmount());
				}else if(attenceGroup.getTimeResult()==3){
					attenceCount.setSeriousLate(attenceGroup.getAmount());
				}else if(attenceGroup.getTimeResult()==4){
					attenceCount.setAbsenteeism(attenceGroup.getAmount());
				}  if(attenceGroup.getTimeResult()==5){
					attenceCount.setNotSigned(attenceGroup.getAmount());
				}
			});
			attenceCount.setUserId(userId);
			attenceCount.setStudentName(name);
			attenceCounts.add(attenceCount);
		});
		PageData pageData = new PageData();
		pageData.setRows(attenceCounts);
		pageData.setTotal(studentDao.getStudengCountByClassId(classId));
		return pageData;
	}
	
	@Override
	public void bindingAttenceAutomatic() throws ApiException {
		// TODO Auto-generated method stub
		
		new Thread	(new Runnable() {
			@Override
			public void run() {
				List<String> userIdList =DingtalkUserIdList.getDingtalkUserIdList();
				Map<String,List<String>> map = new HashMap<String,List<String>>();
				userIdList.removeAll(studentDao.listDingId());
				
				if(userIdList.isEmpty()){
					
				}
				for(String userId:userIdList){
					try {
						map = DingtalkUserIdList.getUser(userId);
					} catch (ApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(String name:map.keySet()){
				    	int count = studentDao.countByName(name);
				    	if(count==1){
				    		
				    		studentDao.updateDingId(userId, name);
				    	}
				    }
				}
			}
		}).start();
		
	}

	@Override
	public List<Employee> getEmployee() throws ApiException {
		// TODO Auto-generated method stub
		List<String> userIdList =DingtalkUserIdList.getDingtalkUserIdList();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		List<Employee> list =new ArrayList<Employee>();
		userIdList.removeAll(studentDao.listDingId());
		Employee employee = null;
		for(String userId:userIdList){
			map = DingtalkUserIdList.getUser(userId);
			for(String name:map.keySet()){
				 employee = new Employee();
				 employee.setUserId(userId);
				 employee.setName(name);
				 employee.setDepartmentList(map.get(name));
			}
			list.add(employee);
		}
		return list;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		
		for(int i = 0;i<20;i++){
			List<String> l = new ArrayList<String>();
			if(i<5){
				l.add(i+"asd");
				aaa(l);
			}
		}
	}
	public static void aaa(List<String> ll){
		for (String string : ll) {
			System.out.println(string+"123");
		}
		
	}
}
