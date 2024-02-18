package com.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.entity.Game;

public class ShoppingCart {
	private Map<Game,Integer> cart = new HashMap<>();
	public ShoppingCart() {
		
	}
	
	public void addItem(Game game) {
		if(cart.containsKey(game)) {
		     Integer quantity=cart.get(game)+1;
		     cart.put(game,quantity);
		}
		else {
			cart.put(game, 1);
		}
	}
	public void removeItem(Game game) {
		cart.remove(game);
	}
	
	public void updateCart(int[] gameIds,int[] quantities) {
		for(int i=0;i<gameIds.length;i++) {
			 Game key=new Game(gameIds[i]);
			 Integer value=quantities[i];
			 cart.put(key, value);
		}
	}
	
	public Integer getTotalQuantity() {
		Integer total=0;
		Iterator<Game> itr=cart.keySet().iterator();
		while(itr.hasNext()) {
			Game next = itr.next();
			Integer quantity=cart.get(next);
			total+=quantity;
		}
		return total;
	}
	
	public float getTotalAmount() {
		float total=0.0f;
		Iterator<Game> itr=cart.keySet().iterator();
		while(itr.hasNext()) {
			Game next = itr.next();
			Integer quantity=cart.get(next);
			float money=next.getPrice();
			total+=(quantity*money);
		}
		return total;
	}
	public void clear() {
		cart.clear();
	}
	public int getTotalItems() {
		return cart.size();
	}
	public Map<Game,Integer> getItems(){
		return this.cart;
	}
}
