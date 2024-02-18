package com.controller.admin.order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

import com.dao.GameDao;
import com.entity.Game;
import com.entity.GameOrder;
import com.entity.OrderDetail;

@WebServlet("/admin/add_game_to_order")
public class AddGameToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddGameToOrderServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer gameId=Integer.parseInt(request.getParameter("gameId"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		GameDao gameDao=new GameDao();
		Game game=gameDao.get(gameId);
		
		HttpSession session=request.getSession();
		GameOrder  order=(GameOrder) session.getAttribute("order");
		
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setGame(game);
		orderDetail.setQuantity(quantity);
		
		float subTotal=quantity*game.getPrice();
		orderDetail.setSubtotal(subTotal);
		
		float newTotal=order.getTotal()+subTotal;
		order.setTotal(newTotal);
		order.getOrderDetails().add(orderDetail);
		
		request.setAttribute("game", game);
		session.setAttribute("AddNewGame", true);
		
		String filePath="../admin/add_game_result.jsp";
		RequestDispatcher rqd=request.getRequestDispatcher(filePath);
		rqd.forward(request, response);
	}

}
