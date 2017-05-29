package net.bulldozer.tourofall.question.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.answer.dto.AnswerRegistrationForm;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.answer.service.AnswerService;
import net.bulldozer.tourofall.common.dto.Response;
import net.bulldozer.tourofall.question.dto.QnARenderingModelsSet;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRegistrationForm;
import net.bulldozer.tourofall.question.service.QuestionService;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
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
	public @ResponseBody ResponseEntity<?> showQnAPage(@PathVariable long questionId){
		QnARenderingModelsSet qnARenderingModelsSet = questionService.getQnARenderingModelsSet(questionId);
		if(qnARenderingModelsSet == null){
			System.out.println("qnARenderingModelsSet is null");
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		System.out.println("qnARenderingModelsSet is full");
		return new ResponseEntity<QnARenderingModelsSet>(qnARenderingModelsSet,HttpStatus.OK);
	}
	
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
	public @ResponseBody ResponseEntity<?> processRegisterQuestion(@RequestBody @Valid QuestionRegistrationForm questionRegistrationForm, BindingResult result, RedirectAttributes model) {
		System.out.println("Entered");
		if(result.hasErrors()){
			List<ObjectError> errors =  result.getAllErrors();
			return new ResponseEntity<List<ObjectError>>(errors,HttpStatus.CONFLICT);
		}
		
		Question question = questionService.registerNewQuestion(questionRegistrationForm);
		if(question == null){
			return new ResponseEntity<Response>(new Response(false,"Question","등록을 실패하였습니다."),HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(new Response(true,"Question","등록을 성공하였습니다."),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/answer/write", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> processRegisterAnswer(@RequestBody @Valid AnswerRegistrationForm answerRegistrationForm, BindingResult result) {
		if(result.hasErrors()){
			List<ObjectError> errors =  result.getAllErrors();
			return new ResponseEntity<List<ObjectError>>(errors,HttpStatus.CONFLICT);
		}
		AnswerRenderingModel answerRenderingModel = answerService.registerNewAnswer(answerRegistrationForm);
		if(answerRenderingModel == null){
			return new ResponseEntity<Response>(new Response(false,"Answer","등록을 실패하였습니다."),HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<AnswerRenderingModel>(answerRenderingModel,HttpStatus.OK);
		
	}
	
}
