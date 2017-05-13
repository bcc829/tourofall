package net.bulldozer.tourofall.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.recommend.dto.DestinationEval;
import net.bulldozer.tourofall.recommend.model.Evaluation;
import net.bulldozer.tourofall.recommend.repository.EvaluationRepository;
import net.bulldozer.tourofall.user.model.User;

@Service
public class RepositoryEvaluationService implements EvaluationService{
	@Autowired
	private EvaluationRepository evaluationRepository;

	@Transactional
	@Override
	public void registerNewEvaluation(DestinationEval destinationEval, User user) {
		Evaluation evaluation = Evaluation.getBuilder()
										.user(user)
										.itemId(destinationEval.getItemId())
										.score(destinationEval.getScore())
										.build();
		user.addEvaluation(evaluation);
		evaluationRepository.save(evaluation);
	}

	
	
	
}
