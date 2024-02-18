package com.controller.admin;

import java.io.IOException;

import com.dao.CustomerDao;
import com.dao.GameDao;
import com.dao.OrderDao;
import com.dao.ReviewDao;
import com.dao.UserDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrderDao orderDao=new OrderDao();
		ReviewDao reviewDao=new ReviewDao();
		UserDao userDao=new UserDao();
		GameDao gameDao=new GameDao();
		CustomerDao custDao=new CustomerDao();
	
		req.setAttribute("listMostRecentSales", orderDao.listMostRecentOrder());
		req.setAttribute("listMostRecentReviews", reviewDao.listMostRecentReviews());
		req.setAttribute("totalUsers", userDao.count());
		req.setAttribute("totalGames", gameDao.count());
		req.setAttribute("totalCustomers", custDao.count());
		req.setAttribute("totalReviews", reviewDao.count());
		req.setAttribute("totalOrders", orderDao.count());
		
		String filePath="index.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
