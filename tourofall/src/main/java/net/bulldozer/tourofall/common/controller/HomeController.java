package net.bulldozer.tourofall.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.destination.service.BestDestinationService;

@Controller
public class HomeController {
	@Autowired
	private BestDestinationService bestDestinationService;  
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("bestDestinationRederingModels", bestDestinationService.getBestDestinationRederingModels());
		return "home";
	}
}
