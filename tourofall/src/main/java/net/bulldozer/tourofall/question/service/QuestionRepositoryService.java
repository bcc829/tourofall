package net.bulldozer.tourofall.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.question.dto.QuestionRegistrationForm;
import net.bulldozer.tourofall.question.model.Question;
import net.bulldozer.tourofall.question.repository.QuestionRepository;
import net.bulldozer.tourofall.user.model.User;

@Service
public class QuestionRepositoryService implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Question> getQuestionsByItemId(int itemId) {
		return questionRepository.findByItemId(itemId);
	}
	@Transactional(readOnly=true)
	@Override
	public Question getQuestionById(long questionId) {
		return questionRepository.findOne(questionId);
	}
	@Transactional
	@Override
	public void registerNewQuestion(QuestionRegistrationForm registrationQuestionForm, User user) {
		Question question = Question.getBuilder()
				.title(registrationQuestionForm.getTitle())
				.content(registrationQuestionForm.getContent())
				.itemId(registrationQuestionForm.getItemId())
				.user(user)
				.build();
		questionRepository.save(question);
	}
	
	@Transactional
	@Override
	public void incrementVisitor(long questionId) {
		Question question = questionRepository.findOne(questionId);
		question.incrementVisitor();
	}

}
