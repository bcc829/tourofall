package net.bulldozer.tourofall.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bulldozer.tourofall.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(@RequestParam(value="s", required = false) String query,Model model) throws Exception{
		if(query != null){
			JSONObject body = service.getSearchResult(query);
			JSONObject items = (JSONObject)body.get("items");
			JSONArray item = (JSONArray)items.get("item");
			model.addAttribute("totalCount",body.get("totalCount"));
			model.addAttribute("items", item);
			model.addAttribute("pageNo", body.get("pageNo"));
		}
		return "search";
	}
}
