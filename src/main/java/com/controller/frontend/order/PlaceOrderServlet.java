package com.controller.frontend.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.OrderServices;

@WebServlet("/place_order")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PlaceOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		OrderServices orderServe=new OrderServices(request,response);
		orderServe.placeOrder();
	}

}
