package cn.deepcoding.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WeixinUtil {
	public static String TOKEN;
	public static String APPID;
	public static String SECRET;
	public static String CREATE_MENU_URL;
	public static String GET_ACCESSTOKEN_URL;
	public static String SEND_TEMPLATE_URL;
	public static String WEB_ACCESSTOKEN_URL;
	public static String GET_TICKET_URL;
	public static String Get_UnionID;
	public static String Get_UnionID2;

	public static String Get_Url;
	public static String ACCESSTOKEN;
	// ACCESSTOKEN的失效时间 
	public static long EXPIRESTIME;

	static {

		try {
			InputStream in = WeixinUtil.class.getClassLoader().getResourceAsStream("const.properties");
			Properties prop = new Properties();
			prop.load(in);
			TOKEN = (String) prop.get("TOKEN");
			APPID = (String) prop.get("APPID");
			SECRET = (String) prop.get("SECRET");
			CREATE_MENU_URL = (String) prop.get("CREATE_MENU_URL");
			GET_ACCESSTOKEN_URL = (String) prop.get("GET_ACCESSTOKEN_URL");
			SEND_TEMPLATE_URL = (String) prop.get("SEND_TEMPLATE_URL");
			WEB_ACCESSTOKEN_URL = (String) prop.get("WEB_ACCESSTOKEN_URL");
			GET_TICKET_URL = (String) prop.get("GET_TICKET_URL");
			Get_UnionID = (String) prop.get("Get_UnionID");
			Get_UnionID2 = (String) prop.get("Get_UnionID2");
			Get_Url = (String) prop.get("Get_Url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取accessToken
	 */
	public static String getAccessToken() {
		// 判断ACCESSTOKEN是否有效
		if (ACCESSTOKEN == null || EXPIRESTIME < new Date().getTime()) {
			String result = HttpUtil.get(GET_ACCESSTOKEN_URL.replace("APPID", APPID).replace("APPSECRET", SECRET));
			System.out.println(result);
			JSONObject json = JSON.parseObject(result);
			// 设置ACCESSTOKEN
			ACCESSTOKEN = json.getString("access_token");
			// 设置ACCESSTOKEN的失效时间
			long expires_in = json.getLong("expires_in");
			// 失效时间 = 当前时间 + 有效期
			EXPIRESTIME = new Date().getTime() + (expires_in - 60) * 1000;
		}
		return ACCESSTOKEN;
	}

	public static void getTicket() {
		String string = HttpUtil.get(GET_TICKET_URL.replace("ACCESS_TOKEN", getAccessToken()));
		System.out.println(string);
	}

	public static String getUnionID(String openid) {
		String string = HttpUtil.get(Get_UnionID.replace("ACCESS_TOKEN", getAccessToken()).replace("OPENID", openid));
		return string;
	}

	// 网页获取用户信息
	public static String getUnionID2(String code) {
		
		String string = HttpUtil
				.get(Get_UnionID2.replace("APPID", APPID).replace("SECRET", SECRET).replace("CODE", code));
		return string;
		
		
	}

	/*// 网页获取用户信息
	@SuppressWarnings("deprecation")
	public static String getURL(String UR) {
		 URLEncoder urlEncoder = new	URLEncoder();

		String URL = urlEncoder.encode(UR);
		String string = HttpUtil.get(Get_Url.replace("APPID", APPID).replace("redirect_uri", URL));
		return string;
	}*/

	public static void main(String[] args) {
		System.out.println(getAccessToken());

		// sendTemplate();

		// getTicket();
		// System.out.println(TOKEN);

		// getTicket();
		// System.out.println(new
		// String(Base64.decodeBase64("RnJlZSB0byBsaXZl8J+NgQ==")));
	  // URLEncoder urlEncoder = new	URLEncoder();
		//String url = getURL("https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo");
		//System.out.println(url);
	}

}
