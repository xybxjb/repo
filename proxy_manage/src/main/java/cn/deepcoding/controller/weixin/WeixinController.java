package cn.deepcoding.controller.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.deepcoding.model.ProxyTeacherWatch;
import cn.deepcoding.model.WatchXmlMessageEntity;
import cn.deepcoding.service.ProxyTeacherWatchService;
import cn.deepcoding.util.SecurityUtil;
import cn.deepcoding.util.WeixinUtil;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	@Autowired
	private ProxyTeacherWatchService poxyteacherwatchservice;

	/**
	 * 微信URL接入验证
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
//	@RequestMapping(value = "/weixin", method = RequestMethod.GET)
//	@ResponseBody
//	public String weixin(String signature, String timestamp, String nonce, String echostr) {
//		System.out.println("------微信访问-------");
//
//		// 加密/校验流程如下：
//		// 1. 将token、timestamp、nonce三个参数进行字典序排序
//		String[] arr = { timestamp, nonce, WeixinUtil.TOKEN };
//		Arrays.sort(arr);
//		// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
//		String str = "";
//		for (String temp : arr) {
//			str += temp;
//		}
//		// 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
//		if (SecurityUtil.SHA1(str).equals(signature)) {
//			System.out.println("-----验证通过-----");
//			return echostr;
//		}
//		System.out.println("-----验证不通过-----");
//		return echostr;
//
//	}

	/**
	 * 短信接入验证
	 */
	@RequestMapping("/getmessige")
	@ResponseBody
	public Map<String, String> getmessige(String tel, HttpServletRequest request) {

		Map<String, String> map = poxyteacherwatchservice.getmessige(tel, request);
		return map;
	}

	/**
	 * 验证码接入验证
	 */
	@RequestMapping("/getcode")
	@ResponseBody
	public Map<String, String> getcode(HttpServletRequest request, String code) {
		return poxyteacherwatchservice.getcode(request, code);

	}
	@RequestMapping("/index")
	public String a(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		 String redirect_uri=URLEncoder.encode("http://23139f2z38.51mypc.cn/proxy_manage/wechat/welcome.html", "UTF-8");
         //简单获取openid的话参数response_type与scope与state参数固定写死即可
	StringBuffer url=new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?redirect_uri="+redirect_uri+
			"&appid="+WeixinUtil.APPID+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
	return "redirect:"+url.toString();
	//response.sendRedirect(url.toString());//这里请不要使用get请求单纯的将页面跳转到该url即可
	}

}