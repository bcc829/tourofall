package net.bulldozer.tourofall.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.user.service.UserService;

@Controller
@RequestMapping("/myinfo")
public class MyInfoController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showMyInfoHome(){
		return "myinfo-home";
	}
	@RequestMapping("/detail")
	public String showMyInfoDetail(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		model.addAttribute("userAuthenticationDetails", userAuthenticationDetails);
		return "myinfo-detail";
	}
	@RequestMapping("/reviews")
	public String showMyInfoReviews(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(userAuthenticationDetails.getId());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("reviews", user.getReviews());
		return "myinfo-reviews";
	}
	@RequestMapping("/questions")
	public String showMyInfoQuestions(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(userAuthenticationDetails.getId());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("questions", user.getQuestions());
		return "myinfo-questions";
	}
	@RequestMapping("/answers")
	public String showMyInfoAnswers(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(userAuthenticationDetails.getId());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("answers", user.getAnswers());
		return "myinfo-answers";
	}
	@RequestMapping("/evals")
	public String showMyInfoEvals(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(userAuthenticationDetails.getId());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("evaluations", user.getEvaluations());
		return "myinfo-evals";
	}
}
