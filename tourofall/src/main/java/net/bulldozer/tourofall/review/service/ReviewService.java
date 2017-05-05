package net.bulldozer.tourofall.review.service;

import java.util.List;

import net.bulldozer.tourofall.review.model.Review;

public interface ReviewService {
	public List<Review> getReviewsByItemId(int itemId);
	public void registerNewReview(Review review);
}
