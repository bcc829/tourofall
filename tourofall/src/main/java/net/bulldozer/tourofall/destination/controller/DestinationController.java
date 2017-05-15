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
import net.bulldozer.tourofall.review.model.Review;
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
		model.addAttribute("reviews", reviewService.getReviewsByItemId(itemId));
		model.addAttribute("questionInfoes", questionService.getQuestionsByItemId(itemId));
		
	}
	@RequestMapping("/info/basic"+resPath)
	public String showBasicInfo(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) throws Exception {
		JSONObject body = tourApiService.getBasicInfo(itemId, itemTypeId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("basicInfo", item);
		}
		addAttributeToModel(itemId,itemTypeId,model);
		
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
		addAttributeToModel(itemId,itemTypeId,model);
		String type = "";

		switch (itemTypeId) {
		case 12: // ������
			type = "tourist_attraction";
			break;
		case 14: // ��ȭ�ü�
			type = "cultural_facilities";
			break;
		case 15: // �̺�Ʈ
			type = "event";
			break;
		case 25: // �����ڽ�
			type = "tour_course";
			break;
		case 28: // ������
			type = "leports";
			break;
		case 32: // ����
			type = "lodge";
			break;
		case 38: // ����
			type = "shopping";
			break;
		case 39: // ������
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
		addAttributeToModel(itemId,itemTypeId,model);
		String type = "";

		switch (itemTypeId) {
		case 25: // �����ڽ�
			type = "tour_course";
			break;
		case 32: // ����
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
		addAttributeToModel(itemId,itemTypeId,model);
		return "dest-imageinfo";
	}
}
