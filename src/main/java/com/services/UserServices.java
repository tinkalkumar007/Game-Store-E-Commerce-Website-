package com.services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dao.UserDao;
import com.entity.Users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UserServices {
	EntityManagerFactory factory;
	EntityManager em;
	UserDao userDao;
	HttpServletRequest request;
	HttpServletResponse response;
	
	public UserServices(HttpServletRequest request,HttpServletResponse response) {
		this.request=request;
		this.response=response;
		factory=Persistence.createEntityManagerFactory("JavaProject");
		em=factory.createEntityManager();
		userDao=new UserDao();
	}
	
	public void listUser() throws ServletException, IOException{
		listUser(null);
	}
	
	public void listUser(String msg) throws ServletException, IOException{
		
		request.setAttribute("listUsers",userDao.listAll());
		String filePath="listUsers.jsp";
		if(msg!=null) request.setAttribute("message",msg);
		RequestDispatcher rqd=request.getRequestDispatcher(filePath);
		rqd.forward(request,response);
	}
	public void CreateUser() throws ServletException, IOException{
		String email=request.getParameter("email");
		String fullName=request.getParameter("name");
		String pwd=request.getParameter("password");
		
		Users u=userDao.findByEmail(email);
		if(u!=null) {
			String msg="User with email "+email+" already exist!";
			request.setAttribute("message", msg);
			RequestDispatcher rqd=request.getRequestDispatcher("message.jsp");
			rqd.forward(request, response);
		}
		else {
			Users newUser=new Users(email,fullName,pwd);
			userDao.create(newUser);
			listUser("New User Created Successfully!");
		}
	}
	public void editUser() throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		Users u=userDao.get(id);
		String filePath="user_form.jsp";
		request.setAttribute("user", u);
		
		RequestDispatcher rqd=request.getRequestDispatcher(filePath);
		rqd.forward(request, response);
		//userDao.update(u);
	}
	public void updateUser() throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("userId"));
		String email=request.getParameter("email");
		String fullName=request.getParameter("name");
		String pwd=request.getParameter("password");
		
		Users getByEmail=userDao.findByEmail(email);
		if(id==1||(getByEmail!=null && getByEmail.getUserId()!=id)) {
			String msg = null;
			if(id!=1) msg="Couldn't edit user. User with email "+email+" already exist!";
			else msg="This is adminstration's account. You cann't update it!";
			request.setAttribute("message",msg);
			RequestDispatcher rqd=request.getRequestDispatcher("message.jsp");
			rqd.forward(request, response);
		}
		else {
			Users u=new Users(id,email,fullName,pwd);
			System.out.println(u);
			userDao.update(u);
			String msg="User has been updated successfully!";
			listUser(msg);
		}
	}
	public void deleteUser() throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		userDao.delete(id);
		listUser("User has been deleted successfully!");
	}
	public void loginUser() throws ServletException, IOException {
		String email=request.getParameter("email");
		String pwd=request.getParameter("password");
		if(userDao.checkLogin(email, pwd)) {
			request.getSession().setAttribute("useremail",email);
			RequestDispatcher rqd=request.getRequestDispatcher("/admin/");
			rqd.forward(request, response);
		}
		else {
			String msg="Email/password  is incorrect.";
			request.setAttribute("message", msg);
			RequestDispatcher rqd=request.getRequestDispatcher("login.jsp");
			rqd.forward(request, response);
		}
	}
}
