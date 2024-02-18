package com.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.entity.GameOrder;

public class OrderDao extends JpaDao<GameOrder> implements GenericDao<GameOrder> {

	@Override
	public GameOrder create(GameOrder order) {
		order.setOrderDate(new Date());
		order.setStatus("Processing");
		return super.create(order);
	}

	@Override
	public GameOrder update(GameOrder order) {
		return super.update(order);
	}

	@Override
	public GameOrder get(Object id) {
		return super.get(GameOrder.class, id);
	}
	public GameOrder get(Integer customerId,Integer orderId) {
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("customerId", customerId);
		parameters.put("orderId", orderId);
		List<GameOrder> orderList=super.findWithNamedQuery("GameOrder.findByIdAndCustomer", parameters);
		if(!orderList.isEmpty()) {
			return orderList.get(0);
		}
		return null;
	}

	@Override
	public void delete(Object id) {
		super.delete(GameOrder.class, id);
	}

	@Override
	public List<GameOrder> listAll() {
		return super.findWithNamedQuery("GameOrder.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("GameOrder.countAll");
	}
	public List<GameOrder> listByCustomer(Integer customerId){
		return super.findWithNamedQuery("GameOrder.findByCustomer", "customerId", customerId);
	}
	
	public List<GameOrder> listMostRecentOrder(){
		return super.findWithNamedQuery("GameOrder.findAll", 0, 3);
	}
}
