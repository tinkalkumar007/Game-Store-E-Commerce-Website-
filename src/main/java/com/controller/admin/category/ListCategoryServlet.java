package com.controller.admin.category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.CategoryDao;
import com.services.CategoryServices;
@WebServlet("/admin/list_category")
public class ListCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ListCategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryServices catServe= new CategoryServices(request,response);
		catServe.listCategory();
	}

}
