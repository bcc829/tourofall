package net.bulldozer.tourofall.answer.service;

import net.bulldozer.tourofall.answer.dto.AnswerRegistrationForm;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.user.dto.User;

public interface AnswerService {
	public void registerNewAnswer(AnswerRegistrationForm registrationAnswerForm,Question question, User user);
}
