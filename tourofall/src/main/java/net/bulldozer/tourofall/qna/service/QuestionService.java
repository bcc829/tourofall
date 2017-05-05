package net.bulldozer.tourofall.qna.service;

import java.util.List;

import net.bulldozer.tourofall.qna.model.Question;

public interface QuestionService {
	public List<Question> getQuestionsByItemId(int itemId);
	public Question getQuestionById(long questionId);
	public void registerNewQuestion(Question question);
	public void incrementVisitor(long questionId);
}
