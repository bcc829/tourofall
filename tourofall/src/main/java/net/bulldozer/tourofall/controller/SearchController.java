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

	@RequestMapping(method = RequestMethod.GET)
	public String search(@RequestParam(value = "s", required = false) String query, Model model) throws Exception {
		if (query != null) {
			JSONObject body = service.getSearchResult(query);
			if (body != null) {
				JSONObject items = (JSONObject) body.get("items");
				if (((Long)body.get("totalCount")) != 1) {
					JSONArray item = (JSONArray) items.get("item");
					model.addAttribute("items", item);
				} else {
					JSONObject item = (JSONObject) items.get("item");
					model.addAttribute("item", item);
				}
			}
		}
		return "search";
	}
}
