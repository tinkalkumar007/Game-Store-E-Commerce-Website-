package com.controller.admin.order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import com.dao.GameDao;
import com.entity.Game;
import com.entity.GameOrder;
import com.entity.OrderDetail;

@WebServlet("/admin/remove_game_from_order")
public class RemoveGameFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveGameFromOrderServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer gameId=Integer.parseInt(request.getParameter("id"));
		
		HttpSession session=request.getSession();
		GameOrder order=(GameOrder) session.getAttribute("order");
		Set<OrderDetail> listOrder=order.getOrderDetails();
		Iterator<OrderDetail> itr=listOrder.iterator();
		while(itr.hasNext()) {
			OrderDetail orderDetail=itr.next();
			if(orderDetail.getGame().getGameId()==gameId) {
				float newTotal=order.getTotal()-orderDetail.getSubtotal();
				order.setTotal(newTotal);
				itr.remove();
			}
		}
		String filePath="../admin/edit_order.jsp";
		RequestDispatcher rqd=request.getRequestDispatcher(filePath);
		rqd.forward(request, response);
	}
}
