package net.bulldozer.tourofall.destination.util;

import java.util.ArrayList;

import net.bulldozer.tourofall.destination.dto.Area;
import net.bulldozer.tourofall.destination.dto.ItemType;
import net.bulldozer.tourofall.destination.dto.ServiceCategory;

public class DetailInfoUtil {
	private static final ArrayList<ItemType> tourType= new ArrayList<ItemType>();
	private static final ArrayList<ServiceCategory> serviceCategory = new ArrayList<ServiceCategory>();
	private static final ArrayList<Area> area = new ArrayList<Area>();
	static{
		tourType.add(new ItemType("관광지",12));
		tourType.add(new ItemType("문화시설",14));
		tourType.add(new ItemType("행사/공연/축제",15));
		tourType.add(new ItemType("여행코스",25));
		tourType.add(new ItemType("레포츠",28));
		tourType.add(new ItemType("숙박",32));
		tourType.add(new ItemType("쇼핑",38));
		tourType.add(new ItemType("음식점",39));
		
		serviceCategory.add(new ServiceCategory("자연","A01"));
		serviceCategory.add(new ServiceCategory("인문(문화/예술/역사)","A02"));
		serviceCategory.add(new ServiceCategory("레포츠","A03"));
		serviceCategory.add(new ServiceCategory("쇼핑","A04"));
		serviceCategory.add(new ServiceCategory("음식","A05"));
		serviceCategory.add(new ServiceCategory("숙박","B02"));
		serviceCategory.add(new ServiceCategory("추천코스","C01"));
		
		
		area.add(new Area("서울",1));
		area.add(new Area("인천",2));
		area.add(new Area("대전",3));
		area.add(new Area("대구",4));
		area.add(new Area("광주",5));
		area.add(new Area("부산",6));
		area.add(new Area("울산",7));
		area.add(new Area("세종특별자치시",8));
		area.add(new Area("경기도",31));
		area.add(new Area("강원도",32));
		area.add(new Area("충청북도",33));
		area.add(new Area("충청남도",34));
		area.add(new Area("경상북도",35));
		area.add(new Area("경상남도",36));
		area.add(new Area("전라북도",37));
		area.add(new Area("전라남도",38));
		area.add(new Area("제주도",39));
	}
	
	public static ArrayList<ItemType> getTourType(){
		return new ArrayList<ItemType>(tourType);
	}
	public static ArrayList<ServiceCategory> getServiceCategory(){
		return new ArrayList<ServiceCategory>(serviceCategory);
	}
	public static ArrayList<Area> getProvince(){
		return new ArrayList<Area>(area);
	}
}
