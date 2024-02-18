package com.controller.admin.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import com.entity.Customer;
import com.services.CustomerServices;

@WebServlet("/admin/create_customer")
public class CreateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CreateCustomerServlet() {
        super();
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices custServe=new CustomerServices(req,response);
		custServe.createCustomer();
	}
	
}
