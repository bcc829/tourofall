package net.bulldozer.tourofall.evaluation.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.repository.UserRepository;

@Service
public class EvaluationRepositoryService implements EvaluationService{
	@Autowired
	private EvaluationRepository evaluationRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional
	@Override
	public Evaluation registerNewEvaluation(EvaluationRegistration evaluationRegistration) {
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findOne(userAuthenticationDetails.getId());
		
		Evaluation getEvaluation = evaluationRepository.findByUserIdAndItemId(user.getId(), evaluationRegistration.getItemId());
		if(getEvaluation == null){
			getEvaluation = Evaluation.getBuilder()
										.itemId(evaluationRegistration.getItemId())
										.build();
		}
		getEvaluation.setScore(evaluationRegistration.getScore());
		
		user.addEvaluation(getEvaluation);
		return evaluationRepository.save(getEvaluation);
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
	
	@Transactional(readOnly=true)
	@Override
	public int getEvaluationCountByItemId(int itemId){
		List<Evaluation> evaluations = evaluationRepository.findByItemId(itemId);
		return evaluations.size();
	}
	
	@Transactional(readOnly=true)
	@Override
	public double getEvaluationMeanByItemId(int itemId){
		double mean = 0;
		List<Evaluation> evaluations = evaluationRepository.findByItemId(itemId);
		Iterator<Evaluation> evaluationIter = evaluations.iterator();
		while(evaluationIter.hasNext()){
			Evaluation evaluation = evaluationIter.next();
			mean += evaluation.getScore();
		}
		mean = mean / evaluations.size();
		return mean;
	}
}
