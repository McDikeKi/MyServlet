package org.harvey.solve.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.harvey.solve.business.CheckJosephRequest;
import org.harvey.solve.business.SolveJosephProblem;
import org.harvey.solve.converter.universalconverter.JsonConverter;
import org.harvey.solve.dto.Request;
import org.harvey.solve.dto.Response;
import org.harvey.solve.exception.IlligalInputException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Servlet implementation class NewJosephServlet
 */
public class NewJosephServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(NewJosephServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewJosephServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		boolean legalInput = false;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String result;
		
		String line="";
		StringBuffer jsonStr = new StringBuffer();
		
		while((line = request.getReader().readLine()) != null){
			jsonStr.append(line.trim());
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
				e.printStackTrace();
			} catch (IllegalAccessException e) {
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
		((ConfigurableApplicationContext)context).close();
	}
}
