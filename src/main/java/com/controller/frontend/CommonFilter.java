package com.controller.frontend;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import com.dao.CategoryDao;

@WebFilter("/*")
public class CommonFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private final CategoryDao catDao;
    public CommonFilter() {
    	catDao=new CategoryDao();
    }
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		String path=req.getRequestURI().substring(req.getContextPath().length());
		if(!path.startsWith("/admin/")) {
			request.setAttribute("listCategory", catDao.listAll());
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
