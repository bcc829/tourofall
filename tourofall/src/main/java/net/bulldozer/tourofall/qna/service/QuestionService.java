package net.bulldozer.tourofall.qna.service;

import java.util.List;

import net.bulldozer.tourofall.qna.dto.RegistrationQuestionForm;
import net.bulldozer.tourofall.qna.model.Question;
import net.bulldozer.tourofall.user.model.User;

public interface QuestionService {
	public List<Question> getQuestionsByItemId(int itemId);
	public Question getQuestionById(long questionId);
	public void registerNewQuestion(RegistrationQuestionForm registrationQuestionForm, User user);
	public void incrementVisitor(long questionId);
}
