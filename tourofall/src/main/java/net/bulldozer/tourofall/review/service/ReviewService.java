package net.bulldozer.tourofall.review.service;

import java.util.List;

import net.bulldozer.tourofall.evaluation.model.Evaluation;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.model.Review;
import net.bulldozer.tourofall.user.model.User;

public interface ReviewService {
	public List<Review> getReviewsByItemId(int itemId);
	public void registerNewReview(ReviewRegistrationForm registrationReviewForm);
}
