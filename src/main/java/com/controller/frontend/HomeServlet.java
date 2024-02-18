package com.controller.frontend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.dao.CategoryDao;
import com.dao.GameDao;
import com.entity.Game;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public HomeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GameDao gameDao = new GameDao();
		
		request.setAttribute("listNewGames", gameDao.listNewGames());
		request.setAttribute("listBestSellingGames", gameDao.bestSellingGames());
		request.setAttribute("listMostRatedGames", gameDao.listMostFavoriteGames());
		
		String filePath="frontend/index.jsp";
		RequestDispatcher rqd=request.getRequestDispatcher(filePath);
		rqd.forward(request, response);
	}
}
