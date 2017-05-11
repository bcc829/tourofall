package net.bulldozer.tourofall.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import net.bulldozer.tourofall.security.util.SecurityUtil;
import net.bulldozer.tourofall.user.dto.RegistrationUserForm;
import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.user.service.ConnectionManager;
import net.bulldozer.tourofall.user.service.DuplicateUsernameException;
import net.bulldozer.tourofall.user.service.UserService;
import net.bulldozer.tourofall.user.util.DateList;

@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	private UserService userService;

	@Autowired
	private ConnectionManager connectionManager;
	
	protected static final String MODEL_NAME_REGISTRATION_USER = "registrationUserForm";
	protected static final String ERROR_MESSAGE_USERNAME_EXIST = "아이디가 이미 존재합니다.";
	
	private void addDateList(Model model){
		model.addAttribute("years", DateList.getYearList());
		model.addAttribute("months", DateList.getMonthList());
		model.addAttribute("dates", DateList.getDateList());
	}
	@RequestMapping(method = RequestMethod.GET)
	public String showJoinPage(Model model, WebRequest request) {
		Connection<?> connection = connectionManager.getConnection(request);
		RegistrationUserForm dto = connectionManager.createRegistrationUserForm(connection);
		model.addAttribute(MODEL_NAME_REGISTRATION_USER, dto);
		addDateList(model);
		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processJoin(@Valid RegistrationUserForm registrationUserForm, BindingResult result, Model model, WebRequest request)
			throws Exception {
		if (result.hasErrors()) {
			addDateList(model);
			return "signup";
		}
		registrationUserForm.setBirth();
		System.out.println(registrationUserForm);
		
		User createdUser = createUser(registrationUserForm,result);
		if(createdUser == null){
			addDateList(model);
			return "signup";
		}
		
		SecurityUtil.logInUser(createdUser);
		connectionManager.signupForConnectionRepository(createdUser.getUsername(), request);
		return "redirect:/";
	}
	private User createUser(RegistrationUserForm registrationUserForm, BindingResult result){
		User createdUser = null;
		
		try{
			createdUser = userService.registerNewUser(registrationUserForm);
		}catch(DuplicateUsernameException ex){
			addFieldError(MODEL_NAME_REGISTRATION_USER, RegistrationUserForm.FILE_NAME_USERNAME, registrationUserForm.getUsername(),ERROR_MESSAGE_USERNAME_EXIST,result);
		}
		return createdUser;
	}
	
	private void addFieldError(String modelName, String fieldName, String fieldValue, String errorMsg, BindingResult result){
		FieldError error = new FieldError(modelName,fieldName,fieldValue,false, new String[]{errorMsg},new Object[]{},errorMsg);
		result.addError(error);
	}
	
}
