package cn.deepcoding.util;

import java.util.ArrayList;
import java.util.List;

public class UtilList {
	public List<String> method(List<String> userIdList,int a){
		List<String> smallUserIdList = new ArrayList<String>();
		int num =0;
    	for(int i =0;i<userIdList.size()/8;i++){
    		smallUserIdList=userIdList.subList(num, num+8);
    		num=num+8;
    		if(i==a){
    			return smallUserIdList;
    		}
    	}
    	smallUserIdList=userIdList.subList(userIdList.size()-userIdList.size()%8, userIdList.size());
    	return smallUserIdList;
	}
}
