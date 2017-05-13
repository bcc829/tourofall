package net.bulldozer.tourofall.evaluation.service;

import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.user.model.User;

public interface EvaluationService {
	public void registerNewEvaluation(EvaluationRegistration destinationEval, User user);
}
