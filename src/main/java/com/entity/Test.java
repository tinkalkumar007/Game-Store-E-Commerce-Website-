package com.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dao.ReviewDao;
import com.entity.Users;

public class Test {

	public static void main(String[] args) {
		//EntityManagerFactory factory=Persistence.createEntityManagerFactory("JavaProject");
		//EntityManager em=factory.createEntityManager();
		ReviewDao revDao=new ReviewDao();
		Customer cust=new Customer();
		cust.setCustomerId(1);
		
		Game game=new Game();
		game.setGameId(1);
		
		Review review=new Review();
		review.setCustomer(cust);
		review.setGame(game);
		review.setComment("This is very good Game.!");
		review.setRating(5);
		review.setHeadline("What a Game? Just Wowwww!");
		revDao.create(review);
	}
}
