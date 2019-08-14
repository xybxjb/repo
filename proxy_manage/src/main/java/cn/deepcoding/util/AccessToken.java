package cn.deepcoding.util;

 

import java.util.ResourceBundle;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;

 

public class AccessToken {
	/**
	 * 获得getAccessToken
	 * @return
	 */
    public static String getAccessToken(){
    DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
   	 OapiGettokenRequest request = new OapiGettokenRequest();
   	 ResourceBundle resource = ResourceBundle.getBundle("const");
   	 request.setAppkey(resource.getString("Appkey"));
   	 request.setAppsecret(resource.getString("Appsecret"));
   	 request.setHttpMethod("GET");
   	 OapiGettokenResponse response=null;
	try {
		response = (OapiGettokenResponse) client.execute(request);
		
	} catch (ApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String accessToken = response.getAccessToken();
   	 return accessToken;
    }
public static void main(String[] args) {
	System.err.println(getAccessToken());
	
}
}
