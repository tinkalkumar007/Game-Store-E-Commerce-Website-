package com.controller.admin.customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.CustomerDao;
import com.entity.Customer;
import com.services.CustomerServices;
@WebServlet("/admin/edit_customer")
public class EditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCustomerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices custServe=new CustomerServices(request,response);
		custServe.editCustomer();
	}
}
