package com.controller.admin.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.OrderServices;

@WebServlet("/admin/edit_order")
public class EditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		OrderServices orderServe=new OrderServices(request,response);
		orderServe.showEditOrderForm();
	}
}
