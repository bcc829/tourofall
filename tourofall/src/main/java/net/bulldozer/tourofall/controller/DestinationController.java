package net.bulldozer.tourofall.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bulldozer.tourofall.service.DestinationService;

@Controller
@RequestMapping("/dest")
public class DestinationController {
	@Autowired
	private DestinationService service;
	
	@RequestMapping("/{contentId}")
	public String showDestInfo(@PathVariable int contentId,Model model) throws Exception{
		JSONObject body = service.getDestInfo(contentId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("destInfo", item);
			model.addAttribute("comments", service.getCommentsByItemId(contentId));
		}
		
		return "destinfo";
	}
}
