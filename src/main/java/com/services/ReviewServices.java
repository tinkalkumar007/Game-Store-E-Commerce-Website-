package com.services;

import java.io.IOException;
import java.util.List;

import com.dao.GameDao;
import com.dao.ReviewDao;
import com.entity.Customer;
import com.entity.Game;
import com.entity.Review;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReviewServices {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private ReviewDao reviewDao;
	
	public ReviewServices(HttpServletRequest request,HttpServletResponse response) {
		super();
		req=request;
		resp=response;
		reviewDao=new ReviewDao();
	}
	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}
	public void listAllReview(String msg) throws ServletException, IOException {
		List<Review> listReview=reviewDao.listAll();
		req.setAttribute("listReview", listReview);
		
		if(msg!=null) {
			req.setAttribute("message", msg);
		}
		
		String filePath="listReview.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void editReview() throws ServletException, IOException {
		int reviewId=Integer.parseInt(req.getParameter("id"));
		Review review=reviewDao.get(reviewId);
		req.setAttribute("review", review);
		
		RequestDispatcher rqd=req.getRequestDispatcher("review_form.jsp");
		rqd.forward(req, resp);
	}
	public void updateReview() throws ServletException, IOException {
		int reviewId=Integer.parseInt(req.getParameter("reviewId"));
		Review review=reviewDao.get(reviewId);
		
		String headline=req.getParameter("headline");
		String comment=req.getParameter("comment");
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDao.update(review);
		String msg="Review has been updated successfully.";
		listAllReview(msg);
	}
	public void deleteReview() throws ServletException, IOException {
		int reviewId=Integer.parseInt(req.getParameter("id"));
		
		reviewDao.delete(reviewId);
		String msg="Review has been deleted successfully.";
		listAllReview(msg);
	}
	public void showReviewForm() throws ServletException, IOException {
		Customer cust=(Customer)req.getSession().getAttribute("loggedCustomer");
		Integer gameId=Integer.parseInt(req.getParameter("game_id"));
		
		GameDao gameDao=new GameDao();
		Game game=gameDao.get(gameId);
		
		req.setAttribute("game", game);
		
		Review review=reviewDao.findByCustomerAndGame(cust.getCustomerId(), gameId);
		String filePath="frontend/review_form.jsp";
		if(review!=null) {
			req.setAttribute("review", review);
			filePath="frontend/review_info.jsp";
		}
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void submitReview() throws ServletException, IOException {
		Integer gameId=Integer.parseInt(req.getParameter("game_id"));
		Integer rating=Integer.parseInt(req.getParameter("rating"));
		String headline=req.getParameter("headline");
		String comment=req.getParameter("comment");
		
		Review review=new Review();
		review.setComment(comment);
		review.setHeadline(headline);
		review.setRating(rating);
		
		GameDao gameDao=new GameDao();
		Game game=gameDao.get(gameId);
		
		review.setGame(game);
		
		Customer cust=(Customer) req.getSession().getAttribute("loggedCustomer");
		review.setCustomer(cust);
		
		reviewDao.create(review);
		req.setAttribute("game", game);
		String filePath="frontend/review_done.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}	
}
