package net.bulldozer.tourofall.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.destination.dto.BestDestinationRenderingModel;
import net.bulldozer.tourofall.destination.service.TodayDestinationService;

@Controller
public class HomeController {
	@Autowired
	private TodayDestinationService todayDestinationService;  
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("todayDestinationRenderingModels", todayDestinationService.getTodayDestinationRenderingModels());
		List<BestDestinationRenderingModel> bestDestinationRenderingModels = new ArrayList<BestDestinationRenderingModel>();
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("경주 동궁과 월지",128526,"http://tong.visitkorea.or.kr/cms/resource/44/2367744_image2_1.jpg","신라 왕궁의 별궁터"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("하늘물빛정원",2022638,"http://tong.visitkorea.or.kr/cms/resource/25/2022625_image2_1.jpg","머들령 계곡을 흐르는 장산호수"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("섬진강기차마을",128578,"http://tong.visitkorea.or.kr/cms/resource/89/2032689_image2_1.jpg","고달면 가정리가 곡성군 관광명소"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("프로방스 마을",129196,"http://tong.visitkorea.or.kr/cms/resource/01/1998801_image2_1.jpg","프로방스 리빙관, 허브관, 패션관, 카페로 구성되어진 테마형 마을"));
		model.addAttribute("bestDestinationRenderingModels", bestDestinationRenderingModels);
		return "home";
	}
}
