package com.controller.frontend.shoppingcart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.GameDao;
import com.entity.Game;


@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ViewCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Object cartObject=request.getSession().getAttribute("cart");
		if(cartObject==null) {
			ShoppingCart cart=new ShoppingCart();
			request.getSession().setAttribute("cart",cart);
		}
		
		String filePath="frontend/shopping_cart.jsp";
		RequestDispatcher rqd=request.getRequestDispatcher(filePath);
		rqd.forward(request, response);
	}
}
