package net.bulldozer.tourofall.question.controller;

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

import net.bulldozer.tourofall.answer.dto.AnswerRegistrationForm;
import net.bulldozer.tourofall.answer.service.AnswerService;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRegistrationForm;
import net.bulldozer.tourofall.question.service.QuestionService;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.service.UserService;

@Controller
@RequestMapping("/qna")
public class QnAController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnswerService answerService;
	
	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
	public String showQuestionPage(@PathVariable int questionId, Model model) {
		if(!model.containsAttribute("answerRegistrationForm")){
			model.addAttribute("answerRegistrationForm", new AnswerRegistrationForm());	
		}
		if(!model.containsAttribute("redirect")){
			questionService.incrementVisitor(questionId);
		}
		model.addAttribute("questionRenderingModel", questionService.getQuestionRenderingModelById(questionId));
		model.addAttribute("answerRenderingModels", questionService.getAnswerRenderingModelsByQuestionId(questionId));
		return "question";
	}

	@RequestMapping(value = "/question/write/{itemTypeId}/{itemId}", method = RequestMethod.GET)
	public String showQuestionForm(@PathVariable int itemId, @PathVariable int itemTypeId, Model model) {
		QuestionRegistrationForm questionRegistrationForm = new QuestionRegistrationForm();
		questionRegistrationForm.setItemId(itemId);

		if (!model.containsAttribute("questionRegistrationForm"))
			model.addAttribute("questionRegistrationForm", questionRegistrationForm);

		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",userAuthenticationDetails.getUsername());

		return "question_write";
	}

	@RequestMapping(value = "/question/write", method = RequestMethod.POST)
	public String processRegisterQuestion(@Valid QuestionRegistrationForm questionRegistrationForm, BindingResult result, RedirectAttributes model) {
		System.out.println("Entered");
		if(result.hasErrors()){
			model.addFlashAttribute("questionRegistrationForm", questionRegistrationForm);
			model.addFlashAttribute("org.springframework.validation.BindingResult.questionRegistrationForm", result);
			return "redirect:/qna/question/write/"+questionRegistrationForm.getItemId();
		}
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(userAuthenticationDetails.getId());
		questionService.registerNewQuestion(questionRegistrationForm, user);
		return "redirect:/dest/info/basic/" + questionRegistrationForm.getItemId();
	}
	
	@RequestMapping(value = "/answer/write/{questionId}", method = RequestMethod.POST)
	public String processRegisterAnswer(@Valid AnswerRegistrationForm answerRegistrationForm, BindingResult result, RedirectAttributes model, @PathVariable int questionId) {
		if(result.hasErrors()){
			model.addFlashAttribute("answerRegistrationForm", answerRegistrationForm);
			model.addFlashAttribute("org.springframework.validation.BindingResult.answerRegistrationForm", result);
			return "redirect:/qna/question/" + questionId;
		}
		Question question = questionService.getQuestionById(questionId);
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUserId(userAuthenticationDetails.getId());
		answerService.registerNewAnswer(answerRegistrationForm,question,user);
		model.addFlashAttribute("redirect", true);
		
		return "redirect:/qna/question/" + questionId;
	}
}
