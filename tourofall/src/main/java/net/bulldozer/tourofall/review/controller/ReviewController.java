package net.bulldozer.tourofall.review.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.service.ReviewService;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.user.service.UserService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TourApiService tourApiService;
	
	@RequestMapping(value = "/write/{itemTypeId}/{itemId}", method = RequestMethod.GET)
	public String showReviewForm(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) {
		ReviewRegistrationForm reviewRegistrationForm = new ReviewRegistrationForm(); 
		reviewRegistrationForm.setItemId(itemId);
		reviewRegistrationForm.setItemTypeId(itemTypeId);
		if (!model.containsAttribute("reviewRegistrationForm"))
			model.addAttribute("reviewRegistrationForm", reviewRegistrationForm);

		if (!model.containsAttribute("result")) {
			System.out.println("Binding result not rendered");
		}
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(userAuthenticationDetails.getId());
		reviewRegistrationForm.setItemTitle(tourApiService.getItemTitle(reviewRegistrationForm.getItemId()));
		reviewService.registerNewReview(reviewRegistrationForm, user);
		return "redirect:/dest/info/basic/" + reviewRegistrationForm.getItemTypeId() +"/"+reviewRegistrationForm.getItemId();
	}
}
