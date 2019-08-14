package cn.deepcoding.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.deepcoding.model.ProxyTeacherWatch;
import cn.deepcoding.service.ProxyTeacherWatchService;
import cn.deepcoding.util.WeixinUtil;

public class WechatInterceptor implements HandlerInterceptor {
	@Autowired
	private ProxyTeacherWatchService poxyteacherwatchservice;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		  
		// @param code 这是微信提供的东西code
		// 通过session判断登陆状态
		if ((String) request.getSession().getAttribute("oppenid") != null) {
			String oppenid = (String) request.getSession().getAttribute("oppenid");
			// 通过oppenid判断是否能查出关联表，查不出来表示当前的openid无效
			if ( poxyteacherwatchservice.watchLogin(oppenid, request)) {
				return true;
			}
		}
		String code = request.getParameter("code");
		if (code != null) {
			// 通过用户携带的code获取用户的openid
			String unionID2 = WeixinUtil.getUnionID2(code);
			System.out.println("openid:"+unionID2);
			// 通过JSON.parseObject(unionID2);获得具体信息
			JSONObject jsonObject = JSON.parseObject(unionID2);
			// 得到openid
			String oppenid = (String) jsonObject.get("openid");
			// 将oppenid放到session域内
			request.getSession().setAttribute("oppenid", oppenid);
			// 调用内部的openid判断方法
			if (poxyteacherwatchservice.watchLogin(oppenid, request)) {
				request.getRequestDispatcher("/wechat/welcome.html").forward(request, response);
				return true;
			}
		}
		request.getRequestDispatcher("/wechat/login.html").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion");

	}

}
