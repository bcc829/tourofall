package net.bulldozer.tourofall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.model.FakeUser;
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
		if(!model.containsAttribute("review"))
			model.addAttribute("review", review);
		
		if(!model.containsAttribute("result")){
			System.out.println("Binding result not rendered");
		}
		model.addAttribute("username", service.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName())).getUsername());
		return "review";
	}
	@RequestMapping(value="/info/review",method=RequestMethod.POST)
	public String processReview(@Valid Review review , BindingResult result, RedirectAttributes model, HttpServletRequest request){
		if(result.hasErrors()){
			model.addFlashAttribute("review",review);
			model.addFlashAttribute("org.springframework.validation.BindingResult.review",result);
			return "redirect:/dest/info/review/"+review.getItemId();
		}
		FakeUser user = service.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName()));
		review.setUser(user);
		service.addReview(review);

		return "redirect:/dest/info/"+review.getItemId();
	}
}
