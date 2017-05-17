package net.bulldozer.tourofall.recommendation.controller;

import java.net.URI;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/recommend")
public class RecommendationController {
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showDestinationRecommendPage(Model model){
		URI uri = UriComponentsBuilder.newInstance()
		.scheme("http")
		.path("/recommender/140")
		.host("223.194.157.205:8080")
        .build().encode().toUri();
		String result = restTemplate.getForObject(uri, String.class);
		model.addAttribute("result", result);
		return "recommend";
	}
}