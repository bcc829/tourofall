package net.bulldozer.tourofall.question.service;

import java.util.List;

import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRegistrationForm;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModel;
import net.bulldozer.tourofall.user.dto.User;

public interface QuestionService {
	public List<QuestionRenderingModel> getQuestionRenderingModelsByItemId(int itemId) ;
	public List<AnswerRenderingModel> getAnswerRenderingModelsByQuestionId(long questionId);
	public QuestionRenderingModel getQuestionRenderingModelById(long questionId);
	public void registerNewQuestion(QuestionRegistrationForm registrationQuestionForm, User user);
	public void incrementVisitor(long questionId);
	
	public Question getQuestionById(long questionId);
}
