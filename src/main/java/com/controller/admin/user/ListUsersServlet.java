package com.controller.admin.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.UserServices;

@WebServlet("/admin/list_users")
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListUsersServlet() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices u=new UserServices(request, response);
		u.listUser();
	}

}
