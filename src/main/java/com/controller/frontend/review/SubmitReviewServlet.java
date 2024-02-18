package com.controller.frontend.review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.ReviewServices;
@WebServlet("/submit_review")
public class SubmitReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SubmitReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ReviewServices reviewServe=new ReviewServices(request,response);
		reviewServe.submitReview();
	}

}
