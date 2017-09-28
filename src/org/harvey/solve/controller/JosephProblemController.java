package org.harvey.solve.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.harvey.solve.business.CheckJosephRequest;
import org.harvey.solve.business.SolveJosephProblem;
import org.harvey.solve.converter.universalconverter.JsonConverter;
import org.harvey.solve.dto.Request;
import org.harvey.solve.dto.Response;
import org.harvey.solve.exception.IlligalInputException;
import org.harvey.solve.servlet.NewJosephServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ProblemSolve")
public class JosephProblemController {
	private static Logger log = Logger.getLogger(NewJosephServlet.class);
	
	@RequestMapping("/JosephProblem")
    public void solveJosephProblem(HttpServletRequest request,HttpServletResponse response){  
		boolean legalInput = false;
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		String result;
		
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage());
			response.setStatus(500);
			return;
		}
		
		String line="";
		StringBuffer jsonStr = new StringBuffer();
		
		try {
			while((line = request.getReader().readLine()) != null){
				jsonStr.append(line.trim());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage());
			response.setStatus(500);
			return;
		}
		
		legalInput = true;
		JSONObject jsonObj = new JSONObject(jsonStr.toString());
		try {
			CheckJosephRequest.check(jsonObj);
		} catch (IlligalInputException e) {
			log.warn(e.getMessage()+"-"+e.getCause().getMessage());
			legalInput = false;
		}
		if(legalInput){
			JsonConverter converter = new JsonConverter();
			Request josephRequest = null;
			try {
				josephRequest = (Request) converter.fromJson(jsonObj,Request.class);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NegativeArraySizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Response josephResponse = SolveJosephProblem.solve(josephRequest);
			
			JSONObject responseJsonObj = null;
			try {
				responseJsonObj = converter.toJson(josephResponse,Response.class);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = responseJsonObj.toString();
			response.setStatus(200);
		}
		else{
			result = "";
			response.setStatus(204);
		}
		out.print(result);
		out.flush();
		out.close();
    }
	
	@RequestMapping("hello")
	public String helloPage(){
		return "hello";
	}
}
