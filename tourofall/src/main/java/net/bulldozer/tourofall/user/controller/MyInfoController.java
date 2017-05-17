package net.bulldozer.tourofall.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.service.UserService;
import net.bulldozer.tourofall.user.util.DateList;

@Controller
@RequestMapping("/myinfo")
public class MyInfoController {
	@Autowired
	private UserService userService;

	private void addModelToView(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("questionCount", userService.getQuestionsSizeByUserId(userAuthenticationDetails.getId()));
		model.addAttribute("answerCount", userService.getAnswersSizeByUserId(userAuthenticationDetails.getId()));
		model.addAttribute("reviewCount", userService.getReviewsSizeByUserId(userAuthenticationDetails.getId()));
		model.addAttribute("evaluationCount", userService.getEvaluationsSizeByUserId(userAuthenticationDetails.getId()));
	}
	private void addDateList(Model model){
		model.addAttribute("years", DateList.getYearList());
		model.addAttribute("months", DateList.getMonthList());
		model.addAttribute("dates", DateList.getDateList());
	}
	@RequestMapping(method=RequestMethod.GET)
	public String showMyInfoHome(Model model){
		addModelToView(model);
		return "myinfo-home";
	}
	@RequestMapping("/setting")
	public String showMyInfoDetail(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addModelToView(model);
		addDateList(model);
		model.addAttribute("userAuthenticationDetails", userAuthenticationDetails);
		model.addAttribute("userModificationForm", userService.getUserModificationFormByUserId(userAuthenticationDetails.getId()));
		
		return "myinfo-detail";
	}
	@RequestMapping("/reviews")
	public String showMyInfoReviews(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addModelToView(model);
		model.addAttribute("reviews", userService.getReviewsByUserId(userAuthenticationDetails.getId()));
		return "myinfo-reviews";
	}
	@RequestMapping("/questions")
	public String showMyInfoQuestions(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addModelToView(model);
		model.addAttribute("questions", userService.getQuestionsByUserId(userAuthenticationDetails.getId()));
		return "myinfo-questions";
	}
	@RequestMapping("/answers")
	public String showMyInfoAnswers(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addModelToView(model);
		model.addAttribute("answers", userService.getAnswersByUserId(userAuthenticationDetails.getId()));
		return "myinfo-answers";
	}
	@RequestMapping("/evaluations")
	public String showMyInfoEvals(Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addModelToView(model);
		model.addAttribute("evaluations", userService.getEvaluationsByUserId(userAuthenticationDetails.getId()));
		return "myinfo-evaluations";
	}
}
