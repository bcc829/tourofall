package net.bulldozer.tourofall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.model.DateList;
import net.bulldozer.tourofall.model.User;
import net.bulldozer.tourofall.model.UserRole;
import net.bulldozer.tourofall.service.JoinService;

@Controller
@RequestMapping("/join")
public class JoinController {
	@Autowired
	private JoinService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showJoinPage(Model model){
		model.addAttribute("newUser", new User());
		model.addAttribute("years", DateList.getYearList());
		model.addAttribute("months", DateList.getMonthList());
		model.addAttribute("dates", DateList.getDateList());
		return "join";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String processJoin(User user) throws Exception{
		UserRole role = new UserRole("ROLE_USER");
		user.setRole(role);
		role.setUser(user);
		System.out.println("user insert in db > birth:" + user.getYear() +"/"+ user.getMonth() +"/"+ user.getDate());
		user.setBirth();
		
		service.addUser(user);
		return "redirect:/";
	}
}
