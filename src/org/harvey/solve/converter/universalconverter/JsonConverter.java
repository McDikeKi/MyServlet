package org.harvey.solve.converter.universalconverter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

import org.harvey.solve.annotation.Mapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonConverter {
	public Object fromJson(JSONObject jsonObject,Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NegativeArraySizeException, ClassNotFoundException, JSONException{
		Object object = null;
		object = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			String fieldName = field.getAnnotation(Mapping.class).jsonFieldName();
			if(jsonObject.has(fieldName)){
				Object json = new JSONTokener(JSONObject.valueToString(jsonObject.get(fieldName))).nextValue();
				if(json instanceof JSONObject){
					field.set(object, fromJson(jsonObject.getJSONObject(fieldName), field.getType()));
				}
				else if(json instanceof JSONArray){
					field.set(object, fromJsonArray(jsonObject.getJSONArray(fieldName),field.getType()));
				}
				else{
					field.set(object, jsonObject.get(fieldName));
				}
			}
		}
		return object;
	}
	
	public Object[] fromJsonArray(JSONArray jsonArray,Class<?> clazz) throws NegativeArraySizeException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, JSONException{;
		if(jsonArray.length()==0){
			return (Object[]) Array.newInstance(clazz, 0);
		}
		else{
			Object arrayObject = new JSONTokener(JSONObject.valueToString(jsonArray.get(0))).nextValue();
			if(arrayObject instanceof JSONObject){
				String itemClassName = clazz.getTypeName().toString().substring(0,clazz.getName().toString().length()-2);
				Object[] value = null;
				value = (Object[]) Array.newInstance(Class.forName(itemClassName),jsonArray.length());
				for(int i = 0;i < jsonArray.length();i++){
					value[i] = fromJson(jsonArray.getJSONObject(i), Class.forName(itemClassName));
				}
				return value;
			}
			else if(arrayObject instanceof JSONArray){
				String itemClassName = clazz.getTypeName().toString().substring(0,clazz.getName().toString().length()-2);
				Object[] value = (Object[]) Array.newInstance( Class.forName(itemClassName),jsonArray.length());
				for(int i = 0;i < jsonArray.length();i++){
					value[i] = fromJsonArray(jsonArray.getJSONArray(i), clazz);
				}
				return value;
			}
			else{		
				String itemClassName = clazz.getTypeName().toString().substring(0,clazz.getTypeName().toString().length()-2);
				Object[] value = (Object[]) Array.newInstance(Class.forName(itemClassName),jsonArray.length());
				List<Object> itemList = jsonArray.toList();
				for(int i = 0;i < itemList.size();i++){
					value[i] = jsonArray.get(i);
				}
				return value;
			}
		}
	}
	
	public JSONObject toJson(Object object,Class<?> clazz) throws IllegalArgumentException, IllegalAccessException{
		JSONObject jsonObject = new JSONObject();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			if((field.get(object)).getClass().isArray()){
				JSONArray array = new JSONArray();
				Object[] list = (Object[]) field.get(object);
				for(int i = 0;i<Array.getLength(list);i++){
					array.put(list[i]);
				}
				jsonObject.put(field.getAnnotation(Mapping.class).jsonFieldName(), list);
			}
			else{
				String type = field.getType().toString();
				if(type.endsWith("int")||type.endsWith("Integer")||type.endsWith("String")
						||type.endsWith("boolean")||type.endsWith("Boolean")
						||type.endsWith("float")||type.endsWith("Float")||type.endsWith("double")||type.endsWith("Double")
						||type.endsWith("long")||type.endsWith("Long")){
					jsonObject.put(field.getAnnotation(Mapping.class).jsonFieldName(), field.get(object));
				}
				else{
					jsonObject.put(field.getAnnotation(Mapping.class).jsonFieldName(),toJson(field.get(object), field.getType()));
				}
			}
		}
		return jsonObject;
	}
}
