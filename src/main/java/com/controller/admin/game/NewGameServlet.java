package com.controller.admin.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.GameServices;

@WebServlet("/admin/new_game")
public class NewGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public NewGameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameServices gameServe=new GameServices(request,response);
		gameServe.newGame();
	}

}
