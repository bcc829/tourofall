package net.bulldozer.tourofall.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogController {
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginForm(@RequestParam(value="error", required=false) String error,@RequestParam(value="logout", required=false) String logout,Model model){
		if(error != null){
			System.out.println("error filled");
			error ="���̵� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
		}
		if(logout != null){
			System.out.println("logout filled");
			error ="�α׾ƿ��� �Ϸ�Ǿ����ϴ�.";
		}
		model.addAttribute("errorMsg", error);
		model.addAttribute("logoutMsg", logout);
		return "login";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(
				AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		cookieClearingLogoutHandler.logout(request, response, null);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(request, response, null);
		return "redirect:/login?logout";
	}

}
