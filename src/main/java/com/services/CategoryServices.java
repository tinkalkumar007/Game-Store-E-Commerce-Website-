package com.services;

import java.io.IOException;
import java.util.List;

import com.dao.CategoryDao;
import com.dao.GameDao;
import com.entity.Category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryServices {
	HttpServletRequest request;
	HttpServletResponse response;
	CategoryDao catDao;
	
	public CategoryServices(HttpServletRequest request,HttpServletResponse response) {
		this.request=request;
		this.response=response;
		catDao=new CategoryDao();
	}
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}
	public void listCategory(String msg) throws ServletException, IOException{
		request.setAttribute("listCategory", catDao.listAll());
		if(msg!=null) {
			request.setAttribute("message", msg);
		}
		RequestDispatcher rqd=request.getRequestDispatcher("listCategory.jsp");
		rqd.forward(request, response);
	}
	public void createCategory() throws ServletException, IOException {
		String name=request.getParameter("name");
		Category obj=catDao.findByName(name);
		
		if(obj!=null) {
			String msg="Category with name "+name+" already exists!";
			request.setAttribute("message", msg);
			
			RequestDispatcher rqd=request.getRequestDispatcher("message.jsp");
			rqd.forward(request, response);
		}
		else {
			Category cat=new Category(name);
			catDao.create(cat);
			listCategory("New category has been created successfully!");
		}
	}
	public void deleteCategory() throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		
		GameDao gameDao = new GameDao();
		long res=gameDao.countByCategory(id);
		String msg=null;
		if(res>0) {
			msg="Could not delete the category(ID: %d) because it currently contains some Games.";
			msg=String.format(msg, res);
		}
		else {
			catDao.delete(id);
			msg="Category with id "+id +" has been deleted successfully!";
		}
		listCategory(msg);
	}
	
	public void updateCategory() throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("categoryId"));
		String name=request.getParameter("name");
		
		Category newCat=catDao.findByName(name);
		if(newCat!=null && newCat.getCategoryId()!=id) {
			String msg="Category with name "+name + " already exists!";
			request.setAttribute("message", msg);
			RequestDispatcher rqd=request.getRequestDispatcher("message.jsp");
			rqd.forward(request, response);
		}
		else {
			Category cat=new Category(id,name);
			catDao.update(cat);
			listCategory("Category has been updated successfully!");
		}
	}
}
