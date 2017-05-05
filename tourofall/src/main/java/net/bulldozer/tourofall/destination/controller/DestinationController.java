package net.bulldozer.tourofall.destination.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.qna.service.QuestionService;
import net.bulldozer.tourofall.review.service.ReviewService;

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
	
	@RequestMapping("/info/basic"+resPath)
	public String showBasicInfo(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) throws Exception {
		JSONObject body = tourApiService.getBasicInfo(itemId, itemTypeId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("basicInfo", item);
		}
		model.addAttribute("itemId", itemId);
		model.addAttribute("itemTypeId", itemTypeId);
		model.addAttribute("reviews", reviewService.getReviewsByItemId(itemId));
		model.addAttribute("questionInfoes", questionService.getQuestionsByItemId(itemId));
		return "dest-basicinfo";
	}

	@RequestMapping("/info/intro"+resPath)
	public String showIntroInfo(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) throws Exception {
		JSONObject body = tourApiService.getIntroInfo(itemId, itemTypeId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("introInfo", item);
		}
		model.addAttribute("itemId", itemId);
		model.addAttribute("itemTypeId", itemTypeId);
		model.addAttribute("reviews", reviewService.getReviewsByItemId(itemId));
		model.addAttribute("questionInfoes", questionService.getQuestionsByItemId(itemId));
		String type = "";

		switch (itemTypeId) {
		case 12: // 관광지
			type = "tourist_attraction";
			break;
		case 14: // 문화시설
			type = "cultural_facilities";
			break;
		case 15: // 이벤트
			type = "event";
			break;
		case 25: // 여행코스
			type = "tour_course";
			break;
		case 28: // 레포츠
			type = "leports";
			break;
		case 32: // 숙박
			type = "lodge";
			break;
		case 38: // 쇼핑
			type = "shopping";
			break;
		case 39: // 음식점
			type = "food";
			break;
		}
		return "dest-introinfo-" + type;
	}

	@RequestMapping("/info/detail"+resPath)
	public String showDetailInfo(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) throws Exception {
		JSONObject body = tourApiService.getDetailInfo(itemId, itemTypeId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");

			if (((Long) body.get("totalCount")) != 1) {
				JSONArray item = (JSONArray) items.get("item");
				model.addAttribute("detailInfoes", item);
				System.out.println("detailInfoes selected");
			} else {
				JSONObject item = (JSONObject) items.get("item");
				model.addAttribute("detailInfo", item);
				System.out.println("detailInfo selected");
			}
		}
		model.addAttribute("itemId", itemId);
		model.addAttribute("itemTypeId", itemTypeId);
		model.addAttribute("reviews", reviewService.getReviewsByItemId(itemId));
		model.addAttribute("questionInfoes", questionService.getQuestionsByItemId(itemId));
		String type = "";

		switch (itemTypeId) {
		case 25: // 여행코스
			type = "tour_course";
			break;
		case 32: // 숙박
			type = "lodge";
			break;
		default:
			type="common";
			break;
		}

		return "dest-detailinfo-"+type;
	}

	@RequestMapping("/info/image"+resPath)
	public String showImageInfo(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) throws Exception {
		JSONObject body = tourApiService.getImageInfo(itemId, itemTypeId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");

			if (((Long) body.get("totalCount")) != 1) {
				JSONArray item = (JSONArray) items.get("item");
				model.addAttribute("imageInfoes", item);
				System.out.println("imageInfoes selected");
			} else {
				JSONObject item = (JSONObject) items.get("item");
				model.addAttribute("imageInfo", item);
				System.out.println("imageInfo selected");
			}
		}
		model.addAttribute("itemId", itemId);
		model.addAttribute("itemTypeId", itemTypeId);
		model.addAttribute("reviews", reviewService.getReviewsByItemId(itemId));
		model.addAttribute("questionInfoes", questionService.getQuestionsByItemId(itemId));
		return "dest-imageinfo";
	}
	
	
}
