package com.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.Customer;
import com.entity.Game;
import com.entity.Review;

public class ReviewDaoTest {
	private static ReviewDao revDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		revDao=new ReviewDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		revDao.close();
	}

	@Test
	public void testCreateReview() {
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
		Review rev=revDao.create(review);
		assertTrue(rev.getReviewId()>0);
	}

	@Test
	public void testUpdateReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

}
