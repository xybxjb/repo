package cn.deepcoding.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 String url = request.getRequestURI();  
		
	        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制  
	        if(url.indexOf("/check")>=0){  
	            return true;  
	        }  
	        
	        //获取Session  
	        HttpSession session = request.getSession();  
	        String username = (String)session.getAttribute("username");  
	          
	        if(username != null){  
	            return true;  
	        }  
	        //不符合条件的，跳转到登录界面  
	        request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);  
	          
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
