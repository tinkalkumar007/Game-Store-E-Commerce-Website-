package com.controller.frontend.shoppingcart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.GameDao;
import com.entity.Game;

@WebServlet("/addto_cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer gameId=Integer.parseInt(request.getParameter("game_id"));
		
		Object cartObject=request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart=null;
		if(cartObject!=null && cartObject instanceof ShoppingCart) {
			shoppingCart=(ShoppingCart) cartObject;
		}
		else {
			shoppingCart=new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		GameDao gameDao=new GameDao();
		Game game=gameDao.get(gameId);
		shoppingCart.addItem(game);
		
		String cartPage=request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}
}
