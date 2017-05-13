package net.bulldozer.tourofall.destination.service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import net.bulldozer.tourofall.destination.util.TourJSONUtilities;
import net.bulldozer.tourofall.destination.util.TourUriUtilities;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;

@Service
public class TourApiService {
	@Autowired
	private RestTemplate restTemplate;
	
	public EvaluationRegistration getEvaluationRegistrationsInfo(String itemId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", itemId);
		parameter.put("defaultYN", "Y");
		parameter.put("firstImageYN", "Y");
		JSONObject items = (JSONObject)(sendAndReceiveDataFromApiServer("detailCommon",parameter).get("items"));
		JSONObject item = (JSONObject)items.get("item");
		
		
		EvaluationRegistration dEval = new EvaluationRegistration(Integer.parseInt(itemId), (String)item.get("firstimage"), (String)item.get("title"), 0);
		return dEval;
	}
	private JSONObject sendAndReceiveDataFromApiServer(String serviceName, Map<String,String> parameter) throws Exception{
		UriComponentsBuilder urIbuilder = TourUriUtilities.getBaseUriComponentsBuilder(serviceName);
		Set<String> parameterName = parameter.keySet();
		Iterator<String> it = parameterName.iterator();
		//System.out.println(parameterName.size());
		while(it.hasNext()){
			String key = it.next();
			//System.out.println("name : "+ key +", value : " + parameter.get(key));
			urIbuilder.queryParam(key, parameter.get(key));
		}
		URI uri = urIbuilder.build().encode().toUri();
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
	 
	public JSONObject getSimpleSearchResult(String query, String pageNum) throws Exception {
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("keyword", URLDecoder.decode(URLEncoder.encode(query, "UTF-8"), "UTF-8"));
		parameter.put("pageNo", pageNum);
		parameter.put("arrange", "A");
		parameter.put("listYN", "Y");
		
		return sendAndReceiveDataFromApiServer("searchKeyword",parameter);
	}
	public JSONObject getDetailSearchResult(String query, String pageNum,String ta, String sc, String a) throws Exception{
		System.out.println(query+", "+pageNum+", "+ta+", "+sc+", "+a);
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("keyword", URLDecoder.decode(URLEncoder.encode(query, "UTF-8"), "UTF-8"));
		parameter.put("pageNo", pageNum);
		parameter.put("contentTypeId", ta);
		parameter.put("cat1", sc);
		parameter.put("areaCode", a);
		parameter.put("arrange", "A");
		parameter.put("listYN", "Y");
		
		return sendAndReceiveDataFromApiServer("searchKeyword",parameter);
	}
	public String getItemTitle(int contentId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("defaultYN","Y");

		
		JSONObject items = (JSONObject)(sendAndReceiveDataFromApiServer("detailCommon",parameter).get("items"));
		JSONObject item = (JSONObject)items.get("item");
		
		return (String)item.get("title");
	}
	public JSONObject getBasicInfo(int contentId, int contentTypeId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("contentTypeId", Integer.toString(contentTypeId));
		parameter.put("defaultYN", "Y");
		parameter.put("firstImageYN", "Y");
		parameter.put("areaCodeYN","Y");
		parameter.put("addrinfoYN","Y");
		parameter.put("mapinfoYN","Y");
		parameter.put("overviewYN","Y");
		return sendAndReceiveDataFromApiServer("detailCommon",parameter);
	}
	public JSONObject getIntroInfo(int contentId,int contentTypeId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("contentTypeId", Integer.toString(contentTypeId));
		parameter.put("introYN","Y");
		return sendAndReceiveDataFromApiServer("detailIntro",parameter);
	}
	public JSONObject getDetailInfo(int contentId,int contentTypeId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("contentTypeId", Integer.toString(contentTypeId));
		parameter.put("introYN","Y");
		return sendAndReceiveDataFromApiServer("detailInfo",parameter);
	}
	
	public JSONObject getImageInfo(int contentId,int contentTypeId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("contentTypeId", Integer.toString(contentTypeId));
		parameter.put("imageYN","Y");
		return sendAndReceiveDataFromApiServer("detailImage",parameter);
	}
}
