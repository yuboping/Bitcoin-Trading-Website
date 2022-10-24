package com.gsww.qyws.gzbd.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 //验证用户是否登陆
		//if(request.getSession().getAttribute("userName")!=null){
//		if(request.getSession().getAttribute("userName")!=null){
//			return true;
//		} else {
//			request.setAttribute("errorMessage", "请重新登录！");
//			String script = "alert('对不起，由于您长时间没有操作，请重新登录！');window.close()";
//			response.setContentType("text/html;charset=UTF-8");
//			response.getWriter().write("<script language=\"javascript\">");
//			response.getWriter().write(script + "\n");
//			response.getWriter().write("</script>");
//			return false;
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}


}
