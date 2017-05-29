package net.bulldozer.tourofall.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.answer.dto.Answer;
import net.bulldozer.tourofall.answer.dto.AnswerRegistrationForm;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.answer.repository.AnswerRepository;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.repository.QuestionRepository;
import net.bulldozer.tourofall.user.dto.User;

@Service
public class AnswerRepositoryService implements AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	@Transactional
	@Override
	public AnswerRenderingModel registerNewAnswer(AnswerRegistrationForm answerRegistrationForm) {
		Question question = questionRepository.findOne(answerRegistrationForm.getQuestionId());
		User user = question.getUser();
		
		Answer answer = Answer.getBuilder()
				.content(answerRegistrationForm.getContent())
				.build();
		
		
		user.addAnswer(answer);
		question.addAnswer(answer);
		
		if(answerRepository.save(answer) == null){
			return null;
		}
		AnswerRenderingModel answerRenderingModel = AnswerRenderingModel.getBuilder()
				.userId(user.getId())
				.lastName(answer.getUser().getLastName())
				.firstName(answer.getUser().getFirstName())
				.createdDate(answer.getCreatedDate())
				.questionTitle(answer.getQuestion().getTitle())
				.content(answer.getContent())
				.build();
		
		return answerRenderingModel;
	}
}
