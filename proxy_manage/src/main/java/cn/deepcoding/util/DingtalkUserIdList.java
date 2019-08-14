package cn.deepcoding.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.CorpRoleSimplelistRequest;
import com.dingtalk.api.request.OapiDepartmentGetRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserSimplelistRequest;
import com.dingtalk.api.response.CorpRoleSimplelistResponse;
import com.dingtalk.api.response.OapiDepartmentGetResponse;
import com.dingtalk.api.response.CorpRoleSimplelistResponse.EmpSimpleList;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse.Userlist;
import com.taobao.api.ApiException;



public class DingtalkUserIdList {
	//获取角色下员工列表
//   public static List<String> getDingtalkUserIdList(){
//	   DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
//		CorpRoleSimplelistRequest req = new CorpRoleSimplelistRequest();
//		req.setRoleId(328734018L);
//		req.setSize(20L);
//		req.setOffset(0L);
//		CorpRoleSimplelistResponse rsp = null;
//		try {
//			rsp = client.execute(req, AccessToken.getAccessToken());
//		} catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<EmpSimpleList> empSimpleList = rsp.getResult().getList();
//		List<String> userIdList = new ArrayList<String>();
//		empSimpleList.forEach(empSimple->{
//			userIdList.add(empSimple.getUserid());
//		});
//		return userIdList;
//   }
	
   //获取员工详细信息
   public static Map<String,List<String>> getUser(String userID) throws ApiException{
	   Map<String,List<String>> map = new HashMap<String,List<String>>();
	   DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
	   OapiUserGetRequest request = new OapiUserGetRequest();
	   request.setUserid(userID);
	   request.setHttpMethod("GET");
	   OapiUserGetResponse response = client.execute(request, AccessToken.getAccessToken());
	   List<Long> listDepartment = response.getDepartment();
	   List<String> list = new ArrayList<String>();
	   listDepartment.forEach(action->{
		   try {
			   list.add(DingtalkUserIdList.getDepartmentName(action));
			} catch (ApiException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   });
	   map.put(response.getName(),list);
	   return map;
   }
   //获取部门名字
   public static String getDepartmentName(Long id) throws ApiException{
	   DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/get");
	   OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
	   request.setId(id.toString());
	   request.setHttpMethod("GET");
	   OapiDepartmentGetResponse response = client.execute(request, AccessToken.getAccessToken());
	   return response.getName();
   }
   
   
   
   // 获取部门下的用户
   public static  List<String> getDingtalkUserIdList(){
	   Long i = 1l;
	   Long j = 100l;
	   List<String> userIdList = new ArrayList<String>();
	   // 暂时 查询 1000 条数据
	   for(int h = 0;h<10;h++){
		   
		   Map<String,String> map = new HashMap<String,String>();
		   DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
		   OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
		   // 研发部 id
		   
		   request.setDepartmentId(77163152L);
		   request.setOffset(i);
		   request.setSize(j);
		   request.setHttpMethod("GET");
		   OapiUserSimplelistResponse response = null;
		   try {
		    	response = client.execute(request, AccessToken.getAccessToken());
		   } catch (ApiException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   List<Userlist> userlist = response.getUserlist();
		   
		   for (Userlist userlist2 : userlist) {
			   userIdList.add(userlist2.getUserid());
		   }
		   i +=j;
	   }
	   return userIdList;
   }
   
   public static void main(String[] args) throws Exception {
	   List<String> dingtalkUserIdList = getDingtalkUserIdList();
	   List<String> smallUserIdList = new ArrayList<String>();
		int num =0;
		int sum = 0;
	    for(int i =0;i<dingtalkUserIdList.size()/8;i++){
	    	smallUserIdList=dingtalkUserIdList.subList(num, num+8);
	    	num=num+8;
	    	sum +=1;
	    	System.out.println(smallUserIdList.get(0)+"   "+sum);
	    }
	   System.out.println(sum);
	   System.out.println(smallUserIdList.size());
	   for (String string : dingtalkUserIdList) {
		System.out.println(string);
	}
	   
   }
}
