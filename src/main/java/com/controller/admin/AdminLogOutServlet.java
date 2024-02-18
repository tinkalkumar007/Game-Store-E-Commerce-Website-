package com.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/logout")
public class AdminLogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLogOutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.removeAttribute("useremail");
		
		RequestDispatcher rqd=request.getRequestDispatcher("login.jsp");
		rqd.forward(request, response);
	}
}
