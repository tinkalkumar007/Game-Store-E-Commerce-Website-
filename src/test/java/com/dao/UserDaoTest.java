package com.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.Users;

public class UserDaoTest {

	private static UserDao userDAO;
	
	@BeforeClass
	public static void setupclass() throws Exception {
		userDAO=new UserDao();
	}
	
	@Test
	public void testCreateUsers() {
		Users user1=new Users(); 
		user1.setEmail("kruskals.com");
		user1.setFullName("krus");
		user1.setPassword("spanning");
		 
		user1=userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test
	public void testUpdateUsers() {
		Users user =new Users();
		user.setUserId(1);
		user.setEmail("name@codejava.net");
		user.setFullName("ANkur");
		user.setPassword("mysecret");
		
		user=userDAO.update(user);
		String expected ="mysecret";
		String actual=user.getPassword();
		System.out.println(user.getPassword());
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId=1;
		Users user= userDAO.get(userId);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user= userDAO.get(userId);
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId=16;
		userDAO.delete(userId);
			
		Users user= userDAO.get(userId);
		assertNull(user);
	}
	
	@Test(expected= Exception.class)
	public void testDeleteNonExistUsers() {
		Integer userId=99;
		userDAO.delete(userId);
	}
	
	@Test
	public void tsetListAll() {
		List<Users> listUsers=userDAO.listAll();
		assertTrue(listUsers.size() > 0 );
	}
	
	@Test
	public void testCount() {
		long totalUsers=userDAO.count();
		assertTrue(totalUsers>0);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email="joker@gmail.com";
		String password="12345";
		
		boolean loginResult=userDAO.checkLogin(email, password);
		
		assertTrue(loginResult);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email="name@codejav.net";
		String password="mysecret";
		
		boolean loginResult=userDAO.checkLogin(email, password);
		
		assertFalse(loginResult);
	}
	
	@Test
	public void testFindByEmail() {
		String email="name@codejava.net";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.close();
	}
	
}
