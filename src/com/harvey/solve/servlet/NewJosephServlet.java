package com.harvey.solve.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.harvey.solve.bean.JosephJsonObj;
import com.harvey.solve.bean.JosephRequest;
import com.harvey.solve.bean.Person;
import com.harvey.solve.service.JosephProblemFunction;


/**
 * Servlet implementation class NewJosephServlet
 */
public class NewJosephServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewJosephServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 
        //response.setCharacterEncoding("utf-8");  
//		int startIndex = Integer.valueOf(request.getParameter("start"));
//		int interval = Integer.valueOf(request.getParameter("interval"));
//		String circle = request.getParameter("circle");
		
		//String lastName = JosephProblemFunction.getFinalElement(circle, startIndex, interval);
		String line="";
		String jsonStr = "";
		while((line = request.getReader().readLine()) != null){
			jsonStr += line.trim();
		}
		//System.out.println("json is"+jsonStr);
		
		Gson gson = new Gson();
		JosephJsonObj jRequest = gson.fromJson(jsonStr, JosephJsonObj.class);
		
		List<String> list = new ArrayList<>(jRequest.getCircle().getPersons());
		
		String result = JosephProblemFunction.getFinalElement(list, Integer.valueOf(jRequest.getCircle().getStart()), 
				Integer.valueOf(jRequest.getCircle().getInterval()));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("{\"person\":\""+result+"\"}");
		out.flush();
		out.close();
	}

}
