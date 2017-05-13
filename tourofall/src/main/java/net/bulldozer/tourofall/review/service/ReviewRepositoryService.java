package net.bulldozer.tourofall.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.model.Review;
import net.bulldozer.tourofall.review.repository.ReviewRepository;
import net.bulldozer.tourofall.user.model.User;

@Service
public class ReviewRepositoryService implements ReviewService{
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional(readOnly=true)
	public List<Review> getReviewsByItemId(int itemId){
		List<Review> reviewList = reviewRepository.findByItemId(itemId);
		return reviewList;
	}
	@Transactional
	public void registerNewReview(ReviewRegistrationForm registrationReviewForm, User user) {
		Review newReview = Review.getBuilder()
				.title(registrationReviewForm.getTitle())
				.content(registrationReviewForm.getContent())
				.itemId(registrationReviewForm.getItemId())
				.itemTypeId(registrationReviewForm.getItemTypeId())
				.itemTitle(registrationReviewForm.getItemTitle())
				.user(user)
				.build();
		reviewRepository.save(newReview);
	}
}
