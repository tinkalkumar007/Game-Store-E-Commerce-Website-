package com.controller.admin.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.GameServices;

@WebServlet("/admin/update_game")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10, // 10KB
		maxFileSize = 1024 * 300, // 300KB
		maxRequestSize = 1024 * 1024 // 1MB
)
public class UpdateGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdateGameServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameServices gameServe=new GameServices(request,response);
		gameServe.updateGame();
	}

}
