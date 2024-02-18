package com.controller.frontend;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class CustomerLoginFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private static String [] loginRequiredUrls={
		"/view_profile","/edit_profile","/update_profile","/write_review",
		"/check_out","/place_order","/view_orders","/show_order_detail"
	};
	public CustomerLoginFilter() {
        super();
    }
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		String path=req.getRequestURI().substring(req.getContextPath().length());
		
		if(path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
	
		HttpSession session=req.getSession(false);
		String reqUrl=req.getRequestURL().toString();
		
		boolean isLogin=session!=null && session.getAttribute("loggedCustomer")!=null;
		System.out.println("Path: "+path);
		System.out.println("isLogin: "+isLogin);
		System.out.println("RequestUrl: "+reqUrl);
		if(!isLogin && isLoginRequired(reqUrl)) {
			
			String query=req.getQueryString();
			if(query!=null) {
				reqUrl=reqUrl.concat("?").concat(query);
			}
			session.setAttribute("requestURL", reqUrl);
			
			String loginPage="frontend/login.jsp";
			RequestDispatcher rqd=req.getRequestDispatcher(loginPage);
			rqd.forward(request, response);
		}
		else {
			chain.doFilter(request, response);
		}
	}
	public boolean isLoginRequired(String reqUrl) {
		for(String loginUrl:loginRequiredUrls) {
			if(reqUrl.contains(loginUrl)) {
				return true;
			}
		}
		return false;
	}
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
