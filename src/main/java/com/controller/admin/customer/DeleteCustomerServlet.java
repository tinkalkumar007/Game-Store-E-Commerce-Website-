package com.controller.admin.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.CustomerDao;
import com.services.CustomerServices;
@WebServlet("/admin/delete_customer")
public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCustomerServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		CustomerDao custDao=new CustomerDao();
		custDao.delete(id);
		String msg="Customer with id "+id+" deleted successfully.";
		CustomerServices custServe=new CustomerServices(request,response);
		custServe.listAllCustomer(msg);
	}
}
