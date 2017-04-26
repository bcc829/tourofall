package net.bulldozer.tourofall.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.model.Comment;
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
			model.addAttribute("comments", service.getCommentsByItemId(itemId));
		}
		
		return "destinfo";
	}
	@RequestMapping(value="/info/{itemId}/review",method=RequestMethod.GET)
	public String showReviewForm(@PathVariable int itemId,Model model){
		Comment comment = new Comment();
		comment.setItemId(itemId);
		model.addAttribute("comment", comment);
		return "review";
	}
	@RequestMapping(value="/info/review",method=RequestMethod.POST)
	public String processReview(Comment comment){
		if(!service.addComment(comment)){
			System.out.println("comment post error");
			return "redirect:/dest/info/"+comment.getItemId() + "/review";
		}
		return "redirect:/dest/info/"+comment.getItemId();
	}
}
