package net.bulldozer.tourofall.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bulldozer.tourofall.destination.dto.BestDestinationRenderingModel;
import net.bulldozer.tourofall.destination.service.TodayDestinationService;

@Controller
public class HomeController {
	@Autowired
	private TodayDestinationService todayDestinationService;  
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("todayDestinationRenderingModels", todayDestinationService.getTodayDestinationRenderingModels());
		List<BestDestinationRenderingModel> bestDestinationRenderingModels = new ArrayList<BestDestinationRenderingModel>();
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("���� ���ð� ����",128526,"http://tong.visitkorea.or.kr/cms/resource/44/2367744_image2_1.jpg","�Ŷ� �ձ��� ������"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("�ϴù�������",2022638,"http://tong.visitkorea.or.kr/cms/resource/25/2022625_image2_1.jpg","�ӵ�� ����� �帣�� ���ȣ��"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("��������������",128578,"http://tong.visitkorea.or.kr/cms/resource/89/2032689_image2_1.jpg","��޸� �������� ��� �������"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("���ι潺 ����",129196,"http://tong.visitkorea.or.kr/cms/resource/01/1998801_image2_1.jpg","���ι潺 ������, ����, �мǰ�, ī��� �����Ǿ��� �׸��� ����"));
		model.addAttribute("bestDestinationRenderingModels", bestDestinationRenderingModels);
		return "home";
	}
}
