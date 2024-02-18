package com.controller.admin.category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.CategoryDao;
import com.entity.Category;

@WebServlet("/admin/edit_category")
public class EditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		CategoryDao catDao=new CategoryDao();
		request.setAttribute("category", catDao.get(id));
		RequestDispatcher rqd=request.getRequestDispatcher("category_form.jsp");
		rqd.forward(request, response);
	}
}
