package net.bulldozer.tourofall.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.model.DateList;
import net.bulldozer.tourofall.model.User;
import net.bulldozer.tourofall.model.UserRole;
import net.bulldozer.tourofall.service.UserService;

@Controller
@RequestMapping("/join")
public class JoinController {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String showJoinPage(Model model) {
		if(!model.containsAttribute("checkResult")){
			model.addAttribute("checkResult", -3);
		}
		model.addAttribute("user", new User());
		model.addAttribute("years", DateList.getYearList());
		model.addAttribute("months", DateList.getMonthList());
		model.addAttribute("dates", DateList.getDateList());
		return "join";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processJoin(@Valid User user, BindingResult result, Model model) throws Exception {
		int checkResult =  user.getChecked();
		System.out.println(checkResult);
		if (result.hasErrors() || checkResult <= 0) {
			if(checkResult == -3){
				checkResult = -2;
			}
			model.addAttribute("checkResult", checkResult);
			model.addAttribute("years", DateList.getYearList());
			model.addAttribute("months", DateList.getMonthList());
			model.addAttribute("dates", DateList.getDateList());
			return "join";
		}

		UserRole role = new UserRole("ROLE_USER");
		user.setRole(role);
		role.setUser(user);
		System.out
				.println("user insert in db > birth:" + user.getYear() + "/" + user.getMonth() + "/" + user.getDate());
		user.setBirth();

		userService.addUser(user);
		return "redirect:/";
	}

	@RequestMapping(value = "/duplicate", method = RequestMethod.GET)
	public String processJoin(@RequestParam(value = "username") String username, RedirectAttributes model) {
		int result;
		System.out.println(username);
		if (username.equals("")) {
			result = -1;
		} else {
			if (userService.checkDuplicate(username) == 1) {
				result = 0;
			} else {
				result = 1;
				model.addFlashAttribute("username", username);
			}
		}
		model.addFlashAttribute("checkResult", result);
		return "redirect:/join";
	}
}
