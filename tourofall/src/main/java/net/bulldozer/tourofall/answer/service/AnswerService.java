package net.bulldozer.tourofall.answer.service;

import net.bulldozer.tourofall.answer.dto.AnswerRegistrationForm;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;

public interface AnswerService {
	public AnswerRenderingModel registerNewAnswer(AnswerRegistrationForm answerRegistrationForm);
}
