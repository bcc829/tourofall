package net.bulldozer.tourofall.review.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.review.dto.Review;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModel;
import net.bulldozer.tourofall.review.repository.ReviewRepository;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
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
	public List<ReviewRenderingModel> getReviewRenderingModelsByItemId(int itemId){
		List<ReviewRenderingModel> reviewRenderingModels = new ArrayList<ReviewRenderingModel>();
		List<Review> reviews = reviewRepository.findByItemId(itemId);
		Iterator<Review> reviewIter = reviews.iterator();
		while(reviewIter.hasNext()){
			Review review = reviewIter.next();
			ReviewRenderingModel reviewRenderingModel = ReviewRenderingModel.getBuilder()
														.userId(review.getUser().getId())
														.title(review.getTitle())
														.content(review.getContent())
														.createdDate(review.getCreatedDate())
														.lastName(review.getUser().getLastName())
														.firstName(review.getUser().getFirstName())
														.score(review.getEvaluation().getScore())
														.itemTitle(review.getItemTitle())
														.build();
			reviewRenderingModels.add(reviewRenderingModel);
		}
		return reviewRenderingModels;
	}
	@Transactional
	public void registerNewReview(ReviewRegistrationForm reviewRegistrationForm) {
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User getUser = userRepository.findOne(userAuthenticationDetails.getId());
		
		Evaluation getEvaluation = evaluationRepository.findByUserIdAndItemId(userAuthenticationDetails.getId(), reviewRegistrationForm.getItemId());
		if(getEvaluation == null){
			getEvaluation = Evaluation.getBuilder()
							.itemId(reviewRegistrationForm.getItemId())
							.build();
		}
		
		getEvaluation.setScore(reviewRegistrationForm.getScore());
		
		
		Review newReview = Review.getBuilder()
				.title(reviewRegistrationForm.getTitle())
				.content(reviewRegistrationForm.getContent())
				.itemId(reviewRegistrationForm.getItemId())
				.evaluation(getEvaluation)
				.build();
		
		getUser.addReview(newReview);
		getUser.addEvaluation(getEvaluation);
		
		reviewRepository.save(newReview);
	}
	@Transactional(readOnly=true)
	@Override
	public Review findByUserIdAndItemId(long id,int itemId) {
		return reviewRepository.findByUserIdAndItemId(id,itemId);
	}
}
