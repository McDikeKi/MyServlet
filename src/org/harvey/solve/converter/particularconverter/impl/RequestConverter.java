package org.harvey.solve.converter.particularconverter.impl;

import java.util.List;

import org.harvey.solve.converter.particularconverter.Converter;
import org.harvey.solve.dto.JosephProblemCircle;
import org.harvey.solve.dto.JosephProblemRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RequestConverter implements Converter<JosephProblemRequest>{

	@Override
	public JosephProblemRequest fromJson(JSONObject jsonObj) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		JSONObject circleObj = jsonObj.getJSONObject("circle");
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> persons = (List<String>)(List)circleObj.getJSONArray("persons").toList();
		int start = circleObj.getInt("start");
		int interval = circleObj.getInt("interval");
		
		JosephProblemCircle circle = (JosephProblemCircle) context.getBean("circle");
		circle.setInterval(interval);
		circle.setStart(start);
		circle.setPersons(persons);
		
		((ConfigurableApplicationContext)context).close();
		return new JosephProblemRequest(circle);
	}

	@Override
	public JSONObject toJson(JosephProblemRequest requestObj) {
		JosephProblemCircle circle = requestObj.getCircle();
		
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
