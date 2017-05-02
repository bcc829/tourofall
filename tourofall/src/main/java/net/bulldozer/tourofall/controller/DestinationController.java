package net.bulldozer.tourofall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bulldozer.tourofall.model.Answer;
import net.bulldozer.tourofall.model.FakeUser;
import net.bulldozer.tourofall.model.Question;
import net.bulldozer.tourofall.model.Review;
import net.bulldozer.tourofall.service.DestinationService;

@Controller
@RequestMapping("/dest")
public class DestinationController {
	private static final String resPath="/{itemTypeId}/{itemId}";
	@Autowired
	private DestinationService service;

	@RequestMapping("/info/basic"+resPath)
	public String showBasicInfo(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) throws Exception {
		JSONObject body = service.getBasicInfo(itemId, itemTypeId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("basicInfo", item);
		}
		model.addAttribute("itemId", itemId);
		model.addAttribute("itemTypeId", itemTypeId);
		model.addAttribute("reviews", service.getReviewsByItemId(itemId));
		model.addAttribute("questionInfoes", service.getQuestionInfoesByItemId(itemId));
		return "dest-basicinfo";
	}

	@RequestMapping("/info/intro"+resPath)
	public String showIntroInfo(@PathVariable int itemId,@PathVariable int itemTypeId, Model model) throws Exception {
		JSONObject body = service.getIntroInfo(itemId, itemTypeId);
		if (body != null) {
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("introInfo", item);
		}
		model.addAttribute("itemId", itemId);
		model.addAttribute("itemTypeId", itemTypeId);
		model.addAttribute("reviews", service.getReviewsByItemId(itemId));
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
		JSONObject body = service.getDetailInfo(itemId, itemTypeId);
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
		model.addAttribute("reviews", service.getReviewsByItemId(itemId));

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
		JSONObject body = service.getImageInfo(itemId, itemTypeId);
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
		model.addAttribute("reviews", service.getReviewsByItemId(itemId));
		return "dest-imageinfo";
	}

	@RequestMapping(value = "/info/review/write/{itemTypeId}/{itemId}", method = RequestMethod.GET)
	public String showReviewForm(@PathVariable int itemId,@PathVariable int itemTypeId, Model model, HttpServletRequest request) {
		Review review = new Review();
		review.setItemId(itemId);
		review.setItemTypeId(itemTypeId);
		if (!model.containsAttribute("review"))
			model.addAttribute("review", review);

		if (!model.containsAttribute("result")) {
			System.out.println("Binding result not rendered");
		}
		model.addAttribute("username",
				service.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName())).getUsername());
		return "review_write";
	}

	@RequestMapping(value = "/info/review/write", method = RequestMethod.POST)
	public String processRegisterReview(@Valid Review review, BindingResult result, RedirectAttributes model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addFlashAttribute("review", review);
			model.addFlashAttribute("org.springframework.validation.BindingResult.review", result);
			return "redirect:/dest/info/review/write/" + review.getItemTypeId() +"/"+review.getItemId();
		}
		FakeUser user = service.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName()));
		review.setUser(user);
		service.addReview(review);

		return "redirect:/dest/info/basic/" + review.getItemTypeId() +"/"+review.getItemId();
	}
	@RequestMapping(value="/info/qna/question/{questionId}",method=RequestMethod.GET)
	public String showQuestionPage(@PathVariable int questionId,Model model){
		model.addAttribute("question", service.getQuestionById(questionId));
		model.addAttribute("answer", new Answer());
		return "question";
	}
	@RequestMapping(value="/info/qna/question/write/{itemTypeId}/{itemId}",method=RequestMethod.GET)
	public String showQuestionForm(@PathVariable int itemId,@PathVariable int itemTypeId, Model model, HttpServletRequest request){
		Question question = new Question();
		question.setItemId(itemId);
		question.setItemTypeId(itemTypeId);
		
		if (!model.containsAttribute("question"))
			model.addAttribute("question", question);

		if (!model.containsAttribute("result")) {
			System.out.println("Binding result not rendered");
		}
		model.addAttribute("username",
				service.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName())).getUsername());
		
		return "question_write";
	}
	@RequestMapping(value="/info/qna/question/write",method=RequestMethod.POST)
	public String processRegisterQuestion(Question question, Model model,HttpServletRequest request){
		FakeUser user = service.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName()));
		question.setUser(user);
		service.addQuestion(question);
		return "redirect:/dest/info/basic/" + question.getItemTypeId() +"/"+question.getItemId();
	}
	@RequestMapping(value="/info/qna/answer/write/{questionId}",method=RequestMethod.POST)
	public String processRegisterAnswer(@PathVariable int questionId, Answer answer, Model model,HttpServletRequest request){
		FakeUser user = service.getUserByUserId(Integer.parseInt(request.getUserPrincipal().getName()));
		answer.setUser(user);
		answer.setQuestion(service.getQuestionInfoById(questionId));
		service.addAnswer(answer);
		return "redirect:/dest/info/qna/question/"+ questionId;
	}
}
