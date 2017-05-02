package net.bulldozer.tourofall.service;

import java.net.URI;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.bulldozer.tourofall.dao.DestinationDao;
import net.bulldozer.tourofall.model.Answer;
import net.bulldozer.tourofall.model.FakeUser;
import net.bulldozer.tourofall.model.Question;
import net.bulldozer.tourofall.model.QuestionInfo;
import net.bulldozer.tourofall.model.Review;
import net.bulldozer.tourofall.model.TourJSONUtilities;
import net.bulldozer.tourofall.model.TourUriUtilities;

@Service
public class DestinationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DestinationDao dao;
	
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
	
	public FakeUser getUserByUserId(int userId){
		return dao.getUserByUserId(userId);
	}
	public List<Review> getReviewsByItemId(int itemId){
		return dao.getReviewsByItemId(itemId);
	}
	public void addReview(Review review){
		dao.addReview(review);
	}
	public List<QuestionInfo> getQuestionInfoesByItemId(int itemId) {
		return dao.getQuestionInfoesByItemId(itemId);
	}
	public Question getQuestionById(int questionId) {
		return dao.getQuestionById(questionId);
	}
	public QuestionInfo getQuestionInfoById(int questionId){
		return dao.getQuestionInfoById(questionId);
	}
	public void addQuestion(Question question) {
		dao.addQuestion(question);
	}
	public void addAnswer(Answer answer) {
		dao.addAnswer(answer);
	}
	
}
