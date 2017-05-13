package net.bulldozer.tourofall.recommend.service;

import net.bulldozer.tourofall.recommend.dto.DestinationEval;
import net.bulldozer.tourofall.user.model.User;

public interface EvaluationService {
	public void registerNewEvaluation(DestinationEval destinationEval, User user);
}
