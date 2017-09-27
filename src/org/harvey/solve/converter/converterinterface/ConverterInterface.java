package org.harvey.solve.converter.converterinterface;

import org.harvey.solve.dto.DataTransferObject;
import org.json.JSONObject;


public interface ConverterInterface <T extends DataTransferObject>{
	public T fromJson(JSONObject obj);
	public JSONObject toJson(T obj);
}
