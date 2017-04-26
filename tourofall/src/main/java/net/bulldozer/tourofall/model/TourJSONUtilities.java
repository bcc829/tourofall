package net.bulldozer.tourofall.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TourJSONUtilities {
	
	public static JSONObject getTourItems(String jsonResult) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonResult);
		JSONObject response = (JSONObject) jsonObject.get("response");
		JSONObject body = (JSONObject) response.get("body");
		Object tItems = body.get("items");
		if (tItems instanceof String)
			return null;
		return body;
	}
}
