package net.bulldozer.tourofall.qna.service;

import net.bulldozer.tourofall.qna.dto.RegistrationAnswerForm;
import net.bulldozer.tourofall.qna.model.Question;
import net.bulldozer.tourofall.user.model.User;

public interface AnswerService {
	public void registerNewAnswer(RegistrationAnswerForm registrationAnswerForm,Question question, User user);
}
