package net.bulldozer.tourofall.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.service.EvaluationService;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.service.UserService;
import net.bulldozer.tourofall.user.util.DateList;

@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UserService userService;

	@Autowired
	private EvaluationService evaluationService;
	
	@Autowired
	private TourApiService tourApiService;
	
	
	private void addModelToView(long userId, Model model){

		model.addAttribute("userId", userId);
		
		
		model.addAttribute("questionCount", userService.getQuestionsSizeByUserId(userId));
		model.addAttribute("answerCount", userService.getAnswersSizeByUserId(userId));
		model.addAttribute("reviewCount", userService.getReviewsSizeByUserId(userId));
		model.addAttribute("evaluationCount", userService.getEvaluationsSizeByUserId(userId));
		
		
		List<Evaluation> evaluations = evaluationService.findByUserId(userId);
		int selected = (int)(Math.random()*evaluations.size());
		Evaluation evaluation = evaluations.get(selected);
		String imageUrl = null;
		try {
			imageUrl = tourApiService.getItemImage(evaluation.getItemId());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		model.addAttribute("imageUrl", imageUrl);
	}
	private void addDateList(Model model){
		model.addAttribute("years", DateList.getYearList());
		model.addAttribute("months", DateList.getMonthList());
		model.addAttribute("dates", DateList.getDateList());
	}
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public String showMyInfoHome(@PathVariable long userId, Model model){
		addModelToView(userId,model);
		return "users-home";
	}
	@RequestMapping(value="/{userId}/setting", method=RequestMethod.GET)
	public String showMyInfoDetail(@PathVariable long userId, Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addModelToView(userId,model);
		addDateList(model);
		model.addAttribute("userAuthenticationDetails", userAuthenticationDetails);
		model.addAttribute("userModificationForm", userService.getUserModificationFormByUserId(userAuthenticationDetails.getId()));
		
		return "users-detail";
	}
	@RequestMapping(value="/{userId}/reviews", method=RequestMethod.GET)
	public String showMyInfoReviews(@PathVariable long userId,Model model){
		addModelToView(userId,model);
		model.addAttribute("reviews", userService.getReviewsByUserId(userId));
		return "users-reviews";
	}
	@RequestMapping(value="/{userId}/questions", method=RequestMethod.GET)
	public String showMyInfoQuestions(@PathVariable long userId,Model model){
		addModelToView(userId,model);
		model.addAttribute("questions", userService.getQuestionsByUserId(userId));
		return "users-questions";
	}
	@RequestMapping(value="/{userId}/answers", method=RequestMethod.GET)
	public String showMyInfoAnswers(@PathVariable long userId,Model model){
		addModelToView(userId,model);
		model.addAttribute("answers", userService.getAnswersByUserId(userId));
		return "users-answers";
	}
	@RequestMapping(value="/{userId}/evaluations", method=RequestMethod.GET)
	public String showMyInfoEvals(@PathVariable long userId,Model model){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addModelToView(userId,model);
		model.addAttribute("evaluations", userService.getEvaluationsByUserId(userAuthenticationDetails.getId()));
		return "users-evaluations";
	}
}
