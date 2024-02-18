package com.controller.admin.review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.ReviewServices;

@WebServlet("/admin/delete_review")
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ReviewServices reviewServe = new ReviewServices(request,response);
		reviewServe.deleteReview();
	}
}