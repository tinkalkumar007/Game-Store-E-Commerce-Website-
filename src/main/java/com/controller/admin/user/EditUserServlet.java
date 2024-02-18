package com.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.UserServices;

@WebServlet("/admin/edit_user")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EditUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices usr=new UserServices(request,response);
		usr.editUser();
	}
}
