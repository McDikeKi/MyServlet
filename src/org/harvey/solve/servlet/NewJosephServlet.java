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
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NegativeArraySizeException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			Response josephResponse = SolveJosephProblem.solve(josephRequest);
			
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
	}
}
