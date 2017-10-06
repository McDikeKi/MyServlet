package org.harvey.solve.converter.particularconverter.particularconverterimpl;

import org.harvey.solve.converter.particularconverter.Converter;
import org.harvey.solve.dto.Response;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResponseConverter implements Converter<Response>{

	@Override
	public Response fromJson(JSONObject jsonObj) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Response response = (Response) context.getBean("response");
		response.setPerson(jsonObj.getString("name"));
		((ConfigurableApplicationContext)context).close();
		return response;
	}

	@Override
	public JSONObject toJson(Response responseObj) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("person", responseObj.getPerson());
		return jsonObject;
	}
		
}
