package net.bulldozer.tourofall.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.qna.dto.RegistrationAnswerForm;
import net.bulldozer.tourofall.qna.model.Answer;
import net.bulldozer.tourofall.qna.model.Question;
import net.bulldozer.tourofall.qna.repository.AnswerRepository;
import net.bulldozer.tourofall.user.model.User;

@Service
public class RepositoryAnswerService implements AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	
	@Transactional
	@Override
	public void registerNewAnswer(RegistrationAnswerForm registrationAnswerForm, Question question, User user) {
		Answer answer = Answer.getBuilder()
				.content(registrationAnswerForm.getContent())
				.user(user)
				.question(question)
				.build();
		user.addAnswer(answer);
		question.addAnswer(answer);
		answerRepository.save(answer);
	}

}
