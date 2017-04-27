package net.bulldozer.tourofall.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.model.Review;
import net.bulldozer.tourofall.service.DestinationService;

@Controller
@RequestMapping("/dest")
public class DestinationController {
	@Autowired
	private DestinationService service;
	
	@RequestMapping("/info/{itemId}")
	public String showDestInfo(@PathVariable int itemId,Model model) throws Exception{
		JSONObject body = service.getDestInfo(itemId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("destInfo", item);
			model.addAttribute("reviews", service.getReviewsByItemId(itemId));
		}
		
		return "destinfo";
	}
	@RequestMapping(value="/info/review/{itemId}",method=RequestMethod.GET)
	public String showReviewForm(@PathVariable int itemId,Model model,HttpServletRequest request){
		Review review = new Review();
		review.setItemId(itemId);
		model.addAttribute("review", review);
		return "review";
	}
	@RequestMapping(value="/info/review",method=RequestMethod.POST)
	public String processReview(Review review){
		if(!service.addReview(review)){
			System.out.println("review post error");
			return "redirect:/dest/info/"+review.getItemId() + "/review";
		}
		return "redirect:/dest/info/"+review.getItemId();
	}
}
