package net.bulldozer.tourofall.service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.bulldozer.tourofall.model.TourJSONUtilities;
import net.bulldozer.tourofall.model.TourUriUtilities;

@Service
public class SearchService {
	final String baseUrl = "api.visitkorea.or.kr";

	@Autowired
	RestTemplate restTemplate;

	public JSONObject getSearchResult(String query) throws Exception {
		URI uri = TourUriUtilities.getBaseUriComponentsBuilder("searchKeyword")
				.queryParam("keyword", URLDecoder.decode(URLEncoder.encode(query, "UTF-8"), "UTF-8"))
				.queryParam("arrange", (String) "A")
				.queryParam("listYN", (String) "Y")
				.build().encode().toUri();		
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
}
