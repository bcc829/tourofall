package net.bulldozer.tourofall.service;

import java.net.URI;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.bulldozer.tourofall.dao.DestinationDao;
import net.bulldozer.tourofall.model.Comment;
import net.bulldozer.tourofall.model.TourJSONUtilities;
import net.bulldozer.tourofall.model.TourUriUtilities;

@Service
public class DestinationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DestinationDao dao;
	
	public JSONObject getDestInfo(int contentId) throws Exception{
		URI uri = TourUriUtilities.getBaseUriComponentsBuilder("detailCommon")
				.queryParam("contentId"     , contentId)
                .queryParam("defaultYN"     , (String) "Y")
                .queryParam("firstImageYN"     , (String) "Y")
                .queryParam("areaCodeYN"     , (String) "Y")
                .queryParam("addrinfoYN"     , (String) "Y")
                .queryParam("mapinfoYN"     , (String) "Y")
                .queryParam("overviewYN"     , (String) "Y")
                .build()
                .encode()
                .toUri();
		
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
	public List<Comment> getCommentsByItemId(int itemId){
		return dao.getCommentsByItemId(itemId);
	}
	
}
