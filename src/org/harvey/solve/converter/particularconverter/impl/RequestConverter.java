package org.harvey.solve.converter.particularconverter.impl;

import java.util.List;

import org.harvey.solve.converter.particularconverter.Converter;
import org.harvey.solve.dto.Circle;
import org.harvey.solve.dto.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RequestConverter implements Converter<Request>{

	@Override
	public Request fromJson(JSONObject jsonObj) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		JSONObject circleObj = jsonObj.getJSONObject("circle");
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> persons = (List<String>)(List)circleObj.getJSONArray("persons").toList();
		int start = circleObj.getInt("start");
		int interval = circleObj.getInt("interval");
		
		Circle circle = (Circle) context.getBean("circle");
		circle.setInterval(interval);
		circle.setStart(start);
		circle.setPersons(persons);
		
		((ConfigurableApplicationContext)context).close();
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
