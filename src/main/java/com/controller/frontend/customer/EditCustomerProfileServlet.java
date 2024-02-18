package com.controller.frontend.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.CustomerServices;

@WebServlet("/edit_profile")
public class EditCustomerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCustomerProfileServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		CustomerServices custServe=new CustomerServices(request,response);
		custServe.showCustomerProfileEditForm();
	}
}