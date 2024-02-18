package com.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.controller.frontend.shoppingcart.ShoppingCart;
import com.dao.CategoryDao;
import com.dao.GameDao;
import com.dao.OrderDao;
import com.entity.Customer;
import com.entity.Game;
import com.entity.GameOrder;
import com.entity.OrderDetail;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderServices extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest req;
	HttpServletResponse resp;
	OrderDao orderDao;
	public OrderServices(HttpServletRequest request,HttpServletResponse response) {
		req=request;
		resp=response;
		orderDao=new OrderDao();
	}
	public void listOrders() throws ServletException, IOException {
		listOrders(null);
	}

	
	public void listOrders(String msg) throws ServletException, IOException {
		List<GameOrder> orderList=orderDao.listAll();
		req.setAttribute("listOrders", orderList);
		if(msg!=null) {
			req.setAttribute("message",msg);
		}
		String filePath="../admin/listOrders.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void viewOrderAdmin() throws ServletException, IOException {
		Integer orderId=Integer.parseInt(req.getParameter("id"));
		req.setAttribute("order", orderDao.get(orderId));
		
		String filePath="../admin/order_detail.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void showCheckForm() throws ServletException, IOException {
		String filePath="frontend/checkout.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void placeOrder() throws ServletException, IOException {
		HttpSession session=req.getSession();
		Customer cust=(Customer)session.getAttribute("loggedCustomer");
		
		String recipientFName=req.getParameter("firstName");
		String recipientLName=req.getParameter("lastName");
		String recipientPhone=req.getParameter("phone");
		String recipientAddr1=req.getParameter("addrLine1");
		String recipientAddr2=req.getParameter("addrLine2");
		String city=req.getParameter("city");
		String state=req.getParameter("state");
		String zipCode=req.getParameter("zipCode");
		String country=req.getParameter("country");
		//String addr=recipientAddr+", "+city+", "+state+", "+country+", "+zipCode;
		String paymentMethod=req.getParameter("payment_method");
		
		GameOrder gameOrder=new GameOrder();
		gameOrder.setCustomer(cust);
		gameOrder.setFirstname(recipientFName);
		gameOrder.setLastname(recipientLName);
		gameOrder.setPhone(recipientPhone);
		gameOrder.setAddressLine1(recipientAddr1);
		gameOrder.setAddressLine2(recipientAddr2);
		gameOrder.setCity(city);
		gameOrder.setState(state);
		gameOrder.setZipcode(zipCode);
		gameOrder.setCountry(country);
		gameOrder.setOrderDate(new Date());
		gameOrder.setPaymentMethod(paymentMethod);
		
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("cart");
		Map<Game,Integer> items=shoppingCart.getItems();
		
		Iterator<Game> itr=items.keySet().iterator();
		Set<OrderDetail> orderDetails=new HashSet<OrderDetail>();
		while(itr.hasNext()) {
			Game game=itr.next();
			Integer quantity=items.get(game);
			float subTotal=quantity*game.getPrice();
			
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setGame(game);
			orderDetail.setGameOrder(gameOrder);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subTotal);
			orderDetails.add(orderDetail);
		}
		gameOrder.setOrderDetails(orderDetails);
		gameOrder.setTotal(shoppingCart.getTotalAmount());
		orderDao.create(gameOrder);
		shoppingCart.clear();
		
		String msg="Thank You. Your order has been recieved!";
		req.setAttribute("message", msg);
		String filePath="frontend/message.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void listOrderCustomer() throws ServletException, IOException {
		HttpSession session=req.getSession();
		Customer cust=(Customer) session.getAttribute("loggedCustomer");
		List<GameOrder> orderList=orderDao.listByCustomer(cust.getCustomerId());
		
		req.setAttribute("listOrder", orderList);
		String filePath="frontend/order_list.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void showOrderDetailForCustomer() throws ServletException, IOException {
		Integer orderId=Integer.parseInt(req.getParameter("id"));
		HttpSession session=req.getSession();
		Customer cust=(Customer) session.getAttribute("loggedCustomer");
		
		GameOrder gameOrder=orderDao.get(cust.getCustomerId(),orderId);
		req.setAttribute("order", gameOrder);
		
		String filePath="frontend/order_detail.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	
	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId=Integer.parseInt(req.getParameter("id"));
		
		
		HttpSession session=req.getSession();
		Object newAddedGame=session.getAttribute("AddNewGame");
		if(newAddedGame==null) {
			GameOrder gameOrder=orderDao.get(orderId);
			session.setAttribute("order", gameOrder);
		}
		else {
			session.removeAttribute("AddNewGame");
		}
		
		String filePath="../admin/edit_order.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void updateOrder() throws ServletException, IOException {
		HttpSession session=req.getSession();
		GameOrder gameOrder=(GameOrder) session.getAttribute("order");
		
		String recipientName=req.getParameter("recipientName");
		String recipientNo=req.getParameter("recipientNo");
		String shippingAddr=req.getParameter("shipTo");
		String paymentMethod=req.getParameter("paymentMethod");
		String orderStatus=req.getParameter("orderStatus");
		
		gameOrder.setFirstname(recipientName);
		gameOrder.setAddressLine1(shippingAddr);
		gameOrder.setPhone(recipientNo);
		gameOrder.setPaymentMethod(paymentMethod);
		gameOrder.setStatus(orderStatus);
		
		String[] gameIds=req.getParameterValues("gameId");
		String[] prices=req.getParameterValues("price");
		String[] quantities=new String[gameIds.length];
		
		for(int i=1;i<=gameIds.length;i++) {
			quantities[i-1]=req.getParameter("quantity"+i);
		}
		Set<OrderDetail> orderDetails=gameOrder.getOrderDetails();
		orderDetails.clear();
		
		float totalAmount=0.0f;
		for(int i=0;i<gameIds.length;i++) {
			int gameId=Integer.parseInt(gameIds[i]);
			float price=Float.parseFloat(prices[i]);
			int quantity=Integer.parseInt(quantities[i]);
			
			float subTotal=price*quantity;
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setGame(new Game(gameId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subTotal);
			orderDetail.setGameOrder(gameOrder);
			
			orderDetails.add(orderDetail);
			
			totalAmount+=subTotal;
		}
		gameOrder.setTotal(totalAmount);
		
		orderDao.update(gameOrder);
		
		String msg="Order with order ID: "+gameOrder.getOrderId()+" has been updated successfully";
		listOrders(msg);
	}
	public void deleteOrder() throws ServletException, IOException {
		Integer orderId=Integer.parseInt(req.getParameter("id"));
		orderDao.delete(orderId);
		String msg="The Order "+orderId+" deleted successfully";
		listOrders(msg);
	}
}
