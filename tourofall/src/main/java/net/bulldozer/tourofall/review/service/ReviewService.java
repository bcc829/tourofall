package net.bulldozer.tourofall.review.service;

import java.util.List;

import net.bulldozer.tourofall.review.dto.RegistrationReviewForm;
import net.bulldozer.tourofall.review.model.Review;
import net.bulldozer.tourofall.user.model.User;

public interface ReviewService {
	public List<Review> getReviewsByItemId(int itemId);
	public void registerNewReview(RegistrationReviewForm registrationReviewForm,User user);
}
