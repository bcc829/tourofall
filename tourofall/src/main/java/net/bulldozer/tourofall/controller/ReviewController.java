package net.bulldozer.tourofall.controller;

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

import net.bulldozer.tourofall.model.Review;
import net.bulldozer.tourofall.model.User;
import net.bulldozer.tourofall.service.ReviewService;
import net.bulldozer.tourofall.service.UserService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/write/{itemTypeId}/{itemId}", method = RequestMethod.GET)
	public String showReviewForm(@PathVariable int itemId,@PathVariable int itemTypeId, Model model, HttpServletRequest request) {
		Review review = new Review();
		review.setItemId(itemId);
		review.setItemTypeId(itemTypeId);
		if (!model.containsAttribute("review"))
			model.addAttribute("review", review);

		if (!model.containsAttribute("result")) {
			System.out.println("Binding result not rendered");
		}
		model.addAttribute("username",
				userService.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName())).getUsername());
		return "review_write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String processRegisterReview(@Valid Review review, BindingResult result, RedirectAttributes model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addFlashAttribute("review", review);
			model.addFlashAttribute("org.springframework.validation.BindingResult.review", result);
			return "redirect:/dest/info/review/write/" + review.getItemTypeId() +"/"+review.getItemId();
		}
		User user = userService.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName()));
		review.setUser(user);
		reviewService.addReview(review);

		return "redirect:/dest/info/basic/" + review.getItemTypeId() +"/"+review.getItemId();
	}
}
