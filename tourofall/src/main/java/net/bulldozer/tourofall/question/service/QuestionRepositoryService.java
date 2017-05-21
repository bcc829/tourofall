package net.bulldozer.tourofall.question.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.answer.dto.Answer;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRegistrationForm;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModel;
import net.bulldozer.tourofall.question.repository.QuestionRepository;
import net.bulldozer.tourofall.user.dto.User;

@Service
public class QuestionRepositoryService implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question getQuestionById(long questionId){
		return questionRepository.findOne(questionId);
	}
	@Transactional(readOnly=true)
	@Override
	public List<AnswerRenderingModel> getAnswerRenderingModelsByQuestionId(long questionId){
		List<AnswerRenderingModel> answerRenderingModels = new ArrayList<AnswerRenderingModel>();
		Question question = questionRepository.findOne(questionId);
		Collection<Answer> answers = question.getAnswers();
		
		Iterator<Answer> answerIter = answers.iterator();
		while(answerIter.hasNext()){
			Answer answer = answerIter.next();
			AnswerRenderingModel answerRenderingModel = AnswerRenderingModel.getBuilder()
					.lastName(answer.getUser().getLastName())
					.firstName(answer.getUser().getFirstName())
					.createdDate(answer.getCreatedDate())
					.questionTitle(answer.getQuestion().getTitle())
					.content(answer.getContent())
					.build();
			
			answerRenderingModels.add(answerRenderingModel);
		}
		return answerRenderingModels;
	}
	@Transactional(readOnly=true)
	@Override
	public List<QuestionRenderingModel> getQuestionRenderingModelsByItemId(int itemId) {
		List<QuestionRenderingModel> questionRenderingModels = new ArrayList<QuestionRenderingModel>();
		
		List<Question> questions = questionRepository.findByItemId(itemId);
		
		Iterator<Question> questionIter = questions.iterator();
		while(questionIter.hasNext()){
			Question question = questionIter.next();
			
			QuestionRenderingModel questionRenderingModel = QuestionRenderingModel.getBuilder()
					.id(question.getId())
					.title(question.getTitle())
					.content(question.getContent())
					.createdDate(question.getCreatedDate())
					.lastName(question.getUser().getLastName())
					.firstName(question.getUser().getFirstName())
					.visitor(question.getVisitor())
					.build();
			questionRenderingModels.add(questionRenderingModel);
		}
		return questionRenderingModels;
	}
	@Transactional(readOnly=true)
	@Override
	public QuestionRenderingModel getQuestionRenderingModelById(long questionId) {
		Question question = questionRepository.findOne(questionId);
		QuestionRenderingModel questionRenderingModel = QuestionRenderingModel.getBuilder()
				.id(question.getId())
				.title(question.getTitle())
				.content(question.getContent())
				.createdDate(question.getCreatedDate())
				.lastName(question.getUser().getLastName())
				.firstName(question.getUser().getFirstName())
				.visitor(question.getVisitor())
				.build();
		
		return questionRenderingModel;
	}
	@Transactional
	@Override
	public void registerNewQuestion(QuestionRegistrationForm registrationQuestionForm, User user) {
		Question question = Question.getBuilder()
				.title(registrationQuestionForm.getTitle())
				.content(registrationQuestionForm.getContent())
				.itemId(registrationQuestionForm.getItemId())
				.build();
		user.addQuestion(question);
		questionRepository.save(question);
	}
	
	@Transactional
	@Override
	public void incrementVisitor(long questionId) {
		Question question = questionRepository.findOne(questionId);
		question.incrementVisitor();
	}

}
