package net.bulldozer.tourofall.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.review.model.Review;
import net.bulldozer.tourofall.review.repository.ReviewRepository;

@Service
public class RepositoryReviewService implements ReviewService{
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional(readOnly=true)
	public List<Review> getReviewsByItemId(int itemId){
		List<Review> reviewList = reviewRepository.findByItemId(itemId);
		return reviewList;
	}
	@Transactional
	public void registerNewReview(Review review) {
		reviewRepository.save(review);
	}
}
