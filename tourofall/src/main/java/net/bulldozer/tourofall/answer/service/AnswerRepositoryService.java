package net.bulldozer.tourofall.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.answer.dto.Answer;
import net.bulldozer.tourofall.answer.dto.AnswerRegistrationForm;
import net.bulldozer.tourofall.answer.repository.AnswerRepository;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.user.dto.User;

@Service
public class AnswerRepositoryService implements AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	
	@Transactional
	@Override
	public void registerNewAnswer(AnswerRegistrationForm registrationAnswerForm, Question question, User user) {
		Answer answer = Answer.getBuilder()
				.content(registrationAnswerForm.getContent())
				.build();
		
		user.addAnswer(answer);
		question.addAnswer(answer);
		answerRepository.save(answer);
	}
}
