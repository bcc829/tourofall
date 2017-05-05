package net.bulldozer.tourofall.qna.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.qna.model.Question;
import net.bulldozer.tourofall.qna.repository.QuestionRepository;

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
	public void registerNewQuestion(Question question) {
		questionRepository.save(question);
	}
	
	@Transactional
	@Override
	public void incrementVisitor(long questionId) {
		Question question = questionRepository.findOne(questionId);
		question.incrementVisitor();
	}

}
