package net.bulldozer.tourofall.review.service;

import net.bulldozer.tourofall.review.dto.Review;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModelsSet;

public interface ReviewService {
	public void registerNewReview(ReviewRegistrationForm registrationReviewForm);
	public Review findByUserIdAndItemId(long id,int itemId);
	public int getReviewCountByItemId(int itemId);
	public ReviewRenderingModelsSet getReviewRenderingModelsSet(int itemId, int index);
}
