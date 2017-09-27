package org.harvey.solve.converter.implementer;

import org.harvey.solve.converter.converterinterface.ConverterInterface;
import org.harvey.solve.dto.Response;
import org.json.JSONObject;

public class ResponseConverter implements ConverterInterface<Response>{

	@Override
	public Response fromJson(JSONObject jsonObj) {
		return new Response(jsonObj.getString("name"));
	}

	@Override
	public JSONObject toJson(Response responseObj) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("person", responseObj.getPerson());
		return jsonObject;
	}
		
}
