package com.controller.admin.order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.dao.GameDao;
import com.entity.Game;
import com.services.GameServices;
import com.services.OrderServices;

@WebServlet("/admin/add_game_form")
public class AddGameFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public AddGameFormServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		GameDao gameDao=new GameDao();
		List<Game> games=gameDao.listAll();
		request.setAttribute("gameList", games);
		
		String filePath="../admin/add_game_form.jsp";
		RequestDispatcher rqd=request.getRequestDispatcher(filePath);
		rqd.forward(request, response);
    }
}
