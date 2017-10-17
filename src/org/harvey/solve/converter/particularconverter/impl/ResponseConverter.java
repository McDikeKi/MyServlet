package org.harvey.solve.converter.particularconverter.impl;

import org.harvey.solve.converter.particularconverter.Converter;
import org.harvey.solve.dto.JosephProblemResponse;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResponseConverter implements Converter<JosephProblemResponse>{

	@Override
	public JosephProblemResponse fromJson(JSONObject jsonObj) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		JosephProblemResponse response = (JosephProblemResponse) context.getBean("response");
		response.setPerson(jsonObj.getString("name"));
		((ConfigurableApplicationContext)context).close();
		return response;
	}

	@Override
	public JSONObject toJson(JosephProblemResponse responseObj) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("person", responseObj.getPerson());
		return jsonObject;
	}
		
}
