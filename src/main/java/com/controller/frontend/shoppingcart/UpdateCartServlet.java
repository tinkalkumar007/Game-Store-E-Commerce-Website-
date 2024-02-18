package com.controller.frontend.shoppingcart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import com.entity.Game;

@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCartServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String[] gameIds=request.getParameterValues("gameId");
		String[] quantities=new String[gameIds.length];
		
		for(int i=1;i<=gameIds.length;i++) {
			String quantity=request.getParameter("quantity"+i);
			quantities[i-1]=quantity;
		}
		int[] ArraygameId=Arrays.stream(gameIds).mapToInt(Integer::parseInt).toArray();
		int[] Arrayquantity=Arrays.stream(quantities).mapToInt(Integer::parseInt).toArray();
		
		ShoppingCart cart=(ShoppingCart) request.getSession().getAttribute("cart");
		cart.updateCart(ArraygameId, Arrayquantity);
		
		String cartPage=request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}
}
