package net.bulldozer.tourofall.review.service;

import java.util.List;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.review.dto.Review;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.user.dto.User;

public interface ReviewService {
	public List<Review> getReviewsByItemId(int itemId);
	public void registerNewReview(ReviewRegistrationForm registrationReviewForm);
	public Review findByUserIdAndItemId(long id,int itemId);
}
