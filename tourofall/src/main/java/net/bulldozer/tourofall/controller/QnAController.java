package net.bulldozer.tourofall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.model.Answer;
import net.bulldozer.tourofall.model.Question;
import net.bulldozer.tourofall.model.User;
import net.bulldozer.tourofall.service.QnAService;
import net.bulldozer.tourofall.service.UserService;

@Controller
@RequestMapping("/qna")
public class QnAController {
	@Autowired
	private QnAService qnAService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
	public String showQuestionPage(@PathVariable int questionId, Model model) {
		qnAService.incrementVisitor(questionId);
		model.addAttribute("question", qnAService.getQuestionById(questionId));
		model.addAttribute("answer", new Answer());
		return "question";
	}

	@RequestMapping(value = "/question/write/{itemTypeId}/{itemId}", method = RequestMethod.GET)
	public String showQuestionForm(@PathVariable int itemId, @PathVariable int itemTypeId, Model model,
			HttpServletRequest request) {
		Question question = new Question();
		question.setItemId(itemId);
		question.setItemTypeId(itemTypeId);

		if (!model.containsAttribute("question"))
			model.addAttribute("question", question);

		if (!model.containsAttribute("result")) {
			System.out.println("Binding result not rendered");
		}
		model.addAttribute("username",
				userService.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName())).getUsername());

		return "question_write";
	}

	@RequestMapping(value = "/question/write", method = RequestMethod.POST)
	public String processRegisterQuestion(Question question, Model model, HttpServletRequest request) {
		User user = userService.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName()));
		question.setUser(user);
		qnAService.addQuestion(question);
		return "redirect:/dest/info/basic/" + question.getItemTypeId() + "/" + question.getItemId();
	}

	@RequestMapping(value = "/answer/write/{questionId}", method = RequestMethod.POST)
	public String processRegisterAnswer(@PathVariable int questionId, Answer answer, Model model,
			HttpServletRequest request) {
		Question question = qnAService.getQuestionById(questionId);
		answer.setUser(question.getUser());
		answer.setQuestion(question);
		qnAService.addAnswer(answer);
		return "redirect:/qna/question/" + questionId;
	}
}
