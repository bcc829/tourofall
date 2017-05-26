package net.bulldozer.tourofall.destination.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.question.service.QuestionService;
import net.bulldozer.tourofall.review.dto.Review;
import net.bulldozer.tourofall.review.service.ReviewService;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;

@Controller
@RequestMapping("/dest")
public class DestinationController {
	private static final String resPath="/{itemTypeId}/{itemId}";
	@Autowired
	private TourApiService tourApiService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private QuestionService questionService;
	
	public void addAttributeToModel(int itemId, int itemTypeId,Model model){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")){
			UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Review review = reviewService.findByUserIdAndItemId(userAuthenticationDetails.getId(), itemId);
			if(review == null){
				model.addAttribute("reviewWrite", true);
			}
		}else{
			model.addAttribute("reviewWrite", true);
		}
		
		model.addAttribute("itemId", itemId);
		model.addAttribute("itemTypeId", itemTypeId);
		model.addAttribute("reviewRenderingModels", reviewService.getReviewRenderingModelsByItemId(itemId));
		model.addAttribute("questionRenderingModels", questionService.getQuestionRenderingModelsByItemId(itemId));
		
	}
	
	@RequestMapping("/info/{itemId}")
	public String showDestinationInfo(@PathVariable int itemId, Model model) throws Exception{
		JSONObject body1 = tourApiService.getBasicInfo(itemId);
		int itemTypeId = 0;
		if (body1 != null) {
			JSONObject items = (JSONObject) body1.get("items");
			JSONObject item = (JSONObject) items.get("item");
			long contentTypeId = (long)item.get("contenttypeid");
			itemTypeId = (int)contentTypeId;
			model.addAttribute("basicInfo", item);
		}
		
		JSONObject body2 = tourApiService.getIntroInfo(itemId, itemTypeId);
		if (body2 != null) {
			JSONObject items = (JSONObject) body2.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("introInfo", item);
		}
		
		JSONObject body3 = tourApiService.getDetailInfo(itemId, itemTypeId);
		if (body3 != null) {
			JSONObject items = (JSONObject) body3.get("items");

			if (((Long) body3.get("totalCount")) != 1) {
				JSONArray item = (JSONArray) items.get("item");
				model.addAttribute("detailInfoes", item);
				System.out.println("detailInfoes selected");
			} else {
				JSONObject item = (JSONObject) items.get("item");
				model.addAttribute("detailInfo", item);
				System.out.println("detailInfo selected");
			}
		}
		
		JSONObject body4 = tourApiService.getImageInfo(itemId, itemTypeId);
		if (body4 != null) {
			JSONObject items = (JSONObject) body4.get("items");

			if (((Long) body4.get("totalCount")) != 1) {
				JSONArray item = (JSONArray) items.get("item");
				model.addAttribute("imageInfoes", item);
				System.out.println("imageInfoes selected");
			} else {
				JSONObject item = (JSONObject) items.get("item");
				model.addAttribute("imageInfo", item);
				System.out.println("imageInfo selected");
			}
		}
		
		
		
		return "dest-info";
	}
	
	@RequestMapping("/info/basic/{itemId}")
	public String showBasicInfo(@PathVariable int itemId, Model model) throws Exception {
		JSONObject body = tourApiService.getBasicInfo(itemId);
		int itemTypeId = 0;
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			long contentTypeId = (long)item.get("contenttypeid");
			itemTypeId = (int)contentTypeId;
			model.addAttribute("basicInfo", item);
		}
		addAttributeToModel(itemId,itemTypeId,model);
		
		return "dest-basicinfo";
	}
}
