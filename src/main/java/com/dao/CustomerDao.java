package com.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Customer;

public class CustomerDao extends JpaDao<Customer> implements GenericDao<Customer>{

	@Override
	public Customer create(Customer t) {
		t.setRegisterDate(new Date());
		return super.create(t);
	}

	@Override
	public Customer update(Customer t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public Customer get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Customer.class, id);
		return;
	}

	@Override
	public List<Customer> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Customer.countAll");
	}
	
	public Customer findByEmail(String email) {
		List<Customer> res=super.findWithNamedQuery("Customer.findByEmail", "email", email);
		if(!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
	public Customer checkLogin(String email,String password) {
		Map<String,Object> mp=new HashMap();
		mp.put("email", email);
		mp.put("pass",password);
		List<Customer> custList=super.findWithNamedQuery("Customer.checkLogin", mp);
		if(custList.size()>0) {
			return custList.get(0);
		}
		return null;
	}
}
