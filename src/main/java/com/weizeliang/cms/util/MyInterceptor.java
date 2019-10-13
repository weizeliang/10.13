package com.weizeliang.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: AdminInterceptor 
 * @Description: 个人中心 后台拦截器
 * @author: weizeliang
 * @date: 2019年9月18日 下午3:55:35
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//从request获取session对象.如果request中没有session对象,则返回null..如果有则返回session对象
		HttpSession session = request.getSession(false);
		if(null !=session) {
			Object object = session.getAttribute("user");
			if(null!=object)//用户登录
				return true;//不拦截.放行
			
		}
		//没有登录则回到登录页面
		response.sendRedirect("/passport/login");
		
		
		return false;
	}
}
