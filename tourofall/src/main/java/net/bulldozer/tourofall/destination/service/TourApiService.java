package net.bulldozer.tourofall.destination.service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.bulldozer.tourofall.util.TourJSONUtilities;
import net.bulldozer.tourofall.util.TourUriUtilities;

@Service
public class TourApiService {
	@Autowired
	private RestTemplate restTemplate;
	public String getItemTitle(int contentId) throws Exception{
		URI uri = TourUriUtilities.getBaseUriComponentsBuilder("detailCommon")
				.queryParam("contentId"     , contentId)
                .queryParam("defaultYN"     , (String) "Y")
                .build()
                .encode()
                .toUri();
		
		String jsonResult = restTemplate.getForObject(uri, String.class);
		JSONObject items = (JSONObject)(TourJSONUtilities.getTourItems(jsonResult).get("items"));
		JSONObject item = (JSONObject)items.get("item");
		
		return (String)item.get("title");
	}
	public JSONObject getBasicInfo(int contentId, int contentTypeId) throws Exception{
		URI uri = TourUriUtilities.getBaseUriComponentsBuilder("detailCommon")
				.queryParam("contentId"     , contentId)
				.queryParam("contentTypeId"     , contentTypeId)
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
	public JSONObject getIntroInfo(int contentId,int contentTypeId) throws Exception{
		URI uri = TourUriUtilities.getBaseUriComponentsBuilder("detailIntro")
				.queryParam("contentId"     , contentId)
				.queryParam("contentTypeId"     , contentTypeId)
                .queryParam("introYN"     , (String) "Y")
                .build()
                .encode()
                .toUri();
		
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
	public JSONObject getDetailInfo(int contentId,int contentTypeId) throws Exception{
		URI uri = TourUriUtilities.getBaseUriComponentsBuilder("detailInfo")
				.queryParam("contentId"     , contentId)
				.queryParam("contentTypeId"     , contentTypeId)
                .queryParam("listYN"     , (String) "Y")
                .build()
                .encode()
                .toUri();
		
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
	
	public JSONObject getImageInfo(int contentId,int contentTypeId) throws Exception{
		URI uri = TourUriUtilities.getBaseUriComponentsBuilder("detailImage")
				.queryParam("contentId"     , contentId)
				.queryParam("contentTypeId"     , contentTypeId)
                .queryParam("imageYN"     , (String) "Y")
                .build()
                .encode()
                .toUri();
		
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
	
	public JSONObject getSearchResult(String query, String pageNum) throws Exception {
		URI uri = TourUriUtilities.getBaseUriComponentsBuilder("searchKeyword")
				.queryParam("keyword", URLDecoder.decode(URLEncoder.encode(query, "UTF-8"), "UTF-8"))
				.queryParam("pageNo", pageNum)
				.queryParam("arrange", (String) "A")
				.queryParam("listYN", (String) "Y")
				.build().encode().toUri();		
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
}
