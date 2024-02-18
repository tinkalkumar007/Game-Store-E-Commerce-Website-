package com.controller.admin;

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
@WebFilter("/admin/*")
public class AdminLoginFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
    public AdminLoginFilter() {
        super();
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpSession session=httpRequest.getSession(false);
		
		boolean logIn=session!=null && session.getAttribute("useremail")!=null;
		String loginUri=httpRequest.getContextPath()+"/admin/login";
		
		boolean loginReq=httpRequest.getRequestURI().equals(loginUri);
		boolean loginPage=httpRequest.getRequestURI().endsWith("login.jsp");
		
		if(logIn && (loginReq||loginPage)) {
			RequestDispatcher rqd=request.getRequestDispatcher("/admin/");
			rqd.forward(request, response);
		
		}
		else if(logIn||loginReq) {
			chain.doFilter(request, response);
		}
		else {
			RequestDispatcher rqd=request.getRequestDispatcher("login.jsp");
			rqd.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
