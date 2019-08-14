package cn.deepcoding.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

public class MessageDemo {

	public static String send(String appid, String appkey, String to, String content) {
		String URL = "http://api.mysubmail.com/message/send.json";
		HashMap<String, String> paramer = new HashMap<String, String>();
		paramer.put("appid", appid);
		paramer.put("signature", appkey);
		paramer.put("to", to);
		paramer.put("content", content);
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

/*		String response = MessageDemo.send("appid", "appkey", "telephone_number", "message_content");
*/		
//		String response = MessageDemo.send("29469", "ad38a3045f009a622eaca6bd19ae15cd", "18910757379", 
//				"【深度】测试一下短信");
//		String response = MessageDemo.send("31621", "edaec030d2ed49943377ffa0fe8aa8a4", "17634010196", 
//				"【深度】测试一下短信");
//		System.out.println(response);

	}

}
