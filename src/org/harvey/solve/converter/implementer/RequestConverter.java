package org.harvey.solve.converter.implementer;

import java.util.List;

import org.harvey.solve.converter.converterinterface.ConverterInterface;
import org.harvey.solve.dto.Circle;
import org.harvey.solve.dto.Request;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestConverter implements ConverterInterface<Request>{

	@Override
	public Request fromJson(JSONObject jsonObj) {
		JSONObject circleObj = jsonObj.getJSONObject("circle");
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> persons = (List<String>)(List)circleObj.getJSONArray("persons").toList();
		int start = circleObj.getInt("start");
		int interval = circleObj.getInt("interval");
		
		Circle circle = new Circle((String[]) persons.toArray(), start, interval);
		
		return new Request(circle);
	}

	@Override
	public JSONObject toJson(Request requestObj) {
		Circle circle = requestObj.getCircle();
		
		JSONObject circleObj = new JSONObject();
		JSONArray persons = new JSONArray(circle.getPersons());
		circleObj.put("start", circle.getStart());
		circleObj.put("interval", circle.getInterval());
		circleObj.put("persons", persons);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("circle", circleObj);
		
		return jsonObject;
	}
		
}
