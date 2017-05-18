package net.bulldozer.tourofall.recommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRenderingModel extends EvaluationRegistration{
	private double recommendScore;
	
	
	public RecommendationRenderingModel(int itemId,String imageUrl, String title, double score, double recommendScore){
		super(itemId,imageUrl,title,score);
		this.recommendScore = recommendScore;
	}
}
