package net.bulldozer.tourofall.review.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.evaluation.model.Evaluation;
import net.bulldozer.tourofall.evaluation.service.EvaluationService;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
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
		reviewRegistrationForm.setItemTypeId(itemTypeId);
		
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
	public String processRegisterReview(@Valid ReviewRegistrationForm reviewRegistrationForm, BindingResult result, RedirectAttributes model) throws Exception {
		if (result.hasErrors()) {
			model.addFlashAttribute("reviewRegistrationForm", reviewRegistrationForm);
			model.addFlashAttribute("org.springframework.validation.BindingResult.reviewRegistrationForm", result);
			return "redirect:/review/write/" + reviewRegistrationForm.getItemTypeId() +"/"+reviewRegistrationForm.getItemId();
		}
		
		reviewService.registerNewReview(reviewRegistrationForm);
		return "redirect:/dest/info/basic/" + reviewRegistrationForm.getItemTypeId() +"/"+reviewRegistrationForm.getItemId();
	}
	
	@ResponseStatus(value=HttpStatus.CONFLICT,reason="이미 있는 리뷰 내용입니다. ")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String processingDuplicatedReviews(Model model,DataIntegrityViolationException ex){
		
		model.addAttribute("errorMsg", ex.getMessage());
		
		return "duplicated_review";
	}
}
