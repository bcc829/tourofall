package net.bulldozer.tourofall.service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SearchService {
	final String baseUrl = "api.visitkorea.or.kr";
	@Autowired
	RestTemplate restTemplate;
	
	
	public JSONObject getSearchResult(String query) throws Exception{
		URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(baseUrl)
                .path("/openapi/service/rest/KorService/searchKeyword")
                .queryParam("ServiceKey"   , URLDecoder.decode("PKxRVEKq4aylIvyIJESCgJiM1SrJl7UXAiiBhuLddUMNQ0aC7U8Dn2%2F8XrrxfcTVLvHE9WBzTWD4LNLDWYyMQw%3D%3D", "UTF-8"))
                .queryParam("keyword"     , URLDecoder.decode(URLEncoder.encode(query, "UTF-8"),"UTF-8"))
                .queryParam("areaCode"     , (String) "")
                .queryParam("sigunguCode"     , (String) "")
                .queryParam("cat1"     , (String) "")
                .queryParam("cat2"     , (String) "")
                .queryParam("cat3"     , (String) "")
                .queryParam("listYN"      , (String) "Y")
                .queryParam("MobileOS" , (String)"ETC")
                .queryParam("MobileApp"  , (String)"AppTesting")
                .queryParam("arrange"  , (String)"A")
                .queryParam("numOfRows"  , (String)"8")
                .queryParam("pageNo", (String)"1")
                .queryParam("_type"  , (String)"json")
                .build()
                .encode()
                .toUri();
		
		JSONParser parser = new JSONParser();
		String result = restTemplate.getForObject(uri, String.class);
		JSONObject jsonObject = (JSONObject)parser.parse(result);
		JSONObject response = (JSONObject)jsonObject.get("response");
		JSONObject body = (JSONObject)response.get("body");
		Object tItems = body.get("items");
		if(tItems instanceof String)
			return null;
		return body;
//		JSONObject items = (JSONObject)tItems;
//		JSONArray item = (JSONArray)items.get("item");
		
	}
}
