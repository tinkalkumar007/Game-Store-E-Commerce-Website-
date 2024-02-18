package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Users;


public class UserDao extends JpaDao<Users> implements GenericDao<Users>{
	public UserDao() {
	}
	@Override
	public Users create(Users u) {
		return super.create(u);
	}
	@Override
	public Users update(Users u) {
		return super.update(u);
	}
	@Override
	public Users get(Object userId) {
		return super.get(Users.class, userId);
	}
	
	public Users findByEmail(String email){
		List<Users> res=super.findWithNamedQuery("Users.findByEmail", "email", email);
		if(res!=null && res.size()>0) {
			return res.get(0);
		}
		return null;
	}
	public boolean checkLogin(String email,String password) {
		Map<String,Object> parameters=new HashMap<>(); 
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Users> listUsers=super.findWithNamedQuery("Users.checkLogin",parameters);
		if(listUsers.size()==1) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}
	@Override
	public long count() {
		return super.countWithNamedQuery("Users.countAll");
	}
	@Override
	public void delete(Object id) {
		super.delete(Users.class,id);
		return;
	}
}