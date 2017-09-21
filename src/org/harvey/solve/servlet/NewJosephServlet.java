package org.harvey.solve.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.harvey.solve.service.JosephProblemFunction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.harvey.solve.bean.JosephJsonObj;


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
		int startIndex;
		int interval;
		while((line = request.getReader().readLine()) != null){
			jsonStr += line.trim();
		}
		//System.out.println("json is"+jsonStr);
		
//		Gson gson = new Gson();
//		JosephJsonObj jRequest = gson.fromJson(jsonStr, JosephJsonObj.class);
		
//		startIndex = Integer.valueOf(jRequest.getCircle().getStart());
//		interval = Integer.valueOf(jRequest.getCircle().getInterval());
//		List<String> list = new ArrayList<>(jRequest.getCircle().getPersons());
		
		JSONObject jsonObj = JSONObject.fromString(jsonStr);
		
		startIndex = Integer.valueOf(jsonObj.getJSONObject("circle").getString("start"));
		interval = Integer.valueOf(jsonObj.getJSONObject("circle").getString("interval"));
	
		List<String> list = JSONArray.toList(jsonObj.getJSONObject("circle").getJSONArray("persons"),String.class);
		
		String result = JosephProblemFunction.getFinalElement(list,startIndex,interval);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("{\"person\":\""+result+"\"}");
		out.flush();
		out.close();
	}

}
