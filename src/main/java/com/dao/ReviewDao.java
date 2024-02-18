package com.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Customer;
import com.entity.Review;

public class ReviewDao extends JpaDao<Review> implements GenericDao<Review> {

	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}

	@Override
	public Review update(Review review) {
		return super.update(review);
	}

	@Override
	public Review get(Object id) {
		return super.get(Review.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Review.class, id);
		return;
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.listAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countAll");
	}
	
	public Review findByCustomerAndGame(Integer customerId,Integer gameId) {
		Map<String,Object> mp=new HashMap<>();
		mp.put("customerId", customerId);
		mp.put("gameId", gameId);
		List<Review> reviewList=super.findWithNamedQuery("Review.findByCustomerAndGame",mp);
		if(!reviewList.isEmpty()) {
			return reviewList.get(0);
		}
		return null;
	}
	public List<Review> listMostRecentReviews(){
		return super.findWithNamedQuery("Review.listAll", 0, 3);
	}
}
