package net.bulldozer.tourofall.destination.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.destination.dto.BestDestination;
import net.bulldozer.tourofall.destination.dto.BestDestinationRederingModel;
import net.bulldozer.tourofall.destination.repository.BestDestinationRepository;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;

@Service
public class BestDestinationRepositoryService implements BestDestinationService{
	@Autowired
	private BestDestinationRepository bestDestinationRepository;
	
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	
	@Transactional(readOnly=true)
	@Override
	public List<BestDestinationRederingModel> getBestDestinationRederingModels() {
		List<BestDestinationRederingModel> bestDestinationRederingModels = new ArrayList<BestDestinationRederingModel>();
		
		
		
		
		
		List<BestDestination> bestDestinations = bestDestinationRepository.findAll();
		Iterator<BestDestination> bestDestinationIter = bestDestinations.iterator();
		
		while(bestDestinationIter.hasNext()){
			BestDestination bestDestination = bestDestinationIter.next();
			
			double meanScore = 0;
			List<Evaluation> evaluations = evaluationRepository.findByItemId(bestDestination.getItemId());
			Iterator<Evaluation> evaluationIter = evaluations.iterator();
			
			while(evaluationIter.hasNext()){
				Evaluation evaluation =  evaluationIter.next();
				meanScore += evaluation.getScore();
			}
			
			meanScore = meanScore / evaluations.size();
			
			
			BestDestinationRederingModel bestDestinationRederingModel = BestDestinationRederingModel.getBuilder()
																	.title(bestDestination.getTitle())
																	.address(bestDestination.getAddress())
																	.imageUrl(bestDestination.getImageUrl())
																	.meanScore(meanScore)
																	.destinationType(bestDestination.getDestinationType())
																	.build();
			
			bestDestinationRederingModels.add(bestDestinationRederingModel);
		}
		
		return bestDestinationRederingModels;
	}
	
}
