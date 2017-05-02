package net.bulldozer.tourofall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.bulldozer.tourofall.dao.QnADao;
import net.bulldozer.tourofall.model.Answer;
import net.bulldozer.tourofall.model.Question;

@Controller
public class QnAService {
	@Autowired
	private QnADao dao;
	
	public List<Question> getQuestionsByItemId(int itemId) {
		return dao.getQuestionsByItemId(itemId);
	}
	public Question getQuestionById(int questionId){
		return dao.getQuestionById(questionId);
	}
	public void addQuestion(Question question) {
		dao.addQuestion(question);
	}
	public void addAnswer(Answer answer) {
		dao.addAnswer(answer);
	}
}
