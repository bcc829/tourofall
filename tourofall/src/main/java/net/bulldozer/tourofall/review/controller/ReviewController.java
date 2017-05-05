package net.bulldozer.tourofall.review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.review.dto.RegistrationReviewForm;
import net.bulldozer.tourofall.review.service.ReviewService;
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
	public String showReviewForm(@PathVariable int itemId,@PathVariable int itemTypeId, Model model, HttpServletRequest request) {
		RegistrationReviewForm registrationReviewForm = new RegistrationReviewForm(); 
		registrationReviewForm.setItemId(itemId);
		registrationReviewForm.setItemTypeId(itemTypeId);
		if (!model.containsAttribute("registrationReviewForm"))
			model.addAttribute("registrationReviewForm", registrationReviewForm);

		if (!model.containsAttribute("result")) {
			System.out.println("Binding result not rendered");
		}
		model.addAttribute("username",
				userService.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName())).getUsername());
		return "review_write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String processRegisterReview(@Valid RegistrationReviewForm registrationReviewForm, BindingResult result, RedirectAttributes model,
			HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			model.addFlashAttribute("registrationReviewForm", registrationReviewForm);
			model.addFlashAttribute("org.springframework.validation.BindingResult.registrationReviewForm", result);
			return "redirect:/review/write/" + registrationReviewForm.getItemTypeId() +"/"+registrationReviewForm.getItemId();
		}
		User user = userService.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName()));
		registrationReviewForm.setItemTitle(tourApiService.getItemTitle(registrationReviewForm.getItemId()));
		reviewService.registerNewReview(registrationReviewForm, user);
		return "redirect:/dest/info/basic/" + registrationReviewForm.getItemTypeId() +"/"+registrationReviewForm.getItemId();
	}
}
