package net.bulldozer.tourofall.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.evaluation.model.Evaluation;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.model.Review;
import net.bulldozer.tourofall.review.repository.ReviewRepository;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.user.repository.UserRepository;

@Service
public class ReviewRepositoryService implements ReviewService{
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly=true)
	public List<Review> getReviewsByItemId(int itemId){
		List<Review> reviewList = reviewRepository.findByItemId(itemId);
		return reviewList;
	}
	@Transactional
	public void registerNewReview(ReviewRegistrationForm reviewRegistrationForm) {
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User getUser = userRepository.findOne(userAuthenticationDetails.getId());
		
		Evaluation getEvaluation = evaluationRepository.findByUserIdAndItemId(userAuthenticationDetails.getId(), reviewRegistrationForm.getItemId());
		if(getEvaluation == null){
			getEvaluation = new Evaluation();
			getEvaluation.setUser(getUser);
			getEvaluation.setItemId(reviewRegistrationForm.getItemId());;
		}
		getEvaluation.setScore(reviewRegistrationForm.getScore());
		
		
		Review newReview = Review.getBuilder()
				.title(reviewRegistrationForm.getTitle())
				.content(reviewRegistrationForm.getContent())
				.itemId(reviewRegistrationForm.getItemId())
				.user(getUser)
				.evaluation(getEvaluation)
				.build();
		reviewRepository.save(newReview);
	}
}
