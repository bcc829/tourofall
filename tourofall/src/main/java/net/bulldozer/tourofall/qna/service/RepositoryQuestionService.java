package net.bulldozer.tourofall.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.qna.dto.RegistrationQuestionForm;
import net.bulldozer.tourofall.qna.model.Question;
import net.bulldozer.tourofall.qna.repository.QuestionRepository;
import net.bulldozer.tourofall.user.model.User;

@Service
public class RepositoryQuestionService implements QuestionService {

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
	public void registerNewQuestion(RegistrationQuestionForm registrationQuestionForm, User user) {
		Question question = Question.getBuilder()
				.title(registrationQuestionForm.getTitle())
				.content(registrationQuestionForm.getContent())
				.itemId(registrationQuestionForm.getItemId())
				.itemTypeId(registrationQuestionForm.getItemTypeId())
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
