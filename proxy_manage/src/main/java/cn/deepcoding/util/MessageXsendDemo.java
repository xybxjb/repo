package cn.deepcoding.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import cn.deepcoding.service.impl.ProxyTeacherWatchServiceImpl;

public class MessageXsendDemo {
	public static String your_appid;
	public static String your_appkey;
	public static String project_id;
	static {

		try {
			InputStream in = WeixinUtil.class.getClassLoader().getResourceAsStream("const.properties");
			Properties prop = new Properties();
			prop.load(in);
			your_appid = (String) prop.get("messiage_appid");
			your_appkey = (String) prop.get("messiage_appkey");
			project_id = (String) prop.get("messiage_project_id");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String xsend(String appid, String appkey, String to, String project, String vars) {
		String URL = "http://api.mysubmail.com/message/xsend.json";
		HashMap<String, String> paramer = new HashMap<String, String>();
		paramer.put("appid", appid);
		paramer.put("signature", appkey);
		paramer.put("to", to);
		paramer.put("project", project);
		paramer.put("vars", vars);
		return executePostByUsual(URL, paramer);

	}

	public static String executePostByUsual(String actionURL, HashMap<String, String> parameters) {
		String response = "";
		try {
			URL url = new URL(actionURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置请求数据内容
			String requestContent = "";
			Set<String> keys = parameters.keySet();
			for (String key : keys) {
				requestContent = requestContent + key + "=" + URLEncoder.encode(parameters.get(key), "UTF-8") + "&";
			}
			requestContent = requestContent.substring(0, requestContent.lastIndexOf("&"));
			DataOutputStream ds = new DataOutputStream(connection.getOutputStream());
			// 使用write(requestContent.getBytes())是为了防止中文出现乱码
			ds.write(requestContent.getBytes());
			ds.flush();
			try {
				// 获取URL的响应
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String s = "";
				String temp = "";
				while ((temp = reader.readLine()) != null) {
					s += temp;
				}
				response = s;
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("No response get!!!");
			}
			ds.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Request failed!");
		}
		return response;
	}

	public static void main(String[] args) {
		// xsend demo

		/*
		 * String response = MessageXsendDemo.xsend("your_appid", "your_appkey",
		 * "telphone_number", "project_id", "var变量（jsonString）");
		 */
		// "{\"code\":\"123456\",\"time\":\"10\"}");
		// System.out.println(response);
		// ProxyTeacherWatchServiceImpl impl = new
		// ProxyTeacherWatchServiceImpl();
		// Integer getrandom = impl.getrandom();
		// getmessige("15034358109", 888888);
		 //getmessige("13303475875", 888888);
		 getmessige("13718962979", 888888);

	}

	public static void getmessige(String tel, Integer code) {
		// String response = MessageXsendDemo.xsend("29469",
		// "78452dce814b760875873e45ad07bb8c", tel, "`",
		// "{\"code\":\""+code+"\",\"time\":\"10\"}");
		String response = MessageXsendDemo.xsend(your_appid, your_appkey, tel, project_id,
				"{\"code\":\"" + code + "\",\"time\":\"10\"}");
	
		System.out.println(response);

	}
}
