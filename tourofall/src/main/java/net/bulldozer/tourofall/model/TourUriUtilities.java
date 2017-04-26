package net.bulldozer.tourofall.model;

import java.io.IOException;
import java.net.URLDecoder;

import org.springframework.web.util.UriComponentsBuilder;

public class TourUriUtilities {
	final static String baseUrl = "api.visitkorea.or.kr";
	final static String path="/openapi/service/rest/KorService/";
	
	public static UriComponentsBuilder getBaseUriComponentsBuilder(String pathRoot) throws IOException{
		return UriComponentsBuilder.newInstance()
				.scheme("http")
				.path(path+pathRoot)
				.host(baseUrl)
				.queryParam("ServiceKey"   , URLDecoder.decode("PKxRVEKq4aylIvyIJESCgJiM1SrJl7UXAiiBhuLddUMNQ0aC7U8Dn2%2F8XrrxfcTVLvHE9WBzTWD4LNLDWYyMQw%3D%3D", "UTF-8"))
				.queryParam("MobileOS" , (String)"ETC")
                .queryParam("MobileApp"  , (String)"AppTesting")
                .queryParam("_type"  , (String)"json");
	}
	
}
