package net.bulldozer.tourofall.evaluation.service;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.user.dto.User;

public interface EvaluationService {
	public void registerNewEvaluation(EvaluationRegistration destinationEval, User user);
	public Evaluation findByUserIdAndItemId(long id,int itemId);
}
