package net.bulldozer.tourofall.evaluation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistrationsForm;
import net.bulldozer.tourofall.evaluation.service.EvaluationService;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.service.UserService;

@Controller
@RequestMapping("/eval")
public class EvaluationController {
private static final String[] tmpList={"127749","127866","1968560","126612","1704536","126302","264590","126509","126508"};
	
	@Autowired
	private EvaluationService evaluationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TourApiService tourApiService;
	
	@RequestMapping(value="/evalmore", method=RequestMethod.GET)
	public String showEvaluationRegistrationsFormPage(@RequestParam(value="fin", required=false) String fin,Model model) throws Exception{
		List<EvaluationRegistration> eList = new ArrayList<EvaluationRegistration>();
		for(int i =0; i < tmpList.length; i++){
			eList.add(tourApiService.getEvaluationRegistrationsInfo(tmpList[i]));
		}
		if(fin != null){
			fin = "평가등록이 완료되었습니다.";
		}
		model.addAttribute("fin", fin);
		model.addAttribute("evaluationRegistrationsForm", new EvaluationRegistrationsForm(eList));
		return "evalmore";
	}
	@RequestMapping(value="/evalmore",method=RequestMethod.POST)
	public String processEvaluationRegistrations(EvaluationRegistrationsForm evaluationRegistrationsForm){
		System.out.println(evaluationRegistrationsForm);
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		User user = userService.getUserByUserId(userAuthenticationDetails.getId());
		
		
		List<EvaluationRegistration> dList = evaluationRegistrationsForm.getEvaluationRegistrations();
		for(EvaluationRegistration evaluationRegistration : dList){
			if(evaluationRegistration.getScore() != 0){
				System.out.println(evaluationRegistration + ": saved in persistence");
				evaluationService.registerNewEvaluation(evaluationRegistration, user);
			}
		}
		return "redirect:/eval/evalmore?fin";
	}
}
