package net.bulldozer.tourofall.qna.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.qna.dto.RegistrationAnswerForm;
import net.bulldozer.tourofall.qna.dto.RegistrationQuestionForm;
import net.bulldozer.tourofall.qna.model.Question;
import net.bulldozer.tourofall.qna.service.AnswerService;
import net.bulldozer.tourofall.qna.service.QuestionService;
import net.bulldozer.tourofall.security.dto.AuthenticationUserDetails;
import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.user.service.UserService;

@Controller
@RequestMapping("/qna")
public class QnAController {
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
	public String showQuestionPage(@PathVariable int questionId, Model model) {
		if(!model.containsAttribute("registrationAnswerForm")){
			model.addAttribute("registrationAnswerForm", new RegistrationAnswerForm());	
		}
		if(!model.containsAttribute("redirect")){
			questionService.incrementVisitor(questionId);
		}
		model.addAttribute("question", questionService.getQuestionById(questionId));
		return "question";
	}

	@RequestMapping(value = "/question/write/{itemTypeId}/{itemId}", method = RequestMethod.GET)
	public String showQuestionForm(@PathVariable int itemId, @PathVariable int itemTypeId, Model model) {
		RegistrationQuestionForm registrationQuestionForm = new RegistrationQuestionForm();
		registrationQuestionForm.setItemId(itemId);
		registrationQuestionForm.setItemTypeId(itemTypeId);

		if (!model.containsAttribute("registrationQuestionForm"))
			model.addAttribute("registrationQuestionForm", registrationQuestionForm);

		if (!model.containsAttribute("result")) {
			System.out.println("Binding result not rendered");
		}
		AuthenticationUserDetails authenticationUserDetails = (AuthenticationUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",authenticationUserDetails.getUsername());

		return "question_write";
	}

	@RequestMapping(value = "/question/write", method = RequestMethod.POST)
	public String processRegisterQuestion(@Valid RegistrationQuestionForm registrationQuestionForm, BindingResult result, RedirectAttributes model) {
		if(result.hasErrors()){
			model.addFlashAttribute("registrationQuestionForm", registrationQuestionForm);
			model.addFlashAttribute("org.springframework.validation.BindingResult.registrationQuestionForm", result);
			return "redirect:/qna/question/write/" + registrationQuestionForm.getItemTypeId() +"/"+registrationQuestionForm.getItemId();
		}
		AuthenticationUserDetails authenticationUserDetails = (AuthenticationUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(authenticationUserDetails.getId());
		questionService.registerNewQuestion(registrationQuestionForm, user);
		return "redirect:/dest/info/basic/" + registrationQuestionForm.getItemTypeId() + "/" + registrationQuestionForm.getItemId();
	}

	@RequestMapping(value = "/answer/write/{questionId}", method = RequestMethod.POST)
	public String processRegisterAnswer(@Valid RegistrationAnswerForm registrationAnswerForm, BindingResult result, RedirectAttributes model, @PathVariable int questionId) {
		if(result.hasErrors()){
			model.addFlashAttribute("registrationAnswerForm", registrationAnswerForm);
			model.addFlashAttribute("org.springframework.validation.BindingResult.registrationAnswerForm", result);
			return "redirect:/qna/question/" + questionId;
		}
		Question question = questionService.getQuestionById(questionId);
		AuthenticationUserDetails authenticationUserDetails = (AuthenticationUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(authenticationUserDetails.getId());
		answerService.registerNewAnswer(registrationAnswerForm,question,user);
		model.addFlashAttribute("redirect", true);
		
		return "redirect:/qna/question/" + questionId;
	}
}
