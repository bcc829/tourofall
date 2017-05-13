package net.bulldozer.tourofall.recommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/recommend")
public class RecommendationController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String showDestinationRecommendPage(){
		return "recommend";
	}
}