package net.bulldozer.tourofall.question.service;

import java.util.List;

import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRegistrationForm;
import net.bulldozer.tourofall.user.dto.User;

public interface QuestionService {
	public List<Question> getQuestionsByItemId(int itemId);
	public Question getQuestionById(long questionId);
	public void registerNewQuestion(QuestionRegistrationForm registrationQuestionForm, User user);
	public void incrementVisitor(long questionId);
}
