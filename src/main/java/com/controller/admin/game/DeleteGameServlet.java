package com.controller.admin.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.GameServices;

@WebServlet("/admin/delete_game")
public class DeleteGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteGameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameServices gameServe=new GameServices(request,response);
		gameServe.deleteGame();
	}
}
