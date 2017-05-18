package net.bulldozer.tourofall.recommendation.controller;

import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistrationsForm;
import net.bulldozer.tourofall.recommendation.dto.RecommendationRenderingModel;
import net.bulldozer.tourofall.recommendation.dto.RecommendationRenderingModelForm;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;

@Controller
@RequestMapping("/recommend")
public class RecommendationController {
	private static final String[] itemList32={"1364932","1364975","635460","1976320","131794","126535","131106","126508","131109"};
	private static final String[] itemList140={"131227","127220","1976320","1608611","821412","131716","809596","126481","945078"};
	private static final double[] scoreList32={4.5, 4.5, 4.5, 4.5, 4.5, 4.3, 4.3, 4.2,4.0};
	private static final double[] scoreList140={3.6, 3.6, 3.6, 3.4, 3.1, 3.1, 3.0, 3.0, 3.0};
	
	
	private static final String[] scores={"4.5","4.1","4.7","4.9","4.0","4.2","4.4","4.4","3.9"};
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TourApiService tourApiService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showDestinationRecommendPage(@RequestParam(value="fin", required=false) String fin,Model model) throws Exception{
//		URI uri = UriComponentsBuilder.newInstance()
//		.scheme("http")
//		.path("/recommender/140")
//		.host("113.198.84.67:8080")
//        .build().encode().toUri();
//		
//		String result = restTemplate.getForObject("http://113.198.84.67:8080/recommender/140", String.class);
//		model.addAttribute("result", result);
		List<RecommendationRenderingModel> eList = new ArrayList<RecommendationRenderingModel>();
		
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userAuthenticationDetails.getId() == 1){
			for(int i =0; i < 9; i++){
				RecommendationRenderingModel recommendationRenderingModel = tourApiService.getRecommendationRenderingModel(itemList32[i]);
				recommendationRenderingModel.setRecommendScore(scoreList32[i]);
				eList.add(recommendationRenderingModel);
			}
		}else if(userAuthenticationDetails.getId() == 2){
			for(int i =0; i < 9; i++){
				RecommendationRenderingModel recommendationRenderingModel = tourApiService.getRecommendationRenderingModel(itemList140[i]);
				recommendationRenderingModel.setRecommendScore(scoreList140[i]);
				eList.add(recommendationRenderingModel);
			}
		}else{
			for(int i =0; i < 9; i++){
				RecommendationRenderingModel recommendationRenderingModel = tourApiService.getRecommendationRenderingModel(itemList32[i]);
				recommendationRenderingModel.setRecommendScore(scoreList32[i]);
				eList.add(recommendationRenderingModel);
			}
		}
		if(fin != null){
			fin = "평가등록이 완료되었습니다.";
		}
		model.addAttribute("fin", fin);
		model.addAttribute("score", scores);
		model.addAttribute("recommendationRenderingModelForm", new RecommendationRenderingModelForm(eList));
//		
		return "recommend";
	}
}