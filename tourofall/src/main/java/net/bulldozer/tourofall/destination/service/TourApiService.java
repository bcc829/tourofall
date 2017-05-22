package net.bulldozer.tourofall.destination.service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import net.bulldozer.tourofall.destination.util.TourJSONUtilities;
import net.bulldozer.tourofall.destination.util.TourUriUtilities;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistrationsForm;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.recommendation.dto.RecommendationRenderingModel;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.repository.UserRepository;

@Service
public class TourApiService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EvaluationRepository evaluationRepository; 
	
	@Autowired 
	private UserRepository userRepository;
	
	
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
	public RecommendationRenderingModel getRecommendationRenderingModel(String itemId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", itemId);
		parameter.put("defaultYN", "Y");
		parameter.put("firstImageYN", "Y");
		JSONObject items = (JSONObject)(sendAndReceiveDataFromApiServer("detailCommon",parameter).get("items"));
		JSONObject item = (JSONObject)items.get("item");
		
		
		RecommendationRenderingModel recommendationRenderingModel = new RecommendationRenderingModel(Integer.parseInt(itemId), (String)item.get("firstimage"), (String)item.get("title"), 0,0);
		return recommendationRenderingModel;
	}
	
	private JSONObject sendAndReceiveDataFromApiServer(String serviceName, Map<String,String> parameter) throws Exception{
		UriComponentsBuilder urIbuilder = TourUriUtilities.getBaseUriComponentsBuilder(serviceName);
		Set<String> parameterName = parameter.keySet();
		Iterator<String> it = parameterName.iterator();
		System.out.println(parameterName.size());
		while(it.hasNext()){
			String key = it.next();
			System.out.println("name : "+ key +", value : " + parameter.get(key));
			urIbuilder.queryParam(key, parameter.get(key));
		}
		URI uri = urIbuilder.build().encode().toUri();
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
	
	
	private boolean addEvaluationRegistrationToList(List<EvaluationRegistration> evalList,Object obj){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject item = (JSONObject) obj;
		long itemId = (long)item.get("contentid");
		long userId = userAuthenticationDetails.getId();
	
		Evaluation evaluation = evaluationRepository.findByUserIdAndItemId(userId, (int)itemId);
		
		if(evaluation == null){
			EvaluationRegistration evaluationRegistration = new EvaluationRegistration((int)itemId, (String)item.get("firstimage"), (String)item.get("title"), 0);
			evalList.add(evaluationRegistration);
			return true;
		}
		return false;
	}
	
	
	@Transactional(readOnly=true)
	private int addEvaluationsToList(JSONObject result, List<EvaluationRegistration> evalList,int requestCount){
		int count = 0;
		JSONObject items = (JSONObject)result.get("items");
		
		if (((long)result.get("totalCount")) != 1) {
			System.out.println(result.get("totalCount"));
			JSONArray item = (JSONArray) items.get("item");
			System.out.println(item.size());
			for(Object temp : item){
				if(count >= requestCount) break;
				if(addEvaluationRegistrationToList(evalList, temp))	count++;
			}
		}else {
			Object item = items.get("item");
			System.out.println("totocalCount = 1");
			if(requestCount != 0){
				addEvaluationRegistrationToList(evalList, item);
				count++;
			}
		}
		return count;
	}


	public EvaluationRegistrationsForm getEvaluationRegistrationsForm(String itemCat1, String itemCat2, int pageNo) throws Exception {
		List<EvaluationRegistration> evalList = new ArrayList<EvaluationRegistration>();
		Map<String,String> parameter = new HashMap<String,String>();
		long totalPage;
		
		int requestCount = 12;
		while(true){
			System.out.println(requestCount);
			parameter.put("cat1", itemCat1);
			parameter.put("cat2", itemCat2);
			parameter.put("listYN", "Y");
			parameter.put("arrange", "B");
			parameter.put("numOfRows"  , "12");
			parameter.put("pageNo", Integer.toString(pageNo));
		
			JSONObject result = sendAndReceiveDataFromApiServer("areaBasedList",parameter);
			
			
			requestCount -= addEvaluationsToList(result,evalList,requestCount);
			pageNo++;
			parameter.clear();
			
			if(requestCount == 0){
				long totalCount = (long)result.get("totalCount");
				long numOfRows = (long)result.get("numOfRows");
				
				totalPage = totalCount/numOfRows;
				if(totalCount%numOfRows != 0){
					totalPage++;
				}
				
				break;
			}
		}	
		return new EvaluationRegistrationsForm(evalList,totalPage);
	}
	
	public JSONObject getSimpleSearchResult(String query, String pageNum) throws Exception {
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("keyword", URLDecoder.decode(URLEncoder.encode(query, "UTF-8"), "UTF-8"));
		parameter.put("pageNo", pageNum);
		parameter.put("arrange", "A");
		parameter.put("numOfRows"  , "8");
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
		parameter.put("numOfRows"  , "8");
		parameter.put("listYN", "Y");
		
		return sendAndReceiveDataFromApiServer("searchKeyword",parameter);
	}
	public String getItemImage(int contentId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("imageYN","Y");

		String imageUrl = null;
		
		JSONObject body = (JSONObject)(sendAndReceiveDataFromApiServer("detailImage",parameter));
		if(body == null){
			return imageUrl;
		}
		
		JSONObject items = (JSONObject)(body.get("items"));
		
		if((long)body.get("totalCount") == 1){
			JSONObject item = (JSONObject)items.get("item");
			imageUrl = (String)item.get("originimgurl");
		}else{
			JSONArray item = (JSONArray)items.get("item");
			for(Object obj : item){
				JSONObject jObj = (JSONObject)obj;
				if(jObj.get("originimgurl") != null){
					imageUrl = (String)jObj.get("originimgurl");
					break;
				}
				
			}
		}
		return imageUrl;
	}

	public JSONObject getBasicInfo(int contentId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
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
