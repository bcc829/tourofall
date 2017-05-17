package net.bulldozer.tourofall.destination.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bulldozer.tourofall.destination.service.TourApiService;
import net.bulldozer.tourofall.destination.util.DetailInfoUtil;

@Controller
@RequestMapping("/search")
public class SearchController {
	private static final int PAGE_COUNT = 5;
	@Autowired
	private TourApiService tourApiService;
	
	public void addPageContentToModel(JSONObject body, Model model){
		JSONObject items = (JSONObject) body.get("items");
		
		long numOfRows = (Long)body.get("numOfRows");
		long pageNo = (Long)body.get("pageNo");
		long totalCount = (Long)body.get("totalCount");
		
		System.out.println("numOfRows: "+numOfRows +", pageNo: " + pageNo +", totalCount: " + totalCount);
		long listIndex = (pageNo-1)/PAGE_COUNT;
		long totalPage = totalCount/numOfRows;
		if(totalCount%numOfRows != 0){
			totalPage++;
		}
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageNo", (Long)body.get("pageNo"));
		model.addAttribute("totalPage", totalPage);
		
		long listTotal = totalPage/PAGE_COUNT;
			
		System.out.println("listIndex: "+listIndex +", totalPage: " + totalPage +", listTotal: " + listTotal);
		
		
		List<Long> list = new ArrayList<Long>();
		
		if(listIndex == listTotal && totalPage%PAGE_COUNT != 0){
			for(Long i = 1 + listIndex*5; i <= listIndex*5 + totalPage%PAGE_COUNT; i++){
				list.add(i);
			}
		}else{
			for(long i = 1 + listIndex*5; i <= 5 + listIndex*5; i++){
				list.add(i);
			}
		}
		
		System.out.println(list);
		
		model.addAttribute("rowNumList", list);
		if (((Long)body.get("totalCount")) != 1) {
			JSONArray item = (JSONArray) items.get("item");
			model.addAttribute("items", item);
		} else {
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("item", item);
		}
	}
	@RequestMapping(value="/simple", method = RequestMethod.GET)
	public String simpleSearch(@RequestParam(value = "s", required = false) String query, @RequestParam(value="p",defaultValue="1") String pageNum, Model model) throws Exception {
		if (query != null) {
			JSONObject body = tourApiService.getSimpleSearchResult(query,pageNum);
			if (body != null) {
				model.addAttribute("query", query);
				addPageContentToModel(body,model);
			}
		}
		return "simple_search";
	}
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detailSearch(@RequestParam(value="s",defaultValue="") String query, @RequestParam(value="p",defaultValue="1") String pageNum, @RequestParam(value="ta", defaultValue="") String ta, @RequestParam(value="sc", defaultValue="") String sc, @RequestParam(value="a", defaultValue="") String a,Model model) throws Exception{
		model.addAttribute("tourTypes", DetailInfoUtil.getTourType());
		model.addAttribute("services", DetailInfoUtil.getServiceCategory());
		model.addAttribute("areas", DetailInfoUtil.getProvince());
		
		if(!(query.equals("") && ta.equals("") && sc.equals("") && a.equals(""))){
			System.out.println("detail search started : 타입 = " + ta +", 분류 = " + sc + ", 지역선택 = " + a);
			JSONObject body = tourApiService.getDetailSearchResult(query, pageNum, ta, sc, a);
			if (body != null) {
				System.out.println("search result");
				model.addAttribute("query", query);
				addPageContentToModel(body,model);
			}
		}
		return "detail_search";
	}
}
