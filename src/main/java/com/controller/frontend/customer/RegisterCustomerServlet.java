package com.controller.frontend.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.CustomerServices;
@WebServlet("/register_customer")
public class RegisterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterCustomerServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		CustomerServices custServe=new CustomerServices(request,response);
		custServe.registerCustomer();
	}
}
