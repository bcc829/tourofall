package net.bulldozer.tourofall.review.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.common.dto.Response;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.service.EvaluationService;
import net.bulldozer.tourofall.review.dto.Review;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModel;
import net.bulldozer.tourofall.review.service.ReviewService;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.service.UserService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EvaluationService evaluationService;
	
	
	@RequestMapping(value = "/write/{itemTypeId}/{itemId}", method = RequestMethod.GET)
	public String showReviewForm(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) {
		ReviewRegistrationForm reviewRegistrationForm = new ReviewRegistrationForm(); 
		reviewRegistrationForm.setItemId(itemId);
		
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Evaluation evaluation = evaluationService.findByUserIdAndItemId(userAuthenticationDetails.getId(),itemId);
		if(evaluation != null){
			reviewRegistrationForm.setScore(evaluation.getScore());
		}
		
		
		if (!model.containsAttribute("reviewRegistrationForm"))
			model.addAttribute("reviewRegistrationForm", reviewRegistrationForm);
		
		
		model.addAttribute("firstName", userAuthenticationDetails.getFirstName());
		model.addAttribute("lastName", userAuthenticationDetails.getLastName());
		return "review_write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> processRegisterReview(@RequestBody @Valid ReviewRegistrationForm reviewRegistrationForm, BindingResult result) throws Exception {
		System.out.println("Entered processRegisterReview");
		if (result.hasErrors()) {
			List<ObjectError> errors =  result.getAllErrors();
		
			return new ResponseEntity<List<ObjectError>>(errors,HttpStatus.CONFLICT);
		}
		Review review = reviewService.registerNewReview(reviewRegistrationForm);
		if(review == null){
			return new ResponseEntity<Response>(new Response(false,"Review","등록이 실패되었습니다."),HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(new Response(true,"Review","등록을 성공하였습니다."),HttpStatus.OK); 
	}
	
	@ResponseStatus(value=HttpStatus.CONFLICT,reason="이미 있는 리뷰 내용입니다. ")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String processingDuplicatedReviews(Model model,DataIntegrityViolationException ex){
		
		model.addAttribute("errorMsg", ex.getMessage());
		
		return "duplicated_review";
	}
}
