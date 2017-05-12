package net.bulldozer.tourofall.recommend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.recommend.dto.DestinationEval;
import net.bulldozer.tourofall.recommend.dto.DestinationEvalForm;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
	private static final String[] tmpList={"127749","127866","1968560","126612","1704536","126302","264590","126509","126508"};
	
	@Autowired
	private TourApiService tourApiService;
	
	@RequestMapping(value="/evalmore", method=RequestMethod.GET)
	public String showDestinationEvalPage(@RequestParam(value="fin", required=false) String fin,Model model) throws Exception{
		List<DestinationEval> eList = new ArrayList<DestinationEval>();
		for(int i =0; i < tmpList.length; i++){
			eList.add(tourApiService.getDestinationEvalInfo(tmpList[i]));
		}
		if(fin != null){
			fin = "평가등록이 완료되었습니다.";
		}
		model.addAttribute("fin", fin);
		model.addAttribute("destinationEvalForm", new DestinationEvalForm(eList));
		return "evalmore";
	}
	@RequestMapping(value="/evalmore",method=RequestMethod.POST)
	public String processDestinationEval(DestinationEvalForm destinationEvalForm){
		System.out.println(destinationEvalForm);
		return "redirect:/recommend/evalmore?fin";
	}
	@RequestMapping(method=RequestMethod.GET)
	public String showDestinationRecommendPage(){
		return "";
	}
}
