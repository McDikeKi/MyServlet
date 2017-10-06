package org.harvey.solve.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JosephProblemController {
	private static Logger log = Logger.getLogger(NewJosephServlet.class);
	
	@RequestMapping("/ProblemSolve/JosephProblem")
    public void solveJosephProblem(HttpServletRequest request,HttpServletResponse response){ 
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		boolean legalInput = false;
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		String result;
		
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			log.error(e1.getMessage());
			response.setStatus(500);
			((ConfigurableApplicationContext)context).close();
			return;
		}
		
		String line="";
		StringBuffer jsonStr = new StringBuffer();
		
		try {
			while((line = request.getReader().readLine()) != null){
				jsonStr.append(line.trim());
			}
		} catch (IOException e1) {
			log.error(e1.getMessage());
			response.setStatus(500);
			if(out != null){
				out.close();
			}
			((ConfigurableApplicationContext)context).close();
			return;
		}
		
		legalInput = true;
		JSONObject jsonObj = new JSONObject(jsonStr.toString());
		try {
			CheckJosephRequest checkJosephRequest = 
					(CheckJosephRequest) context.getBean("checkJosephRequest");
			checkJosephRequest.check(jsonObj);
		} catch (IlligalInputException e) {
			log.error("Illegal input:",e);
			legalInput = false;
		}
		if(legalInput){
			JsonConverter converter = (JsonConverter) context.getBean("jsonConverter");
			Request josephRequest = null;
			try {
				josephRequest = (Request) converter.fromJson(jsonObj,Request.class);
			} catch (InstantiationException e) {
				log.error("error",e);
			} catch (IllegalAccessException e) {
				log.error("error",e);
			} catch (IllegalArgumentException e) {
				log.error("error",e);
			} catch (NegativeArraySizeException e) {
				log.error("error",e);
			} catch (ClassNotFoundException e) {
				log.error("error",e);
			} catch (JSONException e) {
				log.error("error",e);
			}
			
			SolveJosephProblem solveJosephProblem = 
					(SolveJosephProblem) context.getBean("solveJosephProblem");
			Response josephResponse = solveJosephProblem.solve(josephRequest);
			
			JSONObject responseJsonObj = null;
			try {
				responseJsonObj = converter.toJson(josephResponse,Response.class);
			} catch (IllegalArgumentException e) {
				log.error("error",e);
			} catch (IllegalAccessException e) {
				log.error("error",e);
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
		((ConfigurableApplicationContext)context).close();
    }
	
	@RequestMapping("/ProblemSolve/hello")
	public ModelAndView helloPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		List<String> list = new ArrayList<>();
		list.add("Ready");
		list.add("to");
		list.add("go");
		list.add("!");
		mav.addObject("words","Hey man,how's the day");
		mav.addObject("list",list);
		return mav;
	}
}
