package net.bulldozer.tourofall.evaluation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.user.dto.User;

@Service
public class EvaluationRepositoryService implements EvaluationService{
	@Autowired
	private EvaluationRepository evaluationRepository;

	@Transactional
	@Override
	public void registerNewEvaluation(EvaluationRegistration evaluationRegistration, User user) {
		Evaluation evaluation = Evaluation.getBuilder()
										.itemId(evaluationRegistration.getItemId())
										.score(evaluationRegistration.getScore())
										.build();
		user.addEvaluation(evaluation);
		evaluationRepository.save(evaluation);
	}

	@Transactional(readOnly=true)
	@Override
	public Evaluation findByUserIdAndItemId(long id,int itemId) {
		return evaluationRepository.findByUserIdAndItemId(id,itemId);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Evaluation> findByUserId(long userId){
		return evaluationRepository.findByUserId(userId);
	}
	
	
}
