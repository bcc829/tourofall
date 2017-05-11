package net.bulldozer.tourofall.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.user.dto.RegistrationUserForm;
import net.bulldozer.tourofall.user.service.UserService;
import net.bulldozer.tourofall.user.util.DateList;

@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String showJoinPage(Model model) {
		model.addAttribute("registrationUserForm", new RegistrationUserForm());
		model.addAttribute("years", DateList.getYearList());
		model.addAttribute("months", DateList.getMonthList());
		model.addAttribute("dates", DateList.getDateList());
		return "signup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processJoin(@Valid RegistrationUserForm registrationUserForm, BindingResult result, Model model) throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("years", DateList.getYearList());
			model.addAttribute("months", DateList.getMonthList());
			model.addAttribute("dates", DateList.getDateList());
			return "signup";
		}
		return "redirect:/";
	}
}
