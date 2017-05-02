package net.bulldozer.tourofall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bulldozer.tourofall.dao.ReviewDao;
import net.bulldozer.tourofall.model.Review;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao dao;
	
	public List<Review> getReviewsByItemId(int itemId) {
		return dao.getReviewsByItemId(itemId);
	}

	public void addReview(Review review) {
		dao.addReview(review);
	}
}
