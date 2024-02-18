package com.controller.frontend.shoppingcart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.GameDao;
import com.entity.Game;

@WebServlet("/remove_from_cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer gameId=Integer.parseInt(request.getParameter("game_id"));
		
		ShoppingCart shoppingCart=(ShoppingCart)request.getSession().getAttribute("cart");
		shoppingCart.removeItem(new Game(gameId));
		String cartPage=request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}
}
