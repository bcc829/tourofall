package net.bulldozer.tourofall.evaluation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.model.Evaluation;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.user.model.User;

@Service
public class EvaluationRepositoryService implements EvaluationService{
	@Autowired
	private EvaluationRepository evaluationRepository;

	@Transactional
	@Override
	public void registerNewEvaluation(EvaluationRegistration evaluationRegistration, User user) {
		Evaluation evaluation = Evaluation.getBuilder()
										.user(user)
										.itemId(evaluationRegistration.getItemId())
										.score(evaluationRegistration.getScore())
										.build();
		user.addEvaluation(evaluation);
		evaluationRepository.save(evaluation);
	}

	
	
	
}